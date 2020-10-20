package com.example.testproject.recycler.data

import com.example.testproject.network.UserPosts

class Posts(p: UserPosts) {

    var title: String
    var body: String

    init {
        title = p.title
        body = p.body
    }
}