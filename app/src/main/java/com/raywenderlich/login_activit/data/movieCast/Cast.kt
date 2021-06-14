package com.raywenderlich.login_activit.data.movieCast

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Cast(
    @Json(name = "character")
    val character: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "profile_path")
    val profilePath: String?
)