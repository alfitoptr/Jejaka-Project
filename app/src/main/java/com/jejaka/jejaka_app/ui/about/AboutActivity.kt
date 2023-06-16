package com.jejaka.jejaka_app.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jejaka.jejaka_app.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.hide()
    }
}