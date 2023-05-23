package smu.it.ips2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectedRVAdapter(val items : ArrayList<SelectedData>) : RecyclerView.Adapter<SelectedRVAdapter.ViewHolder>() {

    interface ItemCancel {
        fun onClick(view : ImageButton, position: Int)
    }
    var itemCancel : ItemCancel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.seleted_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: SelectedRVAdapter.ViewHolder, position: Int) {
        if (itemCancel != null) {
            holder.itemView.setOnClickListener {v ->
                itemCancel?.onClick(v as ImageButton, position)
            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item : SelectedData) {

            val selectedMenu = itemView.findViewById<TextView>(R.id.menuName)
            //val selectedCount = itemView.findViewById<TextView>(R.id.menuCount)

            selectedMenu.text = item.menu
            //selectedCount.text = item.num.toString()

        }
    }


}