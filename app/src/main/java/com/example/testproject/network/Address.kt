package com.example.testproject.network

import com.google.gson.annotations.SerializedName

class Address {
    @SerializedName("street")
    private var street: String? = null

    @SerializedName("suite")
    private var suite: String? = null

    @SerializedName("city")
    private var city: String? = null

    @SerializedName("zipcode")
    private var zipcode: String? = null

    @SerializedName("geo")
    private var geo: Geo? = null

    fun getStreet(): String? {
        return street
    }

    fun setStreet(street: String?) {
        this.street = street
    }

    fun getSuite(): String? {
        return suite
    }

    fun setSuite(suite: String?) {
        this.suite = suite
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String?) {
        this.city = city
    }

    fun getZipcode(): String? {
        return zipcode
    }

    fun setZipcode(zipcode: String?) {
        this.zipcode = zipcode
    }

    fun getGeo(): Geo? {
        return geo
    }

    fun setGeo(geo: Geo?) {
        this.geo = geo
    }
}