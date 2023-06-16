package com.jejaka.jejaka_app.data

import com.google.gson.annotations.SerializedName

data class EmergencyResponse(

	@field:SerializedName("places")
	val places: List<EmergencyPlacesItem>
)

data class EmergencyPlacesItem(

	@field:SerializedName("place_name")
	val placeName: String,

	@field:SerializedName("distance")
	val distance: Any,

	@field:SerializedName("place_address")
	val placeAddress: String,

	@field:SerializedName("ave_rating")
	val aveRating: Any,

	@field:SerializedName("place_category")
	val placeCategory: String,

	@field:SerializedName("total_review")
	val totalReview: Int,

	@field:SerializedName("lat")
	val lat: Any,

	@field:SerializedName("long")
	val long: Any,

	@field:SerializedName("place_id")
	val placeId: String
)
