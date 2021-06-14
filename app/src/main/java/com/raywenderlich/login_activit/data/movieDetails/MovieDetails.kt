package com.raywenderlich.login_activit.data.movieDetails

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class MovieDetails(
    @Json(name = "budget")
    val budget: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_title")
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany?>,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "tagline")
    val tagline: String?,
    @Json(name = "title")
    val title: String?
)