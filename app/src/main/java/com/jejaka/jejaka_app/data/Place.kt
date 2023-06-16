package com.jejaka.jejaka_app.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("photoUrl")
    val photoUrl: String?,
    @SerializedName("rating")
    val rating: Double?,
): Parcelable
