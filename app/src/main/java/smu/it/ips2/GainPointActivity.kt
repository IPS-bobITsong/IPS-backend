package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.viewpager2.widget.ViewPager2

class GainPointActivity : AppCompatActivity() {
    lateinit var viewPager_article : ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gain_point)

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.quizBtn).setOnClickListener {
            intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        viewPager_article = findViewById(R.id.vpArticle)
        viewPager_article.adapter = ViewPagerAdapter(getArticles()) // 어댑터 생성
        viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로

    }

    private fun getArticles(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.article1_1,
            R.drawable.article1_2,
            R.drawable.article1_3)
    }

}