package smu.it.ips2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import smu.it.ips2.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private val CHECK_POLICY_REQUEST = 1
    private lateinit var binding : ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        binding.joinBtn.isEnabled = false

        binding.checkPolicyBtn.setOnClickListener {
            intent = Intent(this, CheckPolicyActivity::class.java)
            startActivityForResult(intent, CHECK_POLICY_REQUEST)
        }

        binding.joinBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // JoinActivity 종료
        }

        binding.exitBtn.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val spinner = binding.selectAge
        spinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.ageList,
            android.R.layout.simple_spinner_item
        )
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

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CHECK_POLICY_REQUEST && resultCode == Activity.RESULT_OK) {
            binding.joinBtn.isEnabled = true
            binding.joinBtn.setImageResource(R.drawable.joinafterbtn)
        }
    }

}