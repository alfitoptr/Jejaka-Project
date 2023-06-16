package com.jejaka.jejaka_app.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jejaka.jejaka_app.data.ApiConfig
import com.jejaka.jejaka_app.data.DataPlaceRequest
import com.jejaka.jejaka_app.data.DataPlaceResponse
import com.jejaka.jejaka_app.data.PlacesItem
import retrofit2.Call
import retrofit2.Response

class CategoryViewModel : ViewModel() {

    private val _listPlace = MutableLiveData<List<PlacesItem>>()
    val listPlace: LiveData<List<PlacesItem>> = _listPlace

    fun submitSuggestions(
        user_id: String,
        new_art_gallery: Int,
        new_bakery: Int,
        new_bar: Int,
        new_cafe: Int,
        new_food: Int,
        new_liquor_store: Int,
        new_lodging: Int,
        new_meal_delivery: Int,
        new_meal_takeaway: Int,
        new_night_club: Int,
        new_restaurant: Int,
        new_school: Int,
        new_store: Int
    ) {
        val dataPlaceRequest = DataPlaceRequest(user_id, new_art_gallery, new_bakery, new_bar, new_cafe, new_food, new_liquor_store, new_lodging, new_meal_delivery, new_meal_takeaway, new_night_club, new_restaurant, new_school, new_store)

        val call = ApiConfig().getApiServiceRestaurant().getPlaceRestaurant(dataPlaceRequest)
        call.enqueue(object : retrofit2.Callback<DataPlaceResponse> {
            override fun onResponse(call: Call<DataPlaceResponse>, response: Response<DataPlaceResponse>) {
                if (response.isSuccessful) {
                    val dataPlaceResponse = response.body()
                    if (dataPlaceResponse?.places != null) {
                        _listPlace.value = dataPlaceResponse.places
                        Log.d(TAG, "onSuccess: ${response.message()}")
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DataPlaceResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "AuthActivity"
    }
}