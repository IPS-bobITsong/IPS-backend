package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class GiftPopFailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_pop_failed)

        findViewById<ImageButton>(R.id.checkBtn).setOnClickListener {
            intent = Intent(this, ChangeToGiftActivity::class.java)
            startActivity(intent)
        }
    }
}