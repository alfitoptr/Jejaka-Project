package com.jejaka.jejaka_app.ui.detail_place

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.databinding.ActivityDetailPlaceBinding
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

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

        val photoReference = "AZose0lUfIIhjopZUSR_VXwC1_WfiVH7o9Wgiqvj6ZUCAFLsfTJlZey8BqkENyYTz7adn8A0QWeyW4pCLBRXPbyKs_BmjJoOOCDTzrbwdx_bq78O8KllIOJC6-hbZReqe9w_3GYMxGlu76-ab0GZHMHSiZ4E2Txds35vqQdNtmc5IsFX2-HK"

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