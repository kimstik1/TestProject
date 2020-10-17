package com.example.testproject.network

import com.google.gson.annotations.SerializedName

class Geo {

    @SerializedName("lat")
    private var lat: String? = null

    @SerializedName("lng")
    private var lng: String? = null

    fun getLat(): String? {
        return lat
    }

    fun setLat(lat: String?) {
        this.lat = lat
    }

    fun getLng(): String? {
        return lng
    }

    fun setLng(lng: String?) {
        this.lng = lng
    }
}