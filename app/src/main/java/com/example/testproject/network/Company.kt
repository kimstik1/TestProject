package com.example.testproject.network

import com.google.gson.annotations.SerializedName

class Company {

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("catchPhrase")
    private var catchPhrase: String? = null

    @SerializedName("bs")
    private var bs: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCatchPhrase(): String? {
        return catchPhrase
    }

    fun setCatchPhrase(catchPhrase: String?) {
        this.catchPhrase = catchPhrase
    }

    fun getBs(): String? {
        return bs
    }

    fun setBs(bs: String?) {
        this.bs = bs
    }
}