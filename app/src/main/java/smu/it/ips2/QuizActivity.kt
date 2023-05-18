package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, GainPointActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 맞았으면 CorrectActivity로, 틀렸으면 WrongActivity로
        findViewById<ImageButton>(R.id.checkAnswerBtn).setOnClickListener {

        }
    }
}