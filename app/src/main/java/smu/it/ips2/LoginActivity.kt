package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import smu.it.ips2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val radioButton = binding.radioButton

        binding.buttonLogIn.setOnClickListener {
            if (radioButton.isChecked == true) {
                intent = Intent(this, LoginPopActivity::class.java)
                startActivity(intent)
            }
            else if (radioButton.isChecked == false) {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        binding.buttonSignUp.setOnClickListener {
            intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

    }

}