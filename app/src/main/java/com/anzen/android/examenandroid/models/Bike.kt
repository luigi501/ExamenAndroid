package com.anzen.android.examenandroid.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Bike (
    @SerializedName("id") val id : Int,
    @SerializedName("district") val district : String,
    @SerializedName("lon") val lon : Double,
    @SerializedName("lat") val lat : Double,
    @SerializedName("bikes") val bikes : Int,
    @SerializedName("slots") val slots : Int,
    @SerializedName("zip") val zip : Int,
    @SerializedName("address") var address : String,
    @SerializedName("addressNumber") val addressNumber : String,
    @SerializedName("nearbyStations") val nearbyStations : String,
    @SerializedName("status") val status : String,
    @SerializedName("name") val name : String,
    @SerializedName("stationType") val stationType : String,
    @Expose(deserialize = false) var distance: Float
)

