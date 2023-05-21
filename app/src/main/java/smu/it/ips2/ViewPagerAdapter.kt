package smu.it.ips2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter (var cardNews : ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)) {
        val card = itemView.findViewById<ImageView>(R.id.iv_na)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = cardNews.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.card.setImageResource(cardNews[position])
    }

}