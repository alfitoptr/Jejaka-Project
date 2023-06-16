package com.jejaka.jejaka_app.ui.emergency

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jejaka.jejaka_app.data.EmergencyPlacesItem
import com.jejaka.jejaka_app.databinding.ActivityEmergencyBinding

class EmergencyActivity : AppCompatActivity() {

    private var _binding: ActivityEmergencyBinding? = null
    private val binding get() = _binding!!

    private lateinit var emergencyViewModel: EmergencyViewModel

    private val listDataPlace = ArrayList<EmergencyPlacesItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEmergencyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        emergencyViewModel = ViewModelProvider(this)[EmergencyViewModel::class.java]

        val sharedPreferencesLoc = getSharedPreferences("dataLocation", Context.MODE_PRIVATE)
        val lat = sharedPreferencesLoc?.getString("lat", "")?.toFloat()
        val lon = sharedPreferencesLoc?.getString("lon", "")?.toFloat()

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvEmergency.layoutManager = layoutManager

        emergencyViewModel.listPlace.observe(this){
            setListPlace(it)
        }

        emergencyViewModel.isLoading.observe(this){
            showLoading(it)
        }

        if (lat != null && lon != null) {
            emergencyViewModel.getEmergencyPlace(
                lat, lon, 20
            )
        }

    }

    private fun setListPlace(placeData: List<EmergencyPlacesItem>) {
        val list = ArrayList<EmergencyPlacesItem>()
        for (i in placeData) {
            val placeName = i.placeName
            val distance = i.distance
            val placeAddress = i.placeAddress
            val aveRating = i.aveRating
            val placeCategory = i.placeCategory
            val totalReview = i.totalReview
            val lat = i.lat
            val long = i.long
            val placeId = i.placeId
            val place = EmergencyPlacesItem(placeName, distance, placeAddress, aveRating, placeCategory, totalReview, lat, long, placeId)
            list.add(place)
        }
        listDataPlace.clear()
        listDataPlace.addAll(list)

        showRecyclerList()
    }

    private fun showRecyclerList() {
        val adapter = EmergencyAdapter(listDataPlace)
        binding.rvEmergency.adapter = adapter

//        adapter.setOnItemClickCallBack(object : CardHorizontalAdapter.OnItemClickCallback{
//            override fun onItemClicked(data: RecommendPlacesItem) {
//
//
//
//            }
//        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}