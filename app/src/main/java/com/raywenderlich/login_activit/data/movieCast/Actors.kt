package com.raywenderlich.login_activit.data.movieCast

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Actors<T>(
    @Json(name = "cast")
    val cast: List<Cast>,
    @Json(name = "id")
    val id: Int
)