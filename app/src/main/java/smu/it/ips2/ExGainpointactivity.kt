package smu.it.ips2

import android.os.Bundle

class ExGainpointactivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gain_point)

        setupEvents()

    }

    override fun setupEvents() {
    }

    override fun setValues() {

        val quiz = intent.getSerializableExtra("quizes") as Quizes


    }
}