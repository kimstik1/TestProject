package com.example.testproject.network

import com.google.gson.annotations.SerializedName

class Model {

    @SerializedName("id")
    private var id: Int? = null

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("username")
    private var username: String? = null

    @SerializedName("email")
    private var email: String? = null

    @SerializedName("address")
    private var address: Address? = null

    @SerializedName("phone")
    private var phone: String? = null

    @SerializedName("website")
    private var website: String? = null

    @SerializedName("company")
    private var company: Company? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getAddress(): Address? {
        return address
    }

    fun setAddress(address: Address?) {
        this.address = address
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getWebsite(): String? {
        return website
    }

    fun setWebsite(website: String?) {
        this.website = website
    }

    fun getCompany(): Company? {
        return company
    }

    fun setCompany(company: Company?) {
        this.company = company
    }
}