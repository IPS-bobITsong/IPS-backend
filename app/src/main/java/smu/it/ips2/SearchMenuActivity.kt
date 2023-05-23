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
//    private lateinit var recyclerView2 : RecyclerView

    private lateinit var recyclerViewAdapter : SearchMenuActivity.RecyclerViewAdapter
    private lateinit var recyclerViewAdapter2 : SelectedRVAdapter

    private lateinit var searchQuery: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_menu)

//        // SearchRestaurantActivity.kt에서 resname 값을 전달받음
//        val resname = intent.getStringExtra("resname")

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

        // 메뉴 선택하면 장바구니에 담기도록

        // 취소 버튼 누르면 삭제

        // 선택한 메뉴 정보 받아서
//        findViewById<ImageButton>(R.id.selectBtn).setOnClickListener {
////            intent = Intent(this, CompleteActivity::class.java)
////            startActivity(intent)
//            val selectedMenu = recyclerViewAdapter.getSelectedMenu().firstOrNull()
//
//            if (selectedMenu != null) {
//                val intent = Intent(this, CompleteActivity::class.java)
//                intent.putExtra("selectedMenu", selectedMenu)
//                startActivity(intent)
//            }
//        }

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()
        // RecyclerViewAdapter 초기화
        recyclerView = findViewById(R.id.menuList)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

//        recyclerView2 = findViewById(R.id.selectedMenuList)
//        recyclerViewAdapter2 = SelectedRVAdapter(ArrayList<SelectedData>())
//        recyclerView2.adapter = recyclerViewAdapter2
//        recyclerView2.layoutManager = LinearLayoutManager(this)


//
//        recyclerViewAdapter2.itemCancel = object : SelectedRVAdapter.ItemCancel {
//            override fun onClick(view : ImageButton, position: Int) {
//                // 'X' 버튼 누르면 선택 목록에서 사라지도록 구현해야 함
//            }
//
//        }

//        // RecyclerView 초기화
//        recyclerView = findViewById(R.id.menuList)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // RecyclerViewAdapter 초기화
//        recyclerViewAdapter = RecyclerViewAdapter()
//        recyclerView.adapter = recyclerViewAdapter


//        // 메뉴를 가져와서 어댑터에 설정
//        fetchMenuData(resname)


    }

//    private fun fetchMenuData(resname: String?) {
//        // 여기에서 파이어스토어 또는 원하는 데이터 소스에서 식당의 메뉴를 가져오는 로직을 구현합니다.
//        // 가져온 메뉴 데이터를 RecyclerViewAdapter에 전달하여 표시합니다.
//        // recyclerViewAdapter.updateData(menuDataList)
//        firestore = FirebaseFirestore.getInstance()
//        if (resname != null) {
//            firestore?.collection("foodname")
//                ?.whereEqualTo("resname", resname)
//                ?.addSnapshotListener { querySnapshot, exception ->
//                    if (exception != null) {
//                        // 오류 처리
//                        return@addSnapshotListener
//                    }
//
//                    val menuDataList = ArrayList<MenuBook>()
//
//                    for (document in querySnapshot!!) {
//                        val menu = document.toObject(MenuBook::class.java)
//                        menuDataList.add(menu)
//                    }
//
//                    recyclerViewAdapter.updateData(menuDataList)
//                }
//        }
//    }

//    private fun fetchMenuData(resname: String?) {
//        firestore = FirebaseFirestore.getInstance()
//        if (resname != null) {
//            firestore?.collection("foodname")
//                ?.whereEqualTo("resname", resname)
//                ?.addSnapshotListener { querySnapshot, exception ->
//                    if (exception != null) {
//                        // 오류 처리
//                        return@addSnapshotListener
//                    }
//
//                    val menuDataList = ArrayList<MenuBook>()
//
//                    for (document in querySnapshot!!) {
//                        val menu = document.toObject(MenuBook::class.java)
//                        menuDataList.add(menu)
//                    }
//
//                    recyclerViewAdapter.updateData(menuDataList)
//                }
//        }
//    }
//    private fun fetchMenuData(resname: String?) {
//        if (resname != null) {
//            firestore?.collection("foodname")
//                ?.whereEqualTo("resname", resname)
//                ?.addSnapshotListener { querySnapshot, exception ->
//                    if (exception != null) {
//                        // 오류 처리
//                        return@addSnapshotListener
//                    }
//
//                    val menuDataList = ArrayList<MenuBook>()
//
//                    for (document in querySnapshot!!) {
//                        val menu = document.toObject(MenuBook::class.java)
//                        menuDataList.add(menu)
//                    }
//
//                    recyclerViewAdapter.setData(menuDataList)
//                }
//        }
//    }



    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//        var foodBook: ArrayList<MenuBook> = arrayListOf()
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

                        //val menuDataList = ArrayList<MenuBook>()

                        // ArrayList 비워줌
                        menuDataList.clear()

                        for (document in querySnapshot!!) {
                            val menu = document.toObject(MenuBook::class.java)
                            menuDataList.add(menu)
                        }

                        //recyclerViewAdapter.updateData(menuDataList)
                        notifyDataSetChanged()

                    }
            }

//            firestore?.collection("restaurantbook")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                // ArrayList 비워줌
//                menuDataList.clear()
//
//                for (snapshot in querySnapshot!!.documents) {
//                    var item = snapshot.toObject(MenuBook::class.java)
//                    menuDataList.add(item!!)
//                }
//                notifyDataSetChanged()
//            }
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

//            viewHolder.foodname.text = menuDataList[position].foodname
            //val menu = filteredDataList[position]
            //viewHolder.foodname.text = menu.foodname

            // 메뉴 선택 시 처리 로직 구현
            // 안됨
//            viewHolder.itemView.setOnClickListener {
//                if (!menu.isSelected) ArrayList<SelectedData>().add(SelectedData(menu.foodname!!))
//                menu.isSelected = true
//                notifyDataSetChanged(
//            }
//
//            // 선택한 메뉴 표시 여부 설정
//            if (menu.isSelected) {
//                viewHolder.checkIcon.visibility = View.VISIBLE
//            } else {
//                viewHolder.checkIcon.visibility = View.INVISIBLE
//            }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val foodname: TextView = view.findViewById(R.id.foodName)
            var selectedMenu: MenuBook? = null
//            val checkIcon: ImageView = view.findViewById(R.id.checkIcon)

            // ver2.
//            init {
//                view.setOnClickListener {
//                    val position = adapterPosition
//                    if (position != RecyclerView.NO_POSITION) {
//                        val menu = menuDataList[position]
//                        menu.isSelected = !menu.isSelected
////                        selectedMenu = if (menu.isSelected) menu else null
////                        notifyDataSetChanged()
//                        val intent = Intent(view.context, CompleteActivity::class.java)
//                        intent.putExtra("resname", menu.foodname)
//                        view.context.startActivity(intent)
//                    }
//                }
//            }
//
//            init {
//                view.setOnClickListener {
//                    val position = adapterPosition
//                    if (position != RecyclerView.NO_POSITION) {
//                        val menu = menuDataList[position]
//                        menu.isSelected = !menu.isSelected
//
//                        // 선택한 메뉴 정보를 CompleteActivity로 전달하는 코드 추가
//                        if (menu.isSelected) {
//                            val intent = Intent(view.context, CompleteActivity::class.java)
//                            intent.putExtra("selectedMenu", menu.foodname)
//                            view.context.startActivity(intent)
//                        }
//
//                        notifyDataSetChanged()
//                    }
//                }
//            }

            init {
                view.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val menu = menuDataList[position]
                        Log.d("size", menu.toString())

                        val intent = Intent(view.context, CompleteActivity::class.java)
                        intent.putExtra("resname", menu.foodname)
                        view.context.startActivity(intent)
                    }
                }
            }





//            // 메뉴 선택하면 selectedMenuList(RecyclerView)에 뜨도록 구현해야 함
//            init {
//                view.setOnClickListener {
//                    val position = adapterPosition
//                    if (position != RecyclerView.NO_POSITION) {
//                        val menu = menuDataList[position]
//                        menu.isSelected = !menu.isSelected
////                        updateSelectionUI(menu.isSelected)
//                    }
//                }
//            }

//            private fun updateSelectionUI(selected: Boolean) {
//                if (selected) {
//                    // 체크 아이콘을 보이게 설정하거나 이미지를 변경합니다
//                    checkIcon.visibility = View.VISIBLE
//                    // 또는 checkIcon.setImageResource(R.drawable.checked_icon)
//                } else {
//                    // 체크 아이콘을 숨기게 설정하거나 이미지를 변경합니다
//                    checkIcon.visibility = View.GONE
//                    // 또는 checkIcon.setImageResource(R.drawable.unchecked_icon)
//                }
//            }
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