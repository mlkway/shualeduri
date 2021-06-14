package com.raywenderlich.login_activit.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedMovies::class], version = 1)
abstract class FavMoviesDataBase:RoomDatabase() {

    abstract fun savedMoviesDao(): SavedMoviesDao


}