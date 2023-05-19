package smu.it.ips2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import smu.it.ips2.model.Restaurant

class SearchRestaurantActivity : AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: ImageButton
    private lateinit var restaurantList: RecyclerView
    private lateinit var restaurantAdapter: RestaurantAdapter

    private lateinit var databaseRef: DatabaseReference
    private lateinit var restaurantQuery: Query

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_restaurant)

        searchEditText = findViewById(R.id.inputRestaurant)
        searchButton = findViewById(R.id.searchBtn)
        restaurantList = findViewById(R.id.restaurantList)

        // Firebase Realtime Database 레퍼런스 초기화
        databaseRef = FirebaseDatabase.getInstance().reference.child("restaurants")

        // RecyclerView 설정
        restaurantList.layoutManager = LinearLayoutManager(this)
        restaurantAdapter = RestaurantAdapter()
        restaurantList.adapter = restaurantAdapter

        searchButton.setOnClickListener {
            val searchQuery = searchEditText.text.toString().trim()

            // 검색어를 사용하여 식당 데이터 검색
            restaurantQuery = databaseRef.orderByChild("name").startAt(searchQuery)
                .endAt(searchQuery + "\uf8ff")
            restaurantQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val restaurantList = mutableListOf<Restaurant>()
                    for (snapshot in dataSnapshot.children) {
                        val restaurant = snapshot.getValue(Restaurant::class.java)
                        restaurant?.let {
                            restaurantList.add(it)
                        }
                    }

                    // 검색 결과를 어댑터에 설정하여 화면에 표시
                    restaurantAdapter.setRestaurants(restaurantList)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // 검색 쿼리 취소 또는 실패 처리
                }
            })
        }

        // 식당 클릭 시 메뉴 검색 화면으로 이동
        restaurantAdapter.setOnRestaurantClickListener { restaurant ->
            // 원하는 동작 수행 (예: 메뉴 검색 화면으로 이동)
        }

        // 식당 즐겨찾기 기능 구현
        restaurantAdapter.setOnBookmarkClickListener { restaurant ->
            // 원하는 동작 수행 (예: 식당 즐겨찾기 상태 변경)
        }
    }

    class RestaurantAdapter(
        private val context: Context,
        private val restaurantList: List<Restaurant>
    ) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false)
            return RestaurantViewHolder(view)
        }

        override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
            val restaurant = restaurantList[position]

            holder.restaurantName.text = restaurant.name
            holder.restaurantAddress.text = restaurant.address

            // 이미지 로딩 및 표시
            holder.restaurantImage.setImageResource(restaurant.imageResId)
        }

        override fun getItemCount(): Int {
            return restaurantList.size
        }

        inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val restaurantImage: ImageView = itemView.findViewById(R.id.restaurantImage)
            val restaurantName: TextView = itemView.findViewById(R.id.restaurantName)
            val restaurantAddress: TextView = itemView.findViewById(R.id.restaurantAddress)
        }
    }
}






// 검색 버튼 : 검색한 식당 결과 아래에 뜨도록

// 식당 클릭하면 메뉴 검색 화면으로 이동

// 식당 즐겨찾기 하면 아이콘 바뀌고 상단에 계속 고정되도록