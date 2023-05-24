package smu.it.ips2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CheckDietActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
//    private lateinit var checkDietAdapter: CheckDietAdapter
    private lateinit var firestore: FirebaseFirestore
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_diet)

//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        checkDietAdapter = CheckDietAdapter()
//        recyclerView.adapter = checkDietAdapter
//
//        firestore = FirebaseFirestore.getInstance()

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.checkBtn).setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
//        fetchMenuData()
    }
//    private fun fetchMenuData() {
//        val currentUser = auth.currentUser
//        val userId = currentUser?.uid
//
//        firestore.collection("users").document(userId.toString())
//            .collection("menubook")
//            .get()
//            .addOnSuccessListener { querySnapshot ->
//                val menuList = mutableListOf<MenuBook>()
//                for (documentSnapshot in querySnapshot) {
//                    val menu = documentSnapshot.toObject(MenuBook::class.java)
//                    menuList.add(menu)
//                }
//                checkDietAdapter.setData(menuList)
//            }
//            .addOnFailureListener { e ->
//                // 데이터 가져오기 실패 시 처리할 내용
//            }
//    }
}