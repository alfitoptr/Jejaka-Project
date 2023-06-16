package com.jejaka.jejaka_app.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jejaka.jejaka_app.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}