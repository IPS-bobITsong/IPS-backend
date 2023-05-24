package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class DetailActivity : AppCompatActivity() {

    lateinit var viewPager_card: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val needNutrient = intent.getStringExtra("nutrient")
        findViewById<TextView>(R.id.textViewNutrient).text = needNutrient
        //findViewById<TextView>(R.id.textViewNoticeNutrient).text = needNutrient

        val noticeMessage = intent.getStringExtra("nutIsNeed")
        if (noticeMessage == "부족해요!") {
            findViewById<TextView>(R.id.moreOrLess).text = "늘려보세요!"
            //findViewById<TextView>(R.id.textViewNotice).text = "함량이 많은 메뉴 둘러보기"
        } else if (noticeMessage == "과다해요!") {
            findViewById<TextView>(R.id.moreOrLess).text = "줄여보세요!"
            //findViewById<TextView>(R.id.textViewNotice).text = "함량이 적은 메뉴 둘러보기"
        }

        // findViewById<TextView>(R.id.textViewNotice).text = "메뉴 둘러보기"


        val standard = intent.getDoubleExtra("standard", 0.0) // 비교할 standard 값

        var minDifference = Double.MAX_VALUE
        var closestFood = ""
        var closestRes = ""

        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("restaurantbook")

        collectionRef.get()
            .addOnSuccessListener { querySnapshot ->
                // 성공적으로 데이터를 가져온 경우
                for (document in querySnapshot) {
                    //val item = document.getString("foodname")
                    //Log.d("foodname", item.toString())
                    val fieldValue = when (needNutrient) {
                        "탄수화물" -> document.getDouble("carbo")!!
                        "단백질" -> document.getDouble("protein")!!
                        "지방" -> document.getDouble("fat")!!
                        "당류" -> document.getDouble("sugars")!!
                        "나트륨" -> document.getDouble("sodium")!!
                        else -> null
                    }

                    if (fieldValue != null) {
                        val difference = Math.abs(fieldValue - standard)
                        if (difference < minDifference) {
                            minDifference = difference
                            closestFood = document.getString("foodname") ?: ""
                            closestRes = document.getString("brand") ?: ""
                        }
                    }
                }

                Log.d("TAG", "Closest foodname: $closestFood")
                Log.d("TAG", "Closest brand: $closestRes")

                findViewById<TextView>(R.id.restaurantName).text = closestRes
                findViewById<TextView>(R.id.foodName).text = closestFood
            }
            .addOnFailureListener { exception ->
                // 데이터 가져오기 실패한 경우
                Log.e("TAG", "Error getting documents: ", exception)
            }


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
        findViewById<ImageButton>(R.id.checkBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        viewPager_card = findViewById(R.id.cardNews)
        viewPager_card.adapter = ViewPagerAdapter(getCardNews(needNutrient)) // 어댑터 생성
        viewPager_card.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
    }


    private fun getCardNews(needNutrient: String?): ArrayList<Int> {
        return when (needNutrient) {
            "탄수화물" -> arrayListOf(
                R.drawable.carbo1,
                R.drawable.carbo2,
                R.drawable.carbo3
            )

            "단백질" -> arrayListOf(
                R.drawable.protein1,
                R.drawable.protein2,
                R.drawable.protein3
            )

            "지방" -> arrayListOf(
                R.drawable.fat1,
                R.drawable.fat2,
                R.drawable.fat3
            )

            "당류" -> arrayListOf(
                R.drawable.sugars1,
                R.drawable.sugars2,
                R.drawable.sugars3
            )

            "나트륨" -> arrayListOf(
                R.drawable.na1,
                R.drawable.na2,
                R.drawable.na3
            )

            else -> arrayListOf() // 기본적으로 빈 리스트 반환
        }
    }
}