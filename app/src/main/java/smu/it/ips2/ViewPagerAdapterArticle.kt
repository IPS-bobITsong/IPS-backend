package smu.it.ips2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapterArticle (var articles : ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapterArticle.PagerViewHolder>() {

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)) {
        val article = itemView.findViewById<ImageView>(R.id.iv_article1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.article.setImageResource(articles[position])
    }

}