package com.raywenderlich.login_activit.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

class HomeViewModel : ViewModel() {

    var popularData = Pager(PagingConfig(pageSize = 20)){
        MoviePageSource("popular")
    }.liveData


    var topRated = Pager(PagingConfig(pageSize = 20)){
        MoviePageSource("top_rated")
    }.liveData

    var upcoming = Pager(PagingConfig(pageSize = 20)){
        MoviePageSource("upcoming")
    }.liveData

    var nowPlaying = Pager(PagingConfig(pageSize = 20)){
        MoviePageSource("now_playing")
    }.liveData

}