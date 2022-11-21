package com.example.mentalhealth.model

class Song_info {

    var Title: String? = null
    var Author: String? = null
    var SongUrl: String? = null

    constructor(Title:String, Author:String, SongUrl: String){
        this.Title = Title
        this.Author = Author
        this.SongUrl = SongUrl.toString()
    }

}