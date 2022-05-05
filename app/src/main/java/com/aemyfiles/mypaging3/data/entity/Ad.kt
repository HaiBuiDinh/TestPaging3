package com.example.mypaging3.data.entity

import com.squareup.moshi.Json

data class Ad(
    @Json(name = "text")
    val text: String,
    @Json(name = "url")
    val url: String
) {
}