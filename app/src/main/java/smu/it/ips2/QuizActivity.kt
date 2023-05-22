package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_quiz)
        setContentView(R.layout.activity_quiz_carbohydrate)

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
            val radioGroup = findViewById<RadioGroup>(R.id.answerGroup)  // XML 레이아웃에서 RadioGroup 뷰를 찾아옵니다.
            val selectedOptionId = radioGroup.checkedRadioButtonId

            if (selectedOptionId != -1) {
                val selectedOption = findViewById<RadioButton>(selectedOptionId)
                val answer = selectedOption.text.toString()

                val intent = if (answer == "선지 1") {    // 정답 : 선지 1
                    Intent(this, CorrectActivity::class.java)   // 정답인 경우
                } else {
                    Intent(this, WrongActivity::class.java)     // 오답인 경우
                }

                startActivity(intent)
            }
        }
    }
}