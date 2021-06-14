package com.raywenderlich.login_activit.storage

import androidx.room.*

@Dao
interface SavedMoviesDao {

    @Query("SELECT * FROM fav")
    fun getAll(): List<SavedMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(vararg movies: SavedMovies)

    @Delete
    fun delete(savedMovies: SavedMovies)

}