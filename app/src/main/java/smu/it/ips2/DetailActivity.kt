package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2

class DetailActivity : AppCompatActivity() {

    lateinit var viewPager_card: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val needNutrient = intent.getStringExtra("nutrient")
        findViewById<TextView>(R.id.textViewNutrient).text = needNutrient
        findViewById<TextView>(R.id.textViewNoticeNutrient).text = needNutrient

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, CompleteActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.mypageBtn).setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 해당 가맹점 링크
        findViewById<ImageButton>(R.id.buttonRecommend1).setOnClickListener {

        }

        // 해당 가맹점 링크
        findViewById<ImageButton>(R.id.buttonRecommend2).setOnClickListener {

        }

        viewPager_card = findViewById(R.id.cardNews)
        viewPager_card.adapter = ViewPagerAdapter(getCardNews()) // 어댑터 생성
        viewPager_card.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
    }

    private fun getCardNews(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.na1,
            R.drawable.na2,
            R.drawable.na3)
    }

}