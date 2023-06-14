package com.jejaka.jejaka_app.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException

data class DataPlaceRequest(
    val user_id: String,
    val new_art_gallery: Int,
    val new_bakery: Int,
    val new_bar: Int,
    val new_cafe: Int,
    val new_food: Int,
    val new_liquor_store: Int,
    val new_lodging: Int,
    val new_meal_delivery: Int,
    val new_meal_takeaway: Int,
    val new_night_club: Int,
    val new_restaurant: Int,
    val new_school: Int,
    val new_store: Int
)

interface ApiService {

    @POST("/predict-restaurant")
    fun dataPlace(
        @Body request: DataPlaceRequest
    ): Call<DataPlaceResponse>

}

class ApiConfig {
    fun getApiService(): ApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://getpredictrestaurant-5dwnbiqvcq-et.a.run.app")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

private class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestBuilder: Request.Builder = originalRequest.newBuilder()
            .header("Content-Type", "application/json")
            .method(originalRequest.method, originalRequest.body)
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}