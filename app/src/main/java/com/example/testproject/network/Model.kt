package com.example.testproject.network

import com.google.gson.annotations.SerializedName
//поля для получения из интернета (пользователи)
class Model {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("email")
    var email: String = ""

    @SerializedName("phone")
    var phone: String = ""

    @SerializedName("website")
    var website: String = ""
}