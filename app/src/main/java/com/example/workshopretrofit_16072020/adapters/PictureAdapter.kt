package com.example.workshopretrofit_16072020.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.workshopretrofit_16072020.R
import com.example.workshopretrofit_16072020.api.MarsPicture

class PictureAdapter(private val clickListener: Listener) :
    RecyclerView.Adapter<PictureAdapter.CatViewHolder>() {

    val items = mutableListOf<MarsPicture>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.picture_item, null)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(items[position])
        if (position == (itemCount - 1)) {
            clickListener.onListScrolled(items[position].date!!)
        }
    }

    fun clearList() {
        items.clear()
    }

    fun addItems(newItems: List<MarsPicture>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    interface Listener {
        fun oItemClicked(position: Int)
        fun onListScrolled(date: String)
    }

    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(picture: MarsPicture) {
            itemView.setOnClickListener {
                clickListener.oItemClicked(adapterPosition)
            }
            itemView.findViewById<TextView>(R.id.camera_name).text = picture.camera?.name
            itemView.findViewById<TextView>(R.id.picture_name).text = picture.id.toString()
            itemView.findViewById<TextView>(R.id.date).text = picture.date
            itemView.findViewById<ImageView>(R.id.picture_mars).load(picture.imageUrl)
        }
    }
}