package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView

class GainPointListActivity : BaseActivity() {

    val quizList = ArrayList<Quizes>()
    lateinit var quizAdapter: QuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gain_point_list)

        setupEvents()
        setValues()

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun setupEvents() {

        findViewById<ListView>(R.id.quizListView).setOnItemClickListener { adapterView, view, i, l ->

            val clickedQuiz = quizList[i]
            val myIntent = Intent(mContext, ExGainpointactivity::class.java)
            myIntent.putExtra("quizes", clickedQuiz)
            startActivity(myIntent)
        }
    }

    override fun setValues() {
        quizList.add(Quizes("건강기사 1","탄수화물"))
        quizList.add(Quizes("건강기사 2","단백질"))
        quizList.add(Quizes("건강기사 3","지방"))
        quizList.add(Quizes("건강기사 4" ,"당류"))
        quizList.add(Quizes("건강기사 5","나트륨"))
        quizList.add(Quizes("건강기사 6","건강한 학교생활"))
        quizList.add(Quizes("건강기사 7","건강한 신체와 자신감"))
        quizList.add(Quizes("건강기사 8","비만 예방"))
        quizList.add(Quizes("건강기사 9","두뇌회전"))
        quizAdapter = QuizAdapter(mContext, R.layout.activity_quiz_list, quizList)
        findViewById<ListView>(R.id.quizListView).adapter = quizAdapter

    }
}