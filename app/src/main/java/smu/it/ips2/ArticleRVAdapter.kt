package smu.it.ips2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleRVAdapter(val items : ArrayList<ArticleData>) : RecyclerView.Adapter<ArticleRVAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(view : View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_quiz_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ArticleRVAdapter.ViewHolder, position: Int) {
        if (itemClick != null) {
            holder.itemView.setOnClickListener {v ->
                itemClick?.onClick(v, position)
            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item : ArticleData) {

            val articleName = itemView.findViewById<TextView>(R.id.articleName)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val articleNum = itemView.findViewById<TextView>(R.id.articleNum)

            articleName.text = item.title
            imageViewArea.setImageResource(item.imageUrl)
            articleNum.text = item.num

        }
    }


}