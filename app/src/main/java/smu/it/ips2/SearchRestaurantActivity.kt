package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SearchRestaurantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_restaurant)

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
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

        // 검색한 식당 결과 아래에 뜨도록
        findViewById<ImageButton>(R.id.searchBtn).setOnClickListener {

        }

        // 식당 클릭하면 메뉴 검색 화면으로 이동

        // 식당 즐겨찾기 하면 아이콘 바뀌고 상단에 계속 고정되도록

    }
}