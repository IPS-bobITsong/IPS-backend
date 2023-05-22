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

        var quizPage = intent.getStringExtra("article")

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, GainPointListActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.quizBtn).setOnClickListener {
            if (quizPage.toString() == "탄수화물") {
                intent = Intent(this, QuizCarboActivity::class.java)
                startActivity(intent)
            } else if (quizPage.toString() == "단백질") {
                intent = Intent(this, QuizProteinActivity::class.java)
                startActivity(intent)
            } else if (quizPage.toString() == "지방") {
                intent = Intent(this, QuizFatActivity::class.java)
                startActivity(intent)
            } else if (quizPage.toString() == "당류") {
                intent = Intent(this, QuizSugarActivity::class.java)
                startActivity(intent)
            } else if (quizPage.toString() == "나트륨") {
                intent = Intent(this, QuizSodiumActivity::class.java)
                startActivity(intent)
            } else if (quizPage.toString() == "건강한 학교생활") {
                intent = Intent(this, QuizSchoolActivity::class.java)
                startActivity(intent)
            } else if (quizPage.toString() == "건강한 신체와 자신감") {
                intent = Intent(this, QuizConfidenceActivity::class.java)
                startActivity(intent)
            } else if (quizPage.toString() == "비만 예방") {
                intent = Intent(this, QuizOverActivity::class.java)
                startActivity(intent)
            } else if (quizPage.toString() == "두뇌 회전") {
                intent = Intent(this, QuizBrainActivity::class.java)
                startActivity(intent)
            }
        }

        viewPager_article = findViewById(R.id.vpArticle)
        viewPager_article.adapter = ViewPagerAdapter(getArticles()) // 어댑터 생성
        viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로

    }

    private fun getArticles(): ArrayList<Int> {

        val getArticle = intent.getStringExtra("article")

        if (getArticle.toString() == "탄수화물") {
            return arrayListOf<Int>(
                R.drawable.carbohydrate_article1,
                R.drawable.carbohydrate_article2,
                R.drawable.carbohydrate_article3
            )
        } else if (getArticle.toString() == "단백질") {
            return arrayListOf<Int>(
                R.drawable.protein_article1,
                R.drawable.protein_article2,
                R.drawable.protein_article3
            )
        } else if (getArticle.toString() == "지방") {
            return arrayListOf<Int>(
                R.drawable.fat_article1,
                R.drawable.fat_article2,
                R.drawable.fat_article3
            )
        } else if (getArticle.toString() == "당류") {
            return arrayListOf<Int>(
                R.drawable.sugars_article1,
                R.drawable.sugars_article2,
                R.drawable.sugars_article3
            )
        } else if (getArticle.toString() == "나트륨") {
            return arrayListOf<Int>(
                R.drawable.natrium_article1,
                R.drawable.natrium_article2,
                R.drawable.natrium_article3
            )
        } else if (getArticle.toString() == "건강한 학교생활") {
            return arrayListOf<Int>(
                R.drawable.school_article1,
                R.drawable.school_article2,
                R.drawable.school_article3
            )
        } else if (getArticle.toString() == "건강한 신체와 자신감") {
            return arrayListOf<Int>(
                R.drawable.confidence_article1,
                R.drawable.confidence_article2,
                R.drawable.confidence_article3
            )
        } else if (getArticle.toString() == "비만 예방") {
            return arrayListOf<Int>(
                R.drawable.overweight_article1,
                R.drawable.overweight_article2,
                R.drawable.overweight_article3,
                R.drawable.overweight_article4
            )
        } else {
            return arrayListOf<Int>(
                R.drawable.brain_article1,
                R.drawable.brain_article2,
                R.drawable.brain_article3,
            )
        }
    }


}