package com.raywenderlich.login_activit.data

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Results(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_title")
    val originalTitle: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "popularity")
    val popularity: Double?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "title")
    val title: String?
)