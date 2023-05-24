package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class SearchRestaurantActivity() : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null

    private lateinit var recyclerView: RecyclerView
//    private lateinit var recyclerViewAdapter: SearchRestaurantActivity.RecyclerViewAdapter

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

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        recyclerView = findViewById(R.id.restaurantList)
        recyclerView.adapter= RecyclerViewAdapter()
        recyclerView.layoutManager= LinearLayoutManager(this)

        var searchOption = "resname"

        // 검색 옵션에 따라 검색
        // 검색한 식당 결과 아래에 뜨도록
        findViewById<ImageButton>(R.id.searchBtn).setOnClickListener {
            val searchWordEditText: EditText = findViewById(R.id.inputRestaurant)
            val searchWord = searchWordEditText.text.toString()
            (recyclerView.adapter as RecyclerViewAdapter).search(searchWord, searchOption)
        }

    }
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private val storeBook: ArrayList<RestaurantBook> = arrayListOf()
        private val uniqueResNames: HashSet<String> = hashSetOf()

        init {
            // 파이어스토어 인스턴스 초기화
            firestore = FirebaseFirestore.getInstance()

            // storeBook의 문서를 불러온 뒤 RestaurantBook으로 변환해 ArrayList에 담음
            firestore?.collection("restaurantbook")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                storeBook.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(RestaurantBook::class.java)
                    storeBook.add(item!!)
                }
                notifyDataSetChanged()
            }
        }

        // xml파일을 inflate하여 ViewHolder를 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false)
            return ViewHolder(view)
        }

        // ViewHolder 클래스 내부에 클릭 이벤트 처리를 추가합니다.
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val resname: TextView = view.findViewById(R.id.restaurantName)

            init {
                view.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val restaurantBook = storeBook[position]
                        val intent = Intent(view.context, SearchMenuActivity::class.java)
                        intent.putExtra("resname", restaurantBook.resname)
                        view.context.startActivity(intent)
                    }
                }
            }
        }

        // onCreateViewHolder에서 만든 view와 실제 데이터를 연결 (가맹점이름 중복일 경우, 숨김 처리)
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val viewHolder = holder as ViewHolder
            val currentRestaurant = storeBook[position]
            if (position > 0 && currentRestaurant.resname == storeBook[position - 1].resname) {
                viewHolder.itemView.layoutParams.height = 0 // 숨겨진 칸 높이 0으로 설정하여 공백 없도록
                viewHolder.itemView.visibility = View.GONE
            } else {
                viewHolder.itemView.visibility = View.VISIBLE
                viewHolder.resname.text = currentRestaurant.resname
            }
        }


        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return storeBook.size
        }

        // 데이터 업데이트 메소드
        fun updateData(newStoreBook: ArrayList<RestaurantBook>) {
            storeBook.clear()
            storeBook.addAll(newStoreBook)
            notifyDataSetChanged()
        }

        // 파이어스토어에서 데이터를 불러와서 검색어가 있는지 판단
        fun search(searchWord: String, searchOption: String) {
            val searchWordLowerCase = searchWord.toLowerCase().replace("\\s".toRegex(), "") // 검색어를 소문자로 변환하고 공백 제거

            firestore?.collection("restaurantbook")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                storeBook.clear()

                for (snapshot in querySnapshot!!.documents) {
                    val value = snapshot.getString(searchOption)?.toLowerCase() // 데이터를 소문자로 변환
                    if (value != null && value.replace("\\s".toRegex(), "").contains(searchWordLowerCase)) { // 소문자로 변환된 검색어와 데이터를 비교하고 공백 제거
                        val item = snapshot.toObject(RestaurantBook::class.java)
                        storeBook.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
        }


    }
}