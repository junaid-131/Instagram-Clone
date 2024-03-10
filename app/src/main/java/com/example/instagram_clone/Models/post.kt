package com.example.instagram_clone.Models

class post {
    var posturl: String = ""
    var caption: String = ""
    var uid: String = ""
    var time: String = ""

    constructor()
    constructor(posturl: String, caption: String) {
        this.caption = caption
        this.posturl = posturl

    }

    constructor(posturl: String, caption: String, uid: String, time: String) {
        this.posturl = posturl
        this.caption = caption
        this.uid = uid
        this.time = time
    }

}