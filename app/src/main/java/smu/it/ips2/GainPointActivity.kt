package smu.it.ips2

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
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
        val article = intent.getStringExtra("quizes")
        when (article) {
            "건강기사 1" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles1())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }

            "건강기사 1" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles1())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }

            "건강기사 2" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles2())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }

            "건강기사 3" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles3())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }

            "건강기사 4" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles4())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }

            "건강기사 5" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles5())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }

            "건강기사 6" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles6())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }

            "건강기사 7" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles7())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }

            "건강기사 8" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles8())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
            "건강기사 9" -> {
                viewPager_article.adapter = ViewPagerAdapter(getArticles8())
                viewPager_article.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
        }
    }
        private fun getArticles1(): ArrayList<Int> {
            return arrayListOf<Int>(
                R.drawable.carbohydrate_article1,)
            R.drawable.carbohydrate_article2,)
            R.drawable.article1_1)
        }
        private fun getArticles2(): ArrayList<Int> {
            return arrayListOf<Int>(
                R.drawable.article1_1,)
            R.drawable.article1_1,)
            R.drawable.article1_1)
        }
        private fun getArticles3(): ArrayList<Int> {
            return arrayListOf<Int>(
                R.drawable.article1_1,)
            R.drawable.article1_1,)
            R.drawable.article1_1)
        }
    private fun getArticles4(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.article1_1,)
        R.drawable.article1_1,)
        R.drawable.article1_1)
    }
    private fun getArticles5(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.article1_1,)
        R.drawable.article1_1,)
        R.drawable.article1_1)
    }
    private fun getArticles6(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.article1_1,)
        R.drawable.article1_1,)
        R.drawable.article1_1)
    }
    private fun getArticles7(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.article1_1,)
        R.drawable.article1_1,)
        R.drawable.article1_1)
    }
    private fun getArticles8(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.article1_1,)
        R.drawable.article1_1,)
        R.drawable.article1_1)
    }
    private fun getArticles9(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.article1_1,)
        R.drawable.article1_1,)
        R.drawable.article1_1)
    }

}