package com.jejaka.jejaka_app.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private var _binding: ActivityAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val fragmentManager = supportFragmentManager
        val signInFragment = SignInFragment()
        val fragment = fragmentManager.findFragmentByTag(SignInFragment::class.java.simpleName)

        if (fragment !is SignInFragment) {
            Log.d(TAG, "Fragment Name :" + SignInFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.auth_frame_container, signInFragment, SignInFragment::class.java.simpleName)
                .commit()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}