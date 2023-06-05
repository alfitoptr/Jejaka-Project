package com.jejaka.jejaka_app.ui.detail_place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}