//package smu.it.ips2
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.firestore.auth.User
//import smu.it.ips2.databinding.ActivityCheckMyInfoBinding
//
//class CheckMyInfoActivity : AppCompatActivity() {
//
//    private lateinit var binding : ActivityCheckMyInfoBinding
//    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
//    private lateinit var database: FirebaseDatabase
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        database = FirebaseDatabase.getInstance()
//
//        val currentUser = auth.currentUser
//        val userId = currentUser?.uid
//        // 사용자 정보 조회
//        val userRef = database.getReference("users").child(userId.toString())
//        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    val userInfo = snapshot.getValue(User::class.java)
//                    // UI 요소에 사용자 정보 표시
//                    binding.inputName.setText(userInfo?.name)
//                    binding.inputID.setText(userInfo?.id)
//                    binding.inputPWD.setText(userInfo?.password)
//                    // ...
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // 조회 실패 처리
//            }
//        })
//
//        // 사용자 정보 조회
//        val userRef = database.reference.child("users").child(userId)
//        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    val userInfo = snapshot.getValue(User::class.java)
//                    // UI 요소에 사용자 정보 표시
//                    binding.inputName.setText(userInfo?.name)
//                    binding.inputID.setText(userInfo?.id)
//                    binding.inputPWD.setText(userInfo?.password)
//                    binding.selectAge.setSelection(getAgeIndex(userInfo?.age))
//                    // ...
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // 조회 실패 처리
//            }
//        })
//
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_my_info)
//
//        binding.exitBtn.setOnClickListener {
//            intent = Intent(this, MyPageActivity::class.java)
//            startActivity(intent)
//        }
//
//        val spinner = binding.selectAge
//        spinner.adapter = ArrayAdapter.createFromResource(
//            this,
//            R.array.ageList,
//            android.R.layout.simple_spinner_item
//        )
//
//        // 기본값 기존에 저장된 나이로 변경
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                // when 조건문 이용해서 데이터 받아야 될듯
//
//            }
//        }
//
////        binding.changeBtn.setOnClickListener {
////            intent = Intent(this, ChangeInfoPopActivity::class.java)
////            startActivity(intent)
////        }
//        binding.changeBtn.setOnClickListener {
//            val newName = binding.inputName.text.toString()
//            val newId = binding.inputID.text.toString()
//            val newPassword = binding.inputPWD.text.toString()
//
//            // 사용자 정보 업데이트
//            val userRef = database.reference.child("users").child(userId)
//            userRef.child("name").setValue(newName)
//            userRef.child("id").setValue(newId)
//            userRef.child("password").setValue(newPassword)
//            // ...
//
//            // 수정 완료 후 액티비티 이동 등 필요한 처리 수행
//        }
//
//    }
//}

package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import smu.it.ips2.databinding.ActivityCheckMyInfoBinding

class CheckMyInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCheckMyInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_my_info)

        binding.exitBtn.setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        val spinner = binding.selectAge
        spinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.ageList,
            android.R.layout.simple_spinner_item
        )

        // 기본값 기존에 저장된 나이로 변경

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // when 조건문 이용해서 데이터 받아야 될듯

            }
        }

        binding.changeBtn.setOnClickListener {
            intent = Intent(this, ChangeInfoPopActivity::class.java)
            startActivity(intent)
        }
    }
}