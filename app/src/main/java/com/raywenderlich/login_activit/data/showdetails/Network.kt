package com.raywenderlich.login_activit.data.showdetails

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Network(
    @Json(name = "logo_path")
    val logoPath: String,
    @Json(name = "name")
    val name: String
)