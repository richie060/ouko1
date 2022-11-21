package com.example.mentalhealth.model

class Story {
    var name: String? = null
    var email: String? = null
    var feedback: String? = null

    constructor(){}

    constructor(name: String,email: String, feedback:String ){
        this.name= name
        this.email = email
        this.feedback = feedback
    }




}