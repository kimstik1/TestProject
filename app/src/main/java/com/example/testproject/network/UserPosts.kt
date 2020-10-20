package com.example.testproject.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserPosts {

    @SerializedName("userId")
    var userId: Int = 0

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String = ""

    @SerializedName("body")
    var body: String = ""
}