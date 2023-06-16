package com.jejaka.jejaka_app.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jejaka.jejaka_app.data.*
import com.jejaka.jejaka_app.ui.home.HomeViewModel
import retrofit2.Call
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _listPlaceTourism = MutableLiveData<List<RecommendPlacesItem>>()
    val listPlaceTourism: LiveData<List<RecommendPlacesItem>> = _listPlaceTourism

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun getRecommendTourism(
        user_id: String,
        city: String
    ) {
        _isLoading.value = true
        val dataRecommendPlaceRequest = DataRecommendPlaceRequest(user_id, city)

        val call = ApiConfig().getApiServiceRecTourism().getRecommendTourism(dataRecommendPlaceRequest)
        call.enqueue(object : retrofit2.Callback<RecommendPlaceResponse> {
            override fun onResponse(call: Call<RecommendPlaceResponse>, response: Response<RecommendPlaceResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val dataPlaceResponse = response.body()
                    if (dataPlaceResponse?.places != null) {
                        _listPlaceTourism.value = dataPlaceResponse.places
                        Log.d(TAG, "onSuccess: ${response.message()}")
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<RecommendPlaceResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}