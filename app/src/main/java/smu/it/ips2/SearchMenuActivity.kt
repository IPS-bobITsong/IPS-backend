package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SearchMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_menu)

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

        // 메뉴 검색 결과 아래에 뜨도록
        findViewById<ImageButton>(R.id.searchBtn).setOnClickListener {

        }

        // 메뉴 선택하면 장바구니에 담기도록

        // 취소 버튼 누르면 삭제

        // 선택한 메뉴 정보 받아서
        findViewById<ImageButton>(R.id.selectBtn).setOnClickListener {
            intent = Intent(this, CompleteActivity::class.java)
            startActivity(intent)
        }
    }
}