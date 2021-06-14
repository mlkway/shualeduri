package com.raywenderlich.login_activit.data.showdetails

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class CreatedBy(
    @Json(name = "name")
    val name: String?,
    @Json(name = "profile_path")
    val profilePath: String?
)