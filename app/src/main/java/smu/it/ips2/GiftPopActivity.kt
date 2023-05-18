package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class GiftPopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_pop)

        findViewById<ImageButton>(R.id.checkBtn).setOnClickListener {
            intent = Intent(this, ChangeToGiftActivity::class.java)
            startActivity(intent)
        }
    }
}