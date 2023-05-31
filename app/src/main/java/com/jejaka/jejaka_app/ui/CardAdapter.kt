package com.jejaka.jejaka_app.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jejaka.jejaka_app.MainActivity
import com.jejaka.jejaka_app.data.Place
import com.jejaka.jejaka_app.databinding.CardBinding

class CardAdapter: RecyclerView.Adapter<CardAdapter.PlaceViewHolder>() {
    private val placeData = ArrayList<Place>()

    fun setData(places: ArrayList<Place>) {
        placeData.clear()
        placeData.addAll(places)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlaceViewHolder(
        CardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) =
        holder.bind(placeData[position])

    override fun getItemCount() = placeData.size

    inner class PlaceViewHolder(private val binding: CardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(place.photoUrl)
                    .into(binding.ivPlacePhoto)
                tvPlaceName.text = place.name
                tvRating.text = place.rating.toString()
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MainActivity::class.java)
            }
        }
    }
}