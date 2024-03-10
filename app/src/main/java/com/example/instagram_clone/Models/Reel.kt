package com.example.instagram_clone.Models

class Reel {
    var reelurl: String = ""
    var caption: String = ""
    var profilelink:String? =null

    constructor()
    constructor(reelurl: String, caption: String) {


        this.caption = caption
        this.reelurl = reelurl
    }

    constructor(reelurl: String, caption: String, profilelink: String) {
        this.reelurl = reelurl
        this.caption = caption
        this.profilelink = profilelink
    }
}