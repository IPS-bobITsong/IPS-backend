package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioButton

class QuizOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_overweight)

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, GainPointActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        findViewById<ImageButton>(R.id.checkAnswerBtn).setOnClickListener {
            if (findViewById<RadioButton>(R.id.option2).isChecked) {
                intent = Intent(this, CorrectActivity::class.java)
                startActivity(intent)
            }else {
                var correctanswer = findViewById<RadioButton>(R.id.option2).text
                intent = Intent(this, WrongActivity::class.java)
                intent.putExtra("answer", correctanswer)
                startActivity(intent)
            }
        }
    }
}