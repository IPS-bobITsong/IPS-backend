package smu.it.ips2

import android.annotation.SuppressLint
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SearchMenuActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore

    private lateinit var recyclerView : RecyclerView

    private lateinit var recyclerViewAdapter : SearchMenuActivity.RecyclerViewAdapter


    @SuppressLint("MissingInflatedId")
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

        var selectedRes = findViewById<TextView>(R.id.selected)
        selectedRes.text = intent.getStringExtra("resname")


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

            private var auth: FirebaseAuth = FirebaseAuth.getInstance()
            init {
                view.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val menu = menuDataList[position]
                        menu.isSelected = true
                        Log.d("size", menu.toString())
                        val intent = Intent(view.context, CompleteActivity::class.java)
                        intent.putExtra("foodname", menu.foodname)
                        intent.putExtra("carbo", menu.carbo)
                        intent.putExtra("protein", menu.protein)
                        intent.putExtra("fat", menu.fat)
                        intent.putExtra("sugars", menu.sugars)
                        intent.putExtra("sodium", menu.sodium)
                       // intent.putExtra("resname", menu.foodname)
                        view.context.startActivity(intent)


                        val currentUser = auth.currentUser
                        val userId = currentUser?.uid
                        // 파이어스토어 인스턴스 초기화
                        firestore = FirebaseFirestore.getInstance()

                        // 사용자별 컬렉션에 데이터 저장
                        firestore.collection("users").document(userId.toString())
                            .collection("menubook")
                            .add(menu)
                            .addOnSuccessListener { documentReference ->
                                Log.d("SearchMenuActivity", "DocumentSnapshot added with ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.w("SearchMenuActivity", "Error adding document", e)
                            }
                    }
                }
            }
        }


        override fun getItemCount(): Int {
            Log.d("size", menuDataList.size.toString())
            return menuDataList.size
           // return filteredDataList.size

        }


    }

}