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

class GalleryAdapter(private val clickListener: Listener) :
    RecyclerView.Adapter<GalleryAdapter.CatViewHolder>() {

    val items = mutableListOf<MarsPicture>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_picture_item, null)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(newItems: List<MarsPicture>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    interface Listener {
        fun oItemClicked(picture: MarsPicture)
    }

    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(picture: MarsPicture) {
            itemView.setOnClickListener {
                clickListener.oItemClicked(picture)
            }
            itemView.findViewById<ImageView>(R.id.galleryImageView).load(picture.imageUrl)
            itemView.findViewById<TextView>(R.id.gallery_picture_item_text).text = picture.date
        }
    }
}