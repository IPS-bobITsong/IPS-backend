package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class CompleteActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    var standard_carbohydrate: Double = 0.0
    var standard_protein: Double = 0.0
    var standard_fat: Double = 0.0
    var standard_sugar: Double = 0.0
    var standard_sodium: Double = 0.0

    var u_carbohydrate: Double? = 0.0
    var u_protein: Double? = 0.0
    var u_fat: Double? = 0.0
    var u_sugar: Double? = 0.0
    var u_sodium: Double? = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firestore = FirebaseFirestore.getInstance()

        //사용자 나이에 따라 기준 영양소 세팅
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        if (userId != null) {
            database.getReference("users").child(userId).child("age").get()
                .addOnSuccessListener {
                    (object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val age = dataSnapshot.getValue(String::class.java)?.toInt()
                            if (age != null) {
                                if (age in 1 until 3) {
                                    standard_carbohydrate = 43.33
                                    standard_protein = 6.67
                                    standard_fat = 11.67
                                    standard_sugar = 5.0
                                    standard_sodium = 233.33
                                } else if (age in 3 until 6) {
                                    standard_carbohydrate = 43.33
                                    standard_protein = 8.33
                                    standard_fat = 11.67
                                    standard_sugar = 6.67
                                    standard_sodium = 300.0
                                } else if (age in 6 until 9) {
                                    standard_carbohydrate = 43.33
                                    standard_protein = 11.67
                                    standard_fat = 13.33
                                    standard_sugar = 8.33
                                    standard_sodium = 400.0
                                } else if (age in 9 until 12) {
                                    standard_carbohydrate = 43.33
                                    standard_protein = 16.67
                                    standard_fat = 16.0
                                    standard_sugar = 10.33
                                    standard_sodium = 500.0
                                } else if (age in 12 until 15) {
                                    standard_carbohydrate = 43.33
                                    standard_protein = 20.0
                                    standard_fat = 18.33
                                    standard_sugar = 13.33
                                    standard_sodium = 500.0
                                } else if (age in 15 until 19) {
                                    standard_carbohydrate = 43.33
                                    standard_protein = 21.67
                                    standard_fat = 23.33
                                    standard_sugar = 15.0
                                    standard_sodium = 500.0
                                }
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {

                        }
                    })
                }

            // 파이어스토어 인스턴스 초기화
            firestore.collection("users").document(userId.toString())
                .collection("menubook")
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        return@addSnapshotListener
                    }
                    if (!snapshot!!.isEmpty) {
                        val documentSnapshot = snapshot.documents[snapshot.size() - 1]
                        val menu = documentSnapshot.toObject(MenuBook::class.java)
                        u_carbohydrate = menu?.carbo
                        u_protein = menu?.protein
                        u_fat = menu?.fat
                        u_sugar = menu?.sugars
                        u_sodium = menu?.sodium

                        /*firestore.collection("users").document(userId.toString())
            .collection("menubook")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val menuBookCount = querySnapshot.size()
                Log.d("SearchMenuActivity", "MenuBook Count: $menuBookCount")
                if (!querySnapshot.isEmpty) {
                    val documentSnapshot = querySnapshot.documents[menuBookCount-1]
                    val menu = documentSnapshot.toObject(MenuBook::class.java)
                    u_carbohydrate = menu?.carbo
                    u_protein = menu?.protein
                    u_fat = menu?.fat*/

//                    findViewById<TextView>(R.id.foodName).text = u_menuName.toString()
                        findViewById<TextView>(R.id.carbohydrate).text = u_carbohydrate.toString()
                        findViewById<TextView>(R.id.protein).text = u_protein.toString()
                        findViewById<TextView>(R.id.fat).text = u_fat.toString()
                        findViewById<TextView>(R.id.sugars).text = u_sugar.toString()
                        findViewById<TextView>(R.id.sodium).text = u_sodium.toString()
//                    findViewById<TextView>(R.id.protein).text = u_sugars.toString()
//                    findViewById<TextView>(R.id.sodium).text = u_sodium.toString()
                    }
                }


            Log.d("SearchMenuActivity", "carbo: $u_carbohydrate")
            Log.d("SearchMenuActivity", "protein: $u_protein")
            Log.d("SearchMenuActivity", "fat: $u_fat")

            val r_carbohydrate: Double? = (standard_carbohydrate - u_carbohydrate!!)
            val r_protein: Double? = (standard_protein - u_protein!!)
            val r_fat: Double? = (standard_fat - u_fat!!)
            val r_sugar: Double? = (standard_sugar - u_sugar!!)
            val r_sodium: Double? = (standard_sodium - u_sodium!!)

            //기준-영양소 계산 후 절댓값 씌우기
            val a_carbohydrate = Math.abs(r_carbohydrate!!)
            val a_protein = Math.abs(r_protein!!)
            val a_fat = Math.abs(r_fat!!)
            val a_sugar = Math.abs(r_sugar!!)
            val a_sodium = Math.abs(r_sodium!!)

            //절댓값 가장 큰 영양소 나타내기
            val noticeNutrient = compare(a_carbohydrate, a_protein, a_fat, a_sugar, a_sodium)
            findViewById<TextView>(R.id.nutrient).text = compare(a_carbohydrate, a_protein, a_fat, a_sugar, a_sodium)

            //과다/부족 글자 나타내기
            when(compare(a_carbohydrate, a_protein, a_fat, a_sugar, a_sodium)){
                "탄수화물" -> findViewById<TextView>(R.id.moreOrLess).text = setText(r_carbohydrate)
                "단백질" -> findViewById<TextView>(R.id.moreOrLess).text = setText(r_protein)
                "지방" -> findViewById<TextView>(R.id.moreOrLess).text = setText(r_fat)
                "당" -> findViewById<TextView>(R.id.moreOrLess).text = setText(r_sugar)
                "나트륨" -> findViewById<TextView>(R.id.moreOrLess).text = setText(r_sodium)
            }
            var needText = findViewById<TextView>(R.id.moreOrLess)

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

            findViewById<ImageButton>(R.id.nextBtn).setOnClickListener {
                intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("nutrient", noticeNutrient)
                intent.putExtra("needtext", needText.text)
                startActivity(intent)
            }

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
                            Toast.makeText(
                                this@CompleteActivity,
                                "포인트를 획득했습니다!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
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
    }
    //세가지 기준-영양 입력받고, 가장 절댓값이 큰 값 알아내기
    fun compare(carbohydrate: Double, protein: Double, fat: Double, sugars: Double, sodium: Double): String {
        val nutrientList = mutableListOf<Pair<String, Double>>()
        nutrientList.add("탄수화물" to carbohydrate)
        nutrientList.add("단백질" to protein)
        nutrientList.add("지방" to fat)
        nutrientList.add("당류" to sugars)
        nutrientList.add("나트륨" to sodium)

        val maxNutrient = nutrientList.maxByOrNull { it.second }
        return maxNutrient?.first ?: ""
    }


    fun setText(result: Double): String {
        return when {
            result > 0 -> "부족해요!"
            result < 0 -> "과다해요!"
            else -> ""
        }
    }
}

