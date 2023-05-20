package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChangeToGiftActivity : AppCompatActivity() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_to_gift)

        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val database = FirebaseDatabase.getInstance()

        database.getReference("users/$userId/name").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userName = snapshot.getValue(String::class.java)

                // username TextView 가져오기
                val usernameTextView = findViewById<TextView>(R.id.username)

                // 사용자 이름 설정
                usernameTextView.text = userName
            }

            override fun onCancelled(error: DatabaseError) {
                // 데이터베이스에서 값 가져오기 실패
                // 처리 방법 결정 or 오류 처리 가능
            }
        })

        database.getReference("users/$userId/point").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userPoint = snapshot.getValue(String::class.java)

                val userPointTextView = findViewById<TextView>(R.id.point)

                // 사용자 이름 설정
                userPointTextView.text = userPoint
            }

            override fun onCancelled(error: DatabaseError) {
                // 데이터베이스에서 값 가져오기 실패
                // 처리 방법 결정 or 오류 처리 가능
            }
        })

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 상품 버튼들 클릭하면 테두리 색상 바뀌게

        findViewById<ImageButton>(R.id.downloadBtn).setOnClickListener {
            intent = Intent(this, GiftPopActivity::class.java)
            startActivity(intent)
        }
    }
}