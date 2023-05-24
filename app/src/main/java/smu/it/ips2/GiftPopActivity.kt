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

class GiftPopActivity : AppCompatActivity() {

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_pop)

        findViewById<TextView>(R.id.giftName).text = intent.getStringExtra("giftName")

        findViewById<ImageButton>(R.id.checkBtn).setOnClickListener {
            intent = Intent(this, ChangeToGiftActivity::class.java)
            startActivity(intent)
        }

        database = FirebaseDatabase.getInstance()
        val currentUser = auth.currentUser
        val userId = currentUser?.uid

        val ref = database.getReference("users").child(userId.toString()).child("point")

        // 데이터 읽기
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val currentValue = dataSnapshot.getValue(String::class.java) // 현재 값 가져오기

                // 현재 값에서 변화시킬 작업 수행
                val newValue = (currentValue?.toIntOrNull() ?: 0) - 400 // 현재 값에 400 삭제

                // 데이터 수정
                ref.setValue(newValue.toString())
                    .addOnSuccessListener {
                        // 수정 성공
                        Toast.makeText(this@GiftPopActivity, "400p 차감되었습니다", Toast.LENGTH_SHORT).show()
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