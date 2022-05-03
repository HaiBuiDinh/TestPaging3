package com.aemyfiles.testpaging3

import com.squareup.moshi.Json

data class ApiResponse(
    @Json(name = "ad")
    val ad: Ad
) {
}