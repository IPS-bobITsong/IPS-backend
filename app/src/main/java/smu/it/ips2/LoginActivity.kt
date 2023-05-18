package smu.it.ips2

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import smu.it.ips2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.buttonLogIn.setOnClickListener {
            val id = binding.editTextID.text.toString()
            val password = binding.editTextPassWord.text.toString()

            auth.signInWithEmailAndPassword(id, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else {
                        Toast.makeText(this, "로그인에 실패했어요", Toast.LENGTH_LONG).show()
                    }
                }
        }

        binding.buttonSignUp.setOnClickListener {
            intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }
}