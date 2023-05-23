package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class SearchMenuActivity : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null
    private val selectedMenu = mutableListOf<MenuBook>()

    private lateinit var recyclerView : RecyclerView

    private lateinit var recyclerViewAdapter : SearchMenuActivity.RecyclerViewAdapter
    private lateinit var recyclerViewAdapter2 : SelectedRVAdapter

    private lateinit var searchQuery: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_menu)

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            finish()
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
            // 검색 버튼 클릭 시, 사용자의 검색어를 가져와서 searchQuery 변수에 저장
            val searchEditText = findViewById<EditText>(R.id.inputMenu)
            searchQuery = searchEditText.text.toString()

            // 검색어를 사용하여 RecyclerViewAdapter의 filter 메소드 호출
            recyclerViewAdapter.filter(searchQuery)
        }

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()
        // RecyclerViewAdapter 초기화
        recyclerView = findViewById(R.id.menuList)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private var menuDataList: ArrayList<MenuBook> = arrayListOf()
        private var filteredDataList: ArrayList<MenuBook> = arrayListOf()
        init {
            firestore = FirebaseFirestore.getInstance()

            // SearchRestaurantActivity.kt에서 resname 값을 전달받음
            val resname = intent.getStringExtra("resname")

            if (resname != null) {
                Log.d("resname", resname)
                firestore?.collection("restaurantbook")
                    ?.whereEqualTo("resname", resname)
                    ?.addSnapshotListener { querySnapshot, exception ->
                        if (exception != null) {
                            // 오류 처리
                            return@addSnapshotListener
                        }

                        // ArrayList 비워줌
                        menuDataList.clear()

                        for (document in querySnapshot!!) {
                            val menu = document.toObject(MenuBook::class.java)
                            menuDataList.add(menu)
                        }

                        notifyDataSetChanged()

                    }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val viewHolder = holder as ViewHolder
            val menu = menuDataList[position]
            viewHolder.itemView.visibility = View.VISIBLE
            viewHolder.foodname.text = menu.foodname

            menu.isSelected = false
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val foodname: TextView = view.findViewById(R.id.foodName)

            init {
                view.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val menu = menuDataList[position]
                        menu.isSelected = true
                        Log.d("size", menu.toString())
                        val intent = Intent(view.context, CompleteActivity::class.java)
                        intent.putExtra("resname", menu.foodname)
                        view.context.startActivity(intent)
                    }
                }
            }
        }


        override fun getItemCount(): Int {
            Log.d("size", menuDataList.size.toString())
            return menuDataList.size
           // return filteredDataList.size

        }

        // 검색 기능 구현
        fun filter(query: String) {
            val lowerCaseQuery = query.toLowerCase()

            filteredDataList.clear()
            if (lowerCaseQuery.isEmpty()) {
                filteredDataList.addAll(menuDataList)
            } else {
                for (menu in menuDataList) {
                    if (menu.foodname?.toLowerCase()?.contains(lowerCaseQuery) == true) {
                        filteredDataList.add(menu)
                    }
                }
            }

            menuDataList = filteredDataList

            notifyDataSetChanged()
        }

        // 선택된 메뉴 반환
        fun getSelectedMenu(): List<MenuBook> {
            val selectedMenu = ArrayList<MenuBook>()

            for (menu in menuDataList) {
                if (menu.isSelected) {
                    selectedMenu.add(menu)
                }
            }

            return selectedMenu
        }

        // 메뉴 데이터 설정 및 초기화
        fun setData(dataList: ArrayList<MenuBook>) {
            menuDataList = dataList
            filteredDataList.clear()
            filteredDataList.addAll(menuDataList)
            notifyDataSetChanged()
        }

//        // 데이터 업데이트 메소드
//        fun updateData(newMenuDataList: List<MenuBook>) {
//            menuDataList.clear()
//            menuDataList.addAll(newMenuDataList)
//            notifyDataSetChanged()
//        }

    }

}