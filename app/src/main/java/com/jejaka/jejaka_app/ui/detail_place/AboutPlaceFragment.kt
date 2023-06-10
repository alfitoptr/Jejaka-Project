package com.jejaka.jejaka_app.ui.detail_place

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.databinding.FragmentAboutPlaceBinding
import com.jejaka.jejaka_app.ui.emergency.EmergencyActivity

class AboutPlaceFragment : Fragment() {

    private var _binding: FragmentAboutPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAboutPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = parentFragmentManager

        binding.btnEmergency.setOnClickListener {
            val intent = Intent(activity, EmergencyActivity::class.java)
            startActivity(intent)
        }

        binding.btnAddReview.setOnClickListener{
            val addReviewFragment = AddReviewFragment()
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, addReviewFragment, AddReviewFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        binding.btnCulture.setOnClickListener{
            val culturePlaceFragment = CulturePlaceFragment()
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, culturePlaceFragment, CulturePlaceFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }

    companion object {

    }
}