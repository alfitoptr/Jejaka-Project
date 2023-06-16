package com.jejaka.jejaka_app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jejaka.jejaka_app.data.RecommendPlacesItem
import com.jejaka.jejaka_app.databinding.CardHorizontalBinding
import com.jejaka.jejaka_app.R

class CardHorizontalAdapter(private val listPlace: List<RecommendPlacesItem>) : RecyclerView.Adapter<CardHorizontalAdapter.ViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: RecommendPlacesItem)
    }

    class ViewHolder(var binding: CardHorizontalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = listPlace.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (image, placeName, placeAddress, aveRating, totalReview, predictedRating, placeId, desc) = listPlace[position]

        if (image == "Belum Ada Foto") {
            holder.binding.ivPlace.setImageResource(R.drawable.raja_ampat)
            holder.binding.tvPlaceName.text = placeName
            holder.binding.tvRating.text = aveRating.toString()
        } else {
            Glide.with(holder.itemView.context)
                .load(image)
                .into(holder.binding.ivPlace)
            holder.binding.tvPlaceName.text = placeName
            holder.binding.tvRating.text = aveRating.toString()
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listPlace[holder.adapterPosition])
        }
    }
}