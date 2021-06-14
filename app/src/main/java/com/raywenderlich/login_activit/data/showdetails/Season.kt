package com.raywenderlich.login_activit.data.showdetails

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Season(
    @Json(name = "name")
    val name: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
)