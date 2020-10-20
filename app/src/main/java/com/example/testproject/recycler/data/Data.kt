package com.example.testproject.recycler.data

import com.example.testproject.network.Model

class Data(m: Model) {

    var name: String
    var eMail: String
    var website: String
    var phone: String

    init {
        name= m.name
        eMail = m.email
        website = m.website
        phone = m.phone
    }
}