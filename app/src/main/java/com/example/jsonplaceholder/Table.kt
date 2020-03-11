package com.example.jsonplaceholder

import java.io.Serializable

class Table : Serializable{
    private var id = ""
    private var title = ""
    private var url = ""
    private var thumbnailUrl = ""

    constructor(id: String, title: String, url: String, thumbnailUrl: String) {
        this.id = id
        this.title = title
        this.url = url
        this.thumbnailUrl = thumbnailUrl
    }

    fun getId(): String {
        return id
    }
    fun getTitle(): String {
        return title
    }
    fun getUrl(): String {
        return url
    }
    fun getThumbnailUrl(): String {
        return thumbnailUrl
    }

}