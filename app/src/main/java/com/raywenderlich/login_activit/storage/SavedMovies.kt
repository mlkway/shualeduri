package com.raywenderlich.login_activit.storage

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "fav")
data class SavedMovies(
    @PrimaryKey
    val id: Int,
    val poster: String?,
    val isMovie: Boolean

):Parcelable {
}