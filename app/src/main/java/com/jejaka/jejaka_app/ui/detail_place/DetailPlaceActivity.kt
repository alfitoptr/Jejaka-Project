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

        val fragmentManager = supportFragmentManager
        val detailPlaceFragment = DetailPlaceFragment()

        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, detailPlaceFragment, DetailPlaceFragment::class.java.simpleName)
            .commit()

        val photoReference = "AZose0nl7Dm278M4gGQVMQn7CCBXnrILc8U0fUYMHl0FvxxT3ktw-zotUKmTMfak2Tak74rTb4SfwZejox2sDgwmOUzEAna5DQzNf5K_qhsPaI33IdsHmuWAQaHxvsRrLt-GWOqxH8YZdDXIcmUO5JHDncf6KSYoY5-KFhFKat9VSPyNdSXn"

        // Replace YOUR_API_KEY with your Google Places API key
        val apiKey = "AIzaSyD-MrJDjEXOL91PkVsgcBk0FQ4VyGmWS0A"

        // Construct the photo URL
        val photoUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=$photoReference&key=$apiKey"


        displayPhoto(photoReference, 400, apiKey)
    }

    fun displayPhoto(photoReference: String, maxWidth: Int, apiKey: String) {
        val url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=$maxWidth&photoreference=$photoReference&key=$apiKey"

        Glide.with(this)
            .load(url)
            .into(binding.ivDetailBackground)
    }

}