package smu.it.ips2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.EnumSet.range

class GainPointListActivity : AppCompatActivity() {
    lateinit var rvAdapter: ArticleRVAdapter
    private val items = ArrayList<ArticleData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gain_point_list)

        val rv : RecyclerView = findViewById(R.id.rv)

        items.add(ArticleData("탄수화물", R.drawable.carbohydrate_article1, "건강기사 1"))
        items.add(ArticleData("단백질", R.drawable.protein_article1, "건강기사 2"))
        items.add(ArticleData("지방", R.drawable.fat_article1, "건강기사 3"))
        items.add(ArticleData("당류", R.drawable.sugars_article1, "건강기사 4"))
        items.add(ArticleData("나트륨", R.drawable.natrium_article1, "건강기사 5"))
        items.add(ArticleData("건강한 학교생활", R.drawable.school_article1, "건강기사 6"))
        items.add(ArticleData("건강한 신체와 자신감", R.drawable.confidence_article1, "건강기사 7"))
        items.add(ArticleData("비만 예방", R.drawable.overweight_article1, "건강기사 8"))
        items.add(ArticleData("두뇌 회전", R.drawable.brain_article1, "건강기사 9"))
        rvAdapter = ArticleRVAdapter(items)
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this, 3)

        rvAdapter.itemClick = object : ArticleRVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

                val  intent = Intent(this@GainPointListActivity, GainPointActivity::class.java)
                intent.putExtra("article", items[position].title)
                startActivity(intent)

                val  quiz1 = Intent(this@GainPointListActivity, QuizCarboActivity::class.java)
                quiz1.putExtra("article", items[position].title)
                val  quiz2 = Intent(this@GainPointListActivity, QuizProteinActivity::class.java)
                quiz2.putExtra("article", items[position].title)
                val  quiz3 = Intent(this@GainPointListActivity, QuizFatActivity::class.java)
                quiz3.putExtra("article", items[position].title)
                val  quiz4 = Intent(this@GainPointListActivity, QuizSugarActivity::class.java)
                quiz4.putExtra("article", items[position].title)
                val  quiz5 = Intent(this@GainPointListActivity, QuizSodiumActivity::class.java)
                quiz5.putExtra("article", items[position].title)
                val  quiz6 = Intent(this@GainPointListActivity, QuizSchoolActivity::class.java)
                quiz6.putExtra("article", items[position].title)
                val  quiz7 = Intent(this@GainPointListActivity, QuizConfidenceActivity::class.java)
                quiz7.putExtra("article", items[position].title)
                val  quiz8 = Intent(this@GainPointListActivity, QuizOverActivity::class.java)
                quiz8.putExtra("article", items[position].title)
                val  quiz9 = Intent(this@GainPointListActivity, QuizBrainActivity::class.java)
                quiz9.putExtra("article", items[position].title)

            }

        }
        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}