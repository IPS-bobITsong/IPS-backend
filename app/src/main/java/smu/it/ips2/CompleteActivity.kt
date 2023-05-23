package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CompleteActivity : AppCompatActivity() {
    private var selectedMenu: MenuBook? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

//        // 선택한 메뉴 정보를 가져옴
//        selectedMenu = intent.getParcelableExtra("selectedMenu")
//
//        // 선택한 메뉴 정보를 사용하여 필요한 처리를 수행
//        if (selectedMenu != null) {
//            // 선택한 메뉴에 대한 처리
//        }

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, SearchRestaurantActivity::class.java)
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
//
//        findViewById<ImageButton>(R.id.detailBtn).setOnClickListener {
//            intent = Intent(this, DetailActivity::class.java)
//            startActivity(intent)
//        }

        findViewById<ImageButton>(R.id.nextBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}