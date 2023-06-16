package com.jejaka.jejaka_app.ui.emergency

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jejaka.jejaka_app.data.*
import retrofit2.Call
import retrofit2.Response

class EmergencyViewModel : ViewModel() {

    private val _listPlace = MutableLiveData<List<EmergencyPlacesItem>>()
    val listPlace: LiveData<List<EmergencyPlacesItem>> = _listPlace

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getEmergencyPlace(
        lat: Float,
        lon: Float,
        k: Int
    ) {
        _isLoading.value = true
        val dataPlaceRequest = DataEmergencyPlaceRequest(lat, lon, k)

        val call = ApiConfig().getApiServiceEmergency().getEmergency(dataPlaceRequest)
        call.enqueue(object : retrofit2.Callback<EmergencyResponse> {
            override fun onResponse(call: Call<EmergencyResponse>, response: Response<EmergencyResponse>) {
                _isLoading.value = false
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
            override fun onFailure(call: Call<EmergencyResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "EmergencyActivity"
    }
}