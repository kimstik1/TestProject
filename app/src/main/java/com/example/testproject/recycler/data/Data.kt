package com.example.testproject.recycler.data

import com.example.testproject.network.Model

class Data(m: Model) {

    private var id: Int = 0
    private var name: String? = null
    private var eMail: String? = null
    private var website: String? = null
    private var phone: String? = null
    private var posts: List<Posts>? = null

    fun Data(model: Model) {
        id = model.getId()!!
        name = model.getName()!!
        eMail = model.getEmail()!!
        website = model.getWebsite()!!
        phone = model.getPhone()!!
    }


    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun geteMail(): String? {
        return eMail
    }

    fun seteMail(eMail: String?) {
        this.eMail = eMail
    }

    fun getWebsite(): String? {
        return website
    }

    fun setWebsite(website: String?) {
        this.website = website
    }

    fun getPosts(): List<Posts>? {
        return posts
    }

    fun setPosts(posts: List<Posts>?) {
        this.posts = posts
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }
}