package com.jejaka.jejaka_app.ui.emergency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jejaka.jejaka_app.data.RecommendPlacesItem
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.data.EmergencyPlacesItem
import com.jejaka.jejaka_app.databinding.CardBinding
import com.jejaka.jejaka_app.databinding.CardEmergencyBinding

class EmergencyAdapter(private val listPlace: List<EmergencyPlacesItem>) : RecyclerView.Adapter<EmergencyAdapter.ViewHolder>() {


//    private lateinit var onItemClickCallback: OnItemClickCallback
//
//    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }

//    interface OnItemClickCallback {
//        fun onItemClicked(data: EmergencyPlacesItem)
//    }

    class ViewHolder(var binding: CardEmergencyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = listPlace.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardEmergencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (placeName, distance, placeAddress, aveRating, placeCategory, totalReview, lat, long, placeId) = listPlace[position]

            holder.binding.tvPlaceName.text = placeName
            holder.binding.tvAddress.text = placeAddress


//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClicked(listPlace[holder.adapterPosition])
//        }
    }
}