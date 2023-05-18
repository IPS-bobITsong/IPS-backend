package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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