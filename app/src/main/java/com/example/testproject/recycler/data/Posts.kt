package com.example.testproject.recycler.data

import com.example.testproject.network.UserPosts

class Posts(p: UserPosts) {

    private var title: String? = null
    private var body: String? = null

    fun Posts(p: UserPosts) {
        this.title = title
        this.body = body
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getBody(): String? {
        return body
    }

    fun setBody(body: String?) {
        this.body = body
    }
}