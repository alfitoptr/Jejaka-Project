package com.jejaka.jejaka_app.ui.home

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.data.RecommendPlacesItem
import com.jejaka.jejaka_app.databinding.FragmentHomeBinding
import com.jejaka.jejaka_app.ui.detail_place.DetailPlaceActivity
import com.jejaka.jejaka_app.ui.emergency.EmergencyActivity
import com.jejaka.jejaka_app.ui.maps.MapsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel : HomeViewModel

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val hello = "Hello, "

    private var city = ""

    private val listDataPlace = ArrayList<RecommendPlacesItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

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

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

//        val fragmentManager = parentFragmentManager

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
            btnTourism.setOnClickListener {
                startActivity(
                    Intent(
                        activity, TourismActivity::class.java
                    )
                )
            }
            btnMap.setOnClickListener {
                startActivity(
                    Intent(
                        activity, MapsActivity::class.java
                    )
                )
            }
        }

        val sharedPreferences = context?.getSharedPreferences("LoginSession", Context.MODE_PRIVATE)
        val userId = sharedPreferences?.getString("userId", "")
        val name = sharedPreferences?.getString("name", "")
        val photo = sharedPreferences?.getString("photo", "")

        binding.tvName.text = "$hello $name"
        Glide.with(requireActivity())
            .load(photo)
            .into(binding.ivProfile)

        getMyLocation()

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHome.layoutManager = layoutManager

        homeViewModel.listPlaceTourism.observe(viewLifecycleOwner){
            setListPlace(it)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        homeViewModel.getRecommendTourism(
            "sksssaw", "Jakarta"
        )
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Get the user's last known location
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    // Get the latitude and longitude
                    val lat = location.latitude
                    val lon = location.longitude

                    CoroutineScope(Dispatchers.Main).launch {
                        val address = getAddressFromLocation(lat, lon)
                        binding.tvLocation.text = address

                        city = address

                        val sharedPreferencesLoc = context?.getSharedPreferences("dataLocation", Context.MODE_PRIVATE)
                        val editor = sharedPreferencesLoc?.edit()
                        editor?.putString("lat", lat.toString())
                        editor?.putString("lon", lon.toString())
                        editor?.putString("city", address)
                        editor?.apply()

                        Log.d("Location", "City: $address")

                    }
                }
            }
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private suspend fun getAddressFromLocation(lat: Double, lon: Double): String {
        return withContext(Dispatchers.IO) {
            val geocoder = Geocoder(requireContext())
            val addresses = geocoder.getFromLocation(lat, lon, 1)
            val address = addresses?.getOrNull(0)
            val city = address?.subAdminArea ?: address?.adminArea // Get the city or administrative area
            city.toString()
        }
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
        val adapter = CardHorizontalAdapter(listDataPlace)
        binding.rvHome.adapter = adapter

        adapter.setOnItemClickCallBack(object : CardHorizontalAdapter.OnItemClickCallback{
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