package smu.it.ips2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class CheckPolicyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_policy)

        findViewById<ImageButton>(R.id.agreeBtn).setOnClickListener {
            intent = Intent(this, JoinActivity::class.java)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}