package com.jejaka.jejaka_app.data

import com.google.gson.annotations.SerializedName

data class RecommendPlaceResponse(

	@field:SerializedName("places")
	val places: List<RecommendPlacesItem>
)

data class RecommendPlacesItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("place_name")
	val placeName: String,

	@field:SerializedName("place_address")
	val placeAddress: String,

	@field:SerializedName("ave_rating")
	val aveRating: Any,

	@field:SerializedName("total_review")
	val totalReview: Int,

	@field:SerializedName("Predicted_Rating")
	val predictedRating: Any,

	@field:SerializedName("place_id")
	val placeId: String,

	@field:SerializedName("desc")
	val desc: String
)
