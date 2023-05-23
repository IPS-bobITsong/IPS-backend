package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
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

            findViewById<ImageButton>(R.id.okBtn).setOnClickListener {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        fun Calculate(standard: Int, eating: Int): String {
            val needs = standard - eating
            var u_text = String()
            if (needs > 0) {
                u_text = "부족해요!"
            } else if (needs == 0) {
                u_text = "적당해요"
            } else if (needs < 0) {
                u_text = "과다해요!"
            }
            return u_text
        }
    }
}