package com.jejaka.jejaka_app.ui.emergency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jejaka.jejaka_app.databinding.ActivityEmergencyBinding

class EmergencyActivity : AppCompatActivity() {

    private var _binding: ActivityEmergencyBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEmergencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}