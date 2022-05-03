package com.aemyfiles.testpaging3

import com.squareup.moshi.Json

data class Ad(
    @Json(name = "company")
    val company: String
)
