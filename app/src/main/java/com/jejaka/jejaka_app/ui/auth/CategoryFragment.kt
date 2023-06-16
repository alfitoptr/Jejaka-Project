package com.jejaka.jejaka_app.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jejaka.jejaka_app.MainActivity
import com.jejaka.jejaka_app.data.PlacesItem
import com.jejaka.jejaka_app.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var _binding : FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryViewModel: CategoryViewModel

    private val listDataPlace = ArrayList<PlacesItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        categoryViewModel.listPlace.observe(viewLifecycleOwner) {
            setListPlace(it)
        }

        binding.tvSkip.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
            requireActivity().finish()
        }

        binding.btnNext.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
            requireActivity().finish()
        }

        categoryViewModel.submitSuggestions(
            "bsbvsjvbs", 1, 1,1,1,1,1,0,0,1,0,1,1, 0
        )
    }

    // Handle to not back to previously fragment
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //DoNothing
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setListPlace(placeData: List<PlacesItem>) {
        val list = ArrayList<PlacesItem>()
        for (i in placeData) {
            val image = i.image
            val placeName = i.placeName
            val placeAddress = i.placeAddress
            val aveRating = i.aveRating
            val totalReview = i.totalReview
            val predictedRating = i.predictedRating
            val placeId = i.placeId
            val desc = i.desc
            val place = PlacesItem(image, placeName, placeAddress, aveRating, totalReview, predictedRating, placeId, desc)
            list.add(place)
        }
        listDataPlace.clear()
        listDataPlace.addAll(list)

    }

    companion object {

    }

}