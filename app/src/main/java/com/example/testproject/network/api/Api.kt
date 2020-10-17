package com.example.testproject.network.api

import com.example.testproject.network.Model
import com.example.testproject.network.UserPosts
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("users")
    fun getUsers(): Call<List<Model>>

    @GET("posts")
    fun getPosts(): Call<List<UserPosts>>
}