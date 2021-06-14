package com.raywenderlich.login_activit.data.showdetails

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class DetailTvShows(
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "created_by")
    val createdBy: List<CreatedBy?>,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "networks")
    val networks: List<Network?>,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "seasons")
    val seasons: List<Season?>,
)