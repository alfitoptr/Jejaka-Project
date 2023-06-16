package com.jejaka.jejaka_app.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jejaka.jejaka_app.data.RecommendPlacesItem
import com.jejaka.jejaka_app.databinding.FragmentSearchBinding
import com.jejaka.jejaka_app.ui.detail_place.DetailPlaceActivity
import com.jejaka.jejaka_app.ui.home.CardAdapter
import com.jejaka.jejaka_app.ui.home.CardHorizontalAdapter

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var searchViewModel : SearchViewModel

    private val listDataPlace = ArrayList<RecommendPlacesItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvSearch.layoutManager = layoutManager

        searchViewModel.listPlaceTourism.observe(viewLifecycleOwner){
            setListPlace(it)
        }

        searchViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        searchViewModel.getRecommendTourism(
            "sksssaw", "Jakarta"
        )
    }

    private fun setListPlace(placeData: List<RecommendPlacesItem>) {
        val list = ArrayList<RecommendPlacesItem>()
        for (i in placeData) {
            val image = i.image
            val placeName = i.placeName
            val placeAddress = i.placeAddress
            val aveRating = i.aveRating
            val totalReview = i.totalReview
            val predictedRating = i.predictedRating
            val placeId = i.placeId
            val desc = i.desc
            val place = RecommendPlacesItem(image, placeName, placeAddress, aveRating, totalReview, predictedRating, placeId, desc)
            list.add(place)
        }
        listDataPlace.clear()
        listDataPlace.addAll(list)

        showRecyclerList()
    }

    private fun showRecyclerList() {
        val adapter = CardAdapter(listDataPlace)
        binding.rvSearch.adapter = adapter

        adapter.setOnItemClickCallBack(object : CardAdapter.OnItemClickCallback{
            override fun onItemClicked(data: RecommendPlacesItem) {

                val placeId = data.placeId
                val placeName = data.placeName
                val placeAddress = data.placeAddress
                val desc = data.desc
                val aveRating = data.aveRating.toString()

                val intent = Intent(requireContext(), DetailPlaceActivity::class.java)
                intent.putExtra("placeId", placeId)
                intent.putExtra("placeName", placeName)
                intent.putExtra("placeAddress", placeAddress)
                intent.putExtra("desc", desc)
                intent.putExtra("aveRating", aveRating)
                startActivity(intent)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}