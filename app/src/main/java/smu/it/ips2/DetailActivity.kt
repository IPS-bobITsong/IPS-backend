package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.firestore.FirebaseFirestore

class DetailActivity : AppCompatActivity() {

    lateinit var viewPager_card: ViewPager2
    lateinit var firestore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val needNutrient = intent.getStringExtra("nutrient")
        findViewById<TextView>(R.id.textViewNutrient).text = needNutrient
        findViewById<TextView>(R.id.textViewNoticeNutrient).text = needNutrient

        val noticeMessage = intent.getStringExtra("needtext")
        if (noticeMessage == "부족해요!") {
            findViewById<TextView>(R.id.moreOrLess).text = "늘려보세요!"
            findViewById<TextView>(R.id.textViewNotice).text = "함량이 많은 메뉴 둘러보기"
        } else if (noticeMessage == "과다해요!") {
            findViewById<TextView>(R.id.moreOrLess).text = "줄여보세요!"
            findViewById<TextView>(R.id.textViewNotice).text = "함량이 적은 메뉴 둘러보기"
        }


        firestore = FirebaseFirestore.getInstance()
        //파이어스토어 메뉴명&영양성분 불러와서 리스트로 접근
        val carbos = ArrayList<CarboList>()
        val proteins = ArrayList<ProteinList>()
        val fats = ArrayList<FatList>()
        val sugars = ArrayList<SugarList>()
        val sodiums = ArrayList<SodiumList>()
        firestore.collection("restaurantBook").addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            for (snapshot in querySnapshot!!.documents) {
                var menuname = snapshot.getString("foodname")
                var carbo = snapshot.getDouble("carbo")
                var protein = snapshot.getDouble("protein")
                var fat = snapshot.getDouble("fat")
                var sugar = snapshot.getDouble("sugar")
                var sodium = snapshot.getDouble("sodium")

                menuname?.let { carbos.add(CarboList(it, carbo)) }
                menuname?.let { proteins.add(ProteinList(it, protein)) }
                menuname?.let { fats.add(FatList(it, fat)) }
                menuname?.let { sugars.add(SugarList(it, sugar)) }
                menuname?.let { sodiums.add(SodiumList(it, sodium)) }

            }
        }

        val sortedCarbo = carbos.sortedByDescending { it.carbo!! }
        val sortedProtein = proteins.sortedByDescending { it.protein!! }
        val sortedFat = fats.sortedByDescending { it.fat!! }
        val sortedSugar = sugars.sortedByDescending { it.sugar!! }
        val sortedSodium = sodiums.sortedByDescending { it.sodium!! }

        if (needNutrient == "탄수화물") {
            findViewById<Button>(R.id.buttonRecommend1).text = sortedCarbo[0].menuname
            findViewById<Button>(R.id.buttonRecommend2).text = sortedCarbo[1].menuname
        }else if (needNutrient == "단백질") {
            findViewById<Button>(R.id.buttonRecommend1).text = sortedProtein[0].menuname
            findViewById<Button>(R.id.buttonRecommend2).text = sortedProtein[1].menuname
        }else if (needNutrient == "지방") {
            findViewById<Button>(R.id.buttonRecommend1).text = sortedFat[0].menuname
            findViewById<Button>(R.id.buttonRecommend2).text = sortedFat[1].menuname
        }else if (needNutrient == "당") {
            findViewById<Button>(R.id.buttonRecommend1).text = sortedSugar[0].menuname
            findViewById<Button>(R.id.buttonRecommend2).text = sortedSugar[1].menuname
        }else if (needNutrient == "나트륨") {
            findViewById<Button>(R.id.buttonRecommend1).text = sortedSodium[0].menuname
            findViewById<Button>(R.id.buttonRecommend2).text = sortedSodium[1].menuname
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
        viewPager_card.adapter = ViewPagerAdapter(getCardNews()) // 어댑터 생성
        viewPager_card.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
    }

    private fun getCardNews(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.na1,
            R.drawable.na2,
            R.drawable.na3
        )
    }
}