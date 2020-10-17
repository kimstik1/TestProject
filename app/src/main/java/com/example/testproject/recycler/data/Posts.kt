package com.example.testproject.recycler.data

class Posts {

    private var title: String? = null
    private var body: String? = null

    fun Posts(title: String?, body: String?) {
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