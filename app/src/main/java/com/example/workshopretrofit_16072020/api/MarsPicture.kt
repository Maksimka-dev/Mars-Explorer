package com.example.workshopretrofit_16072020.api

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Mars(
    @Json(name = "photos") val photos: List<MarsPicture>
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MarsPicture(
    @Json(name = "id") val id: Int?,
    @Json(name = "img_src") val imageUrl: String?,
    @Json(name = "earth_date") val date: String?,
    @Json(name = "sol") val sol: Int?,
    @Json(name = "camera") val camera: Camera?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Camera(
    @Json(name = "name") val name: String?
) : Parcelable