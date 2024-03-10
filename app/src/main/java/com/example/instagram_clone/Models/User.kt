package com.example.instagram_clone.Models

class User {
    var image:String?= null
    var email:String?=null
    var name:String?=null
    var password:String?=null
    constructor()
    constructor(image: String?, email: String?, name: String?, password: String?) {
        this.image = image
        this.email = email
        this.name = name
        this.password = password
    }

    constructor(email: String?, name: String?, password: String?) {
        this.email = email
        this.name = name
        this.password = password
    }

    constructor(email: String?, password: String?) {
        this.email = email
        this.password = password
    }


}