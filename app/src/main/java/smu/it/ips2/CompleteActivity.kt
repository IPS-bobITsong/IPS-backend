package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

class CompleteActivity : AppCompatActivity() {
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var firestore : FirebaseFirestore

    private var u_menuName: String = ""
    private var u_carbohydrate: Double = 0.0
    private var u_protein: Double = 0.0
    private var u_fat: Double = 0.0
    private var u_sugars: Double = 0.0
    private var u_sodium: Double = 0.0

    private var standard_carbohydrate: Double = 0.0
    private var standard_protein: Double = 0.0
    private var standard_fat: Double = 0.0
    private var standard_sugars: Double = 0.0
    private var standard_sodium: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        u_menuName = intent.getStringExtra("foodname").toString()
        u_carbohydrate = intent.getDoubleExtra("carbo", 0.0)
        u_protein = intent.getDoubleExtra("protein", 0.0)
        u_fat = intent.getDoubleExtra("fat", 0.0)
        u_sugars = intent.getDoubleExtra("sugars", 0.0)
        u_sodium = intent.getDoubleExtra("sodium", 0.0)

        findViewById<TextView>(R.id.selectedMenu1).text = u_menuName
        findViewById<TextView>(R.id.selectedMenu2).text = u_menuName
        findViewById<TextView>(R.id.carbohydrate).text = u_carbohydrate.toString()
        findViewById<TextView>(R.id.protein).text = u_protein.toString()
        findViewById<TextView>(R.id.fat).text = u_fat.toString()
        findViewById<TextView>(R.id.sugars).text = u_sugars.toString()
        findViewById<TextView>(R.id.sodium).text = u_sodium.toString()



        //사용자 나이에 따라 기준 영양소 세팅
        val currentUser = auth.currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            database.getReference("users").child(userId).child("age")
                .addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val age = dataSnapshot.getValue(String::class.java)?.toInt()
                        if (age != null) {
                            if (age in 1 until 3) {
                                standard_carbohydrate = 43.33
                                standard_protein = 6.67
                                standard_fat = 11.67
                                standard_sugars = 5.0
                                standard_sodium = 233.33
                            } else if (age in 3 until 6) {
                                standard_carbohydrate = 43.33
                                standard_protein = 8.33
                                standard_fat = 11.67
                                standard_sugars = 6.67
                                standard_sodium = 300.0
                            } else if (age in 6 until 9) {
                                standard_carbohydrate = 43.33
                                standard_protein = 11.67
                                standard_fat = 13.33
                                standard_sugars = 8.33
                                standard_sodium = 400.0
                            } else if (age in 9 until 12) {
                                standard_carbohydrate = 43.33
                                standard_protein = 16.67
                                standard_fat = 16.0
                                standard_sugars = 10.33
                                standard_sodium = 500.0
                            } else if (age in 12 until 15) {
                                standard_carbohydrate = 43.33
                                standard_protein = 20.0
                                standard_fat = 18.33
                                standard_sugars = 13.33
                                standard_sodium = 500.0
                            } else if (age in 15 until 19) {
                                standard_carbohydrate = 43.33
                                standard_protein = 21.67
                                standard_fat = 23.33
                                standard_sugars = 15.0
                                standard_sodium = 500.0
                            }
                            Log.d("SearchMenuActivity", "standard_fat11: $standard_fat")
                        }
                        Log.d("SearchMenuActivity", "s_fat12: $standard_fat")
                        var r_carbohydrate: Double = 0.0
                        var r_protein: Double = 0.0
                        var r_fat: Double = 0.0
                        var r_sugars: Double = 0.0
                        var r_sodium: Double = 0.0

                        var carbohydrate_more: Boolean = true
                        var protein_more: Boolean = true
                        var fat_more: Boolean = true
                        var sugars_more: Boolean = true
                        var sodium_more: Boolean = true
                        // 영양소 / 기준 계산 후 차이값 계산하기
                        if (standard_carbohydrate > u_carbohydrate) {
                            r_carbohydrate = u_carbohydrate / standard_carbohydrate
                            carbohydrate_more = false
                        }
                        else r_carbohydrate = standard_carbohydrate / u_carbohydrate
                        if (standard_protein > u_protein) {
                            r_protein = u_protein / standard_protein
                            protein_more = false
                        }
                        else r_protein = standard_protein / u_protein
                        if (standard_fat > u_fat) {
                            r_fat = u_fat / standard_fat
                            fat_more = false
                        }
                        else r_fat = standard_fat / u_fat
                        if (standard_sugars > u_sugars) {
                            r_sugars = u_sugars / standard_sugars
                            sugars_more = false
                        }
                        else r_sugars = standard_sugars / u_sugars
                        if (standard_sodium > u_sodium) {
                            r_sodium = u_sodium / standard_sodium
                            sodium_more = false
                        }
                        else r_sodium = standard_sodium / u_sodium
//                        //기준-영양소 계산 후 절댓값 씌우기
//                        val a_carbohydrate = Math.abs(r_carbohydrate!!)
//                        val a_protein = Math.abs(r_protein!!)
//                        val a_fat = Math.abs(r_fat!!)
//                        val a_sugars = Math.abs(r_sugars!!)
//                        val a_sodium = Math.abs(r_sodium!!)
                        Log.d("SearchMenuActivity", "a_carbo: $r_carbohydrate")
                        Log.d("SearchMenuActivity", "a_protein: $r_protein")
                        Log.d("SearchMenuActivity", "a_fat: $r_fat")
                        Log.d("SearchMenuActivity", "a_sugars: $r_sugars")
                        Log.d("SearchMenuActivity", "a_sodium: $r_sodium")
                        //차이값 가장 큰 영양소 나타내기
                        val arr:Array<Double> = arrayOf(r_carbohydrate, r_protein, r_fat, r_sugars, r_sodium)
                        val noticeNutrient = compare(arr)
                        Log.d("SearchMenuActivity", "noticeNutrient: $noticeNutrient")
                        findViewById<TextView>(R.id.nutrient).text = noticeNutrient
                        // 과다/부족
                        var moreOrLess = findViewById<TextView>(R.id.moreOrLess)
                        if (noticeNutrient == "탄수화물") {
                            if (carbohydrate_more) moreOrLess.text = "과다해요!"
                            else moreOrLess.text = "부족해요!"
                        } else if (noticeNutrient == "단백질") {
                            if (protein_more) moreOrLess.text = "과다해요!"
                            else moreOrLess.text = "부족해요!"
                        } else if (noticeNutrient == "지방") {
                            if (fat_more) moreOrLess.text = "과다해요!"
                            else moreOrLess.text = "부족해요!"
                        } else if (noticeNutrient == "당류") {
                            if (sugars_more) moreOrLess.text = "과다해요!"
                            else moreOrLess.text = "부족해요!"
                        } else {
                            if (sodium_more) moreOrLess.text = "과다해요!"
                            else moreOrLess.text = "부족해요!"
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
        }



        Log.d("SearchMenuActivity", "carbo: $u_carbohydrate")
        Log.d("SearchMenuActivity", "protein: $u_protein")
        Log.d("SearchMenuActivity", "fat: $u_fat")


//        //과다/부족 글자 나타내기
//        val needText = findViewById<TextView>(R.id.moreOrLess)
//        if(a_carbohydrate > a_protein && a_carbohydrate > a_fat) {
//            needText.text = setText(r_carbohydrate)
//        } else if(a_protein > a_carbohydrate && a_protein > a_fat) {
//            needText.text = setText(r_protein)
//        } else if(a_fat > a_carbohydrate && a_fat > a_protein) {
//            needText.text = setText(r_fat)
//        }

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, SearchRestaurantActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.mypageBtn).setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

//        findViewById<ImageButton>(R.id.nextBtn).setOnClickListener {
//            intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra("nutrient", noticeNutrient)
////            intent.putExtra("needtext", needText.text)
//            startActivity(intent)
//        }

        val database = FirebaseDatabase.getInstance()

        val ref = database.getReference("users").child(userId.toString()).child("point")

        // 데이터 읽기
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val currentValue = dataSnapshot.getValue(String::class.java) // 현재 값 가져오기

                // 현재 값에서 변화시킬 작업 수행
                val newValue = (currentValue?.toIntOrNull() ?: 0) + 30 // 현재 값에 30 추가

                // 데이터 수정
                ref.setValue(newValue.toString())
                    .addOnSuccessListener {
                        // 수정 성공
                        Toast.makeText(this@CompleteActivity, "포인트를 획득했습니다!", Toast.LENGTH_SHORT).show()
                        // 원하는 작업 수행 or 메시지 표시 가능
                    }
                    .addOnFailureListener { exception ->
                        // 수정 실패
                        // 오류 처리 수행 or 메시지 표시 가능
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // 읽기 실패 처리
                // 오류 처리 수행 or 메시지 표시 가능
            }
        })
    }

    //가장 차이값이 큰 값 알아내기
    fun compare(
        array: Array<Double>
    ): String {
        var largest: Double = 0.0
        for (i in array) {
            if (i > largest) largest = i
        }
        if (largest == array[0]) return "탄수화물"
        else if (largest == array[1]) return "단백질"
        else if (largest == array[2]) return "지방"
        else if (largest == array[3]) return "당류"
        else return "나트륨"
//        return when {
////            carbohydrate > protein && carbohydrate > fat &&  -> "탄수화물"
////            protein > carbohydrate && protein > fat -> "단백질"
////            fat > carbohydrate && fat > protein -> "지방"
////            else -> ""
//        }
    }

    fun setText(result: Double): String{
        return when {
            result > 0 -> "부족해요!"
            result < 0 -> "과다해요!"
            else -> ""
        }
    }

}
