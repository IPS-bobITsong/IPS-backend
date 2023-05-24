package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import smu.it.ips2.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var binding : ActivityMyPageBinding
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_page)
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance()

        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        if (userId != null) {
            database.getReference("users").child(userId).child("name").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val name = dataSnapshot.getValue(String::class.java)
                    if (name != null) {
                        runOnUiThread {
                            binding.username.text = name
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
            val ageRef = database.getReference("users").child(userId).child("age")
            ageRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val age = dataSnapshot.getValue(String::class.java)
                    // 사용자 나이 사용
                    if (age != null) {
                        runOnUiThread {
                            binding.age.text = age + "세"
                        }
                    } else {
                        // age 값이 null인 경우 기본 값 "1"로 설정
                        runOnUiThread {
                            ageRef.setValue("1")
                                .addOnSuccessListener {
                                    Log.d("MyPageActivity", "Age updated to 1")
                                }
                                .addOnFailureListener { e ->
                                    Log.e("MyPageActivity", "Error updating age: ", e)
                                }
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
            database.getReference("users").child(userId).child("sex").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val sex = dataSnapshot.getValue(String::class.java)
                    if (sex != null) {
                        runOnUiThread {
                            binding.sex.text = sex
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
            database.getReference("users").child(userId).child("point").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val point = dataSnapshot.getValue(String::class.java)
                    if (point != null) {
                        runOnUiThread {
                            binding.point.text = point
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
            var level = database.getReference("users").child(userId).child("level")
            level.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val level = dataSnapshot.getValue(String::class.java)
                    if (level != null) {
                        runOnUiThread {
                            binding.level.text = level
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
            level.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val level = dataSnapshot.getValue(String::class.java)
                    val levelIcon = when (level) {
                        "1" -> R.drawable.level1
                        "2" -> R.drawable.level2
                        "3" -> R.drawable.level3
                        "4" -> R.drawable.level4
                        "5" -> R.drawable.level5
                        else -> R.drawable.level1 // 기본 이미지
                    }
                    binding.levelicon.setImageResource(levelIcon)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // 에러 처리
                }
            })

        }
        // 건강 레벨 식단 개수에 따라 변환
        firestore = FirebaseFirestore.getInstance()

        firestore.collection("users").document(userId.toString())
            .collection("menubook")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val menuBookCount = querySnapshot.size()
                Log.d("SearchMenuActivity", "MenuBook Count: $menuBookCount")
                val levelRef = database.getReference("users").child(userId.toString()).child("level")
                when {
                    menuBookCount >= 60 -> {
                        levelRef.setValue("5")
                            .addOnSuccessListener {
                                Log.d("SearchMenuActivity", "Health Level updated to 5")
                                levelRef.setValue("5")
                            }
                            .addOnFailureListener { e ->
                                Log.e("SearchMenuActivity", "Error updating level: ", e)
                            }
                    }
                    menuBookCount >= 45 -> {
                        levelRef.setValue("4")
                            .addOnSuccessListener {
                                Log.d("SearchMenuActivity", "Health Level updated to 4")
                                levelRef.setValue("4")
                            }
                            .addOnFailureListener { e ->
                                Log.e("SearchMenuActivity", "Error updating level: ", e)
                            }
                    }
                    menuBookCount >= 30 -> {
                        levelRef.setValue("3")
                            .addOnSuccessListener {
                                Log.d("SearchMenuActivity", "Health Level updated to 3")
                                levelRef.setValue("3")                            }
                            .addOnFailureListener { e ->
                                Log.e("SearchMenuActivity", "Error updating level: ", e)
                            }
                    }
                    menuBookCount >= 15 -> {
                        levelRef.setValue("2")
                            .addOnSuccessListener {
                                Log.d("SearchMenuActivity", "Health Level updated to 2")
                                levelRef.setValue("2")                            }
                            .addOnFailureListener { e ->
                                Log.e("SearchMenuActivity", "Error updating level: ", e)
                            }
                    }
                    menuBookCount >= 0 -> {
                        levelRef.setValue("1")
                            .addOnSuccessListener {
                                Log.d("SearchMenuActivity", "Health Level updated to 1")
                                levelRef.setValue("1")                            }
                            .addOnFailureListener { e ->
                                Log.e("SearchMenuActivity", "Error updating level: ", e)
                            }
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("SearchMenuActivity", "Error getting documents: ", e)
            }


        binding.homeBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.gainPointBtn.setOnClickListener {
            intent = Intent(this, GainPointListActivity::class.java)
            startActivity(intent)
        }

        binding.pointChangeBtn.setOnClickListener {
            intent = Intent(this, ChangeToGiftActivity::class.java)
            startActivity(intent)
        }

    }
}