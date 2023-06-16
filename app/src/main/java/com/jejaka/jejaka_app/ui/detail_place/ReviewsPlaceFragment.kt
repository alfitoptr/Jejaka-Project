package com.jejaka.jejaka_app.ui.detail_place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.databinding.FragmentReviewsPlaceBinding

class ReviewsPlaceFragment : Fragment() {

    private var _binding: FragmentReviewsPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReviewsPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}