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

        val placeId = arguments?.getString("placeId").toString()
        val placeName = arguments?.getString("placeName").toString()
        val placeAddress = arguments?.getString("placeAddress").toString()
        val desc = arguments?.getString("desc").toString()
        val aveRating = arguments?.getString("aveRating").toString()


        val fragmentManager = parentFragmentManager

        binding.apply {
            btnAddReview.setOnClickListener{
                val addReviewFragment = AddReviewFragment()
                fragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, addReviewFragment, AddReviewFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            }

            tvAddress.text = placeAddress
            tvDescription.text = desc
        }

    }

    companion object {
        fun newInstance(placeId: String, placeName: String, placeAddress: String, desc: String, aveRating: String): AboutPlaceFragment {
            val fragment = AboutPlaceFragment()
            val args = Bundle()
            args.putString("placeId", placeId)
            args.putString("placeName", placeName)
            args.putString("placeAddress", placeAddress)
            args.putString("desc", desc)
            args.putString("aveRating", aveRating)
            fragment.arguments = args
            return fragment
        }
    }

}