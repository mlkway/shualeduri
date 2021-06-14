package com.raywenderlich.login_activit.storage

import android.content.Context
import androidx.room.Room
import java.lang.RuntimeException

object DataInit {

    private var dataBase: FavMoviesDataBase? = null

    val db get() = dataBase ?: throw RuntimeException("not initialized")

    fun initialize(context: Context){
        dataBase = Room.databaseBuilder(context,FavMoviesDataBase::class.java,"fav").build()
    }

}