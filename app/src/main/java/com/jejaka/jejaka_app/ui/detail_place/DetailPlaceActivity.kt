package com.jejaka.jejaka_app.ui.detail_place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.databinding.ActivityDetailPlaceBinding

class DetailPlaceActivity : AppCompatActivity() {

    private var _binding: ActivityDetailPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val placeId = intent.getStringExtra("placeId").toString()
        val placeName = intent.getStringExtra("placeName").toString()
        val placeAddress = intent.getStringExtra("placeAddress").toString()
        val desc = intent.getStringExtra("desc").toString()
        val aveRating = intent.getStringExtra("aveRating").toString()

        val fragmentManager = supportFragmentManager
//        val detailPlaceFragment = DetailPlaceFragment()

        val detailPlaceFragment = DetailPlaceFragment.newInstance(placeId, placeName, placeAddress, desc, aveRating)

        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, detailPlaceFragment, DetailPlaceFragment::class.java.simpleName)
            .commit()


        binding.collapsingToolbar.title = placeName
    }


}