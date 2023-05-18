package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import smu.it.ips2.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_page)

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