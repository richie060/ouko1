package com.example.mentalhealth.model

class ResourcesModel {
    var id: String? = null
    var name: String? = null
    var pdfUri: String? = null

    constructor(){
    }

    constructor(id: String?, name: String?, pdfUri: String?) {
        this.id = id
        this.name = name
        this.pdfUri = pdfUri
    }
}