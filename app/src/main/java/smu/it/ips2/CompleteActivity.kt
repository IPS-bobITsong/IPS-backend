package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CompleteActivity : AppCompatActivity() {
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    var standard_carbohydrate: Float = 0.0f
    var standard_protein: Float = 0.0f
    var standard_fat: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        //사용자 나이에 따라 기준 영양소 세팅
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        if (userId != null) {
            database.getReference("users").child(userId).child("age")
                .addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val age = dataSnapshot.getValue(Int::class.java)
                        if (age != null) {
                            if (age in 1 until 3) {
                                standard_carbohydrate = 43.33f
                                standard_protein = 6.67f
                                standard_fat = 11.67f
                            } else if (age in 3 until 6) {
                                standard_carbohydrate = 43.33f
                                standard_protein = 8.33f
                                standard_fat = 11.67f
                            } else if (age in 6 until 9) {
                                standard_carbohydrate = 43.33f
                                standard_protein = 11.67f
                                standard_fat = 13.33f
                            } else if (age in 9 until 12) {
                                standard_carbohydrate = 43.33f
                                standard_protein = 16.67f
                                standard_fat = 16f
                            } else if (age in 12 until 15) {
                                standard_carbohydrate = 43.33f
                                standard_protein = 20f
                                standard_fat = 18.33f
                            } else if (age in 15 until 19) {
                                standard_carbohydrate = 43.33f
                                standard_protein = 21.67f
                                standard_fat = 23.33f
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
        }

        findViewById<TextView>(R.id.carbohydrate).text = u_carbohydrate.toString()
        findViewById<TextView>(R.id.protein).text = u_protein.toString()
        findViewById<TextView>(R.id.fat).text = u_fat.toString()


        val r_carbohydrate: Float = standard_carbohydrate - u_carbohydrate
        val r_protein: Float = standard_protein - u_protein
        val r_fat: Float = standard_fat - u_fat

        //기준-영양소 계산 후 절댓값 씌우기
        val a_carbohydrate = Math.abs(r_carbohydrate)
        val a_protein = Math.abs(r_protein)
        val a_fat = Math.abs(r_fat)

        //절댓값 가장 큰 영양소 나타내기
        val noticeNutrient = compare(a_carbohydrate, a_protein, a_fat)
        findViewById<TextView>(R.id.nutrient).text = noticeNutrient

        //과다/부족 글자 나타내기
        val needText = findViewById<TextView>(R.id.moreOrLess)
        if(a_carbohydrate > a_protein && a_carbohydrate > a_fat) {
            needText.text = setText(r_carbohydrate)
        } else if(a_protein > a_carbohydrate && a_protein > a_fat) {
            needText.text = setText(r_protein)
        } else if(a_fat > a_carbohydrate && a_fat > a_protein) {
            needText.text = setText(r_fat)
        }

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, SearchMenuActivity::class.java)
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

    //세가지 기준-영양 입력받고, 가장 절댓값이 큰 값 알아내기
    fun compare(carbohydrate: Float, protein: Float, fat: Float): String {
        return when {
            carbohydrate > protein && carbohydrate > fat -> "탄수화물"
            protein > carbohydrate && protein > fat -> "단백질"
            fat > carbohydrate && fat > protein -> "지방"
            else -> ""
        }
    }

    fun setText(result: Float): String{
        return when {
            result > 0 -> "부족해요!"
            result < 0 -> "과다해요!"
            else -> ""
        }
    }

}
