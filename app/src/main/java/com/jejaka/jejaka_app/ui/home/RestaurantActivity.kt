package com.jejaka.jejaka_app.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.data.RecommendPlacesItem
import com.jejaka.jejaka_app.databinding.ActivityRestaurantBinding
import com.jejaka.jejaka_app.ui.detail_place.DetailPlaceActivity

class RestaurantActivity : AppCompatActivity() {

    private var _binding: ActivityRestaurantBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel : HomeViewModel

    private val listDataPlace = ArrayList<RecommendPlacesItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRestaurant.layoutManager = layoutManager

        homeViewModel.listPlaceRestaurant.observe(this){
            setListPlace(it)
        }

        homeViewModel.isLoading.observe(this){
            showLoading(it)
        }

        homeViewModel.getRecommendRestaurant(
            "sksssaw", "Jakarta"
        )
    }

    private fun setListPlace(placeData: List<RecommendPlacesItem>) {
        val list = ArrayList<RecommendPlacesItem>()
        for (i in placeData) {
            val image = i.image
            val placeName = i.placeName
            val placeAddress = i.placeAddress
            val aveRating = i.aveRating
            val totalReview = i.totalReview
            val predictedRating = i.predictedRating
            val placeId = i.placeId
            val desc = i.desc
            val place = RecommendPlacesItem(image, placeName, placeAddress, aveRating, totalReview, predictedRating, placeId, desc)
            list.add(place)
        }
        listDataPlace.clear()
        listDataPlace.addAll(list)

        showRecyclerList()
    }

    private fun showRecyclerList() {
        val adapter = CardAdapter(listDataPlace)
        binding.rvRestaurant.adapter = adapter

        adapter.setOnItemClickCallBack(object : CardAdapter.OnItemClickCallback{
            override fun onItemClicked(data: RecommendPlacesItem) {

                val placeId = data.placeId
                val placeName = data.placeName
                val placeAddress = data.placeAddress
                val desc = data.desc
                val aveRating = data.aveRating.toString()

                val intent = Intent(this@RestaurantActivity, DetailPlaceActivity::class.java)
                intent.putExtra("placeId", placeId)
                intent.putExtra("placeName", placeName)
                intent.putExtra("placeAddress", placeAddress)
                intent.putExtra("desc", desc)
                intent.putExtra("aveRating", aveRating)
                startActivity(intent)

            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}