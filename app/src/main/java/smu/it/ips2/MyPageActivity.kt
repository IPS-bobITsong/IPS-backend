package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import smu.it.ips2.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var binding : ActivityMyPageBinding
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

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
                    // 사용자 이름 사용
                    if (name != null) {
                        runOnUiThread { // UI 업데이트는 UI 스레드에서 실행되어야 함
                            binding.username.text = name
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // 에러 처리\
                }
            })
            database.getReference("users").child(userId).child("age").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val age = dataSnapshot.getValue(String::class.java)
                    // 사용자 이름 사용
                    if (age != null) {
                        runOnUiThread { // UI 업데이트는 UI 스레드에서 실행되어야 함
                            binding.age.text = age
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // 에러 처리\
                }
            })
            database.getReference("users").child(userId).child("sex").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val sex = dataSnapshot.getValue(String::class.java)
                    // 사용자 이름 사용
                    if (sex != null) {
                        runOnUiThread { // UI 업데이트는 UI 스레드에서 실행되어야 함
                            binding.sex.text = sex
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // 에러 처리\
                }
            })
        }
        // 이전 화면으로 이동
        binding.backBtn.setOnClickListener {

        }

        binding.homeBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.checkMyInfoBtn.setOnClickListener {
            intent = Intent(this, CheckMyInfoActivity::class.java)
            startActivity(intent)
        }

        binding.checkDietBtn.setOnClickListener {
            intent = Intent(this, CheckDietActivity::class.java)
            startActivity(intent)
        }

        binding.gainPointBtn.setOnClickListener {
            intent = Intent(this, GainPointActivity::class.java)
            startActivity(intent)
        }

        binding.pointChangeBtn.setOnClickListener {
            intent = Intent(this, ChangeToGiftActivity::class.java)
            startActivity(intent)
        }

    }
}