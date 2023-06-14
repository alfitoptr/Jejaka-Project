package com.jejaka.jejaka_app.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.databinding.FragmentHomeBinding
import com.jejaka.jejaka_app.ui.auth.AuthActivity
import com.jejaka.jejaka_app.ui.detail_place.AddReviewFragment
import com.jejaka.jejaka_app.ui.emergency.EmergencyActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val hello = "Hello, "

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val fragmentManager = parentFragmentManager

        homeViewModel.text.observe(viewLifecycleOwner) {
        }

        binding.apply {
            btnEmergency.setOnClickListener {
                startActivity(
                    Intent(
                        activity, EmergencyActivity::class.java
                    )
                )
            }
            btnHotel.setOnClickListener {
                startActivity(
                    Intent(
                        activity, HotelActivity::class.java
                    )
                )
            }
            btnRestaurant.setOnClickListener {
                startActivity(
                    Intent(
                        activity, RestaurantActivity::class.java
                    )
                )
            }
        }

        val sharedPreferences = context?.getSharedPreferences("LoginSession", Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString("name", "")
        val photo = sharedPreferences?.getString("photo", "")

        binding.tvName.text = "$hello $name"
        Glide.with(requireActivity())
            .load(photo)
            .into(binding.ivProfile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}