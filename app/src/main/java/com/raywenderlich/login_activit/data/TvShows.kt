package com.raywenderlich.login_activit.data

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class TvShows(
    @Json(name = "backdrop_path")
    val backdropPath: Any?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
)