package com.jejaka.jejaka_app.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jejaka.jejaka_app.R

class RestaurantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        supportActionBar?.hide()
    }
}