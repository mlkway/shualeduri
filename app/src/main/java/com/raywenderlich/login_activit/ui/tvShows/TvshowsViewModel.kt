package com.raywenderlich.login_activit.ui.tvShows

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

class TvshowsViewModel : ViewModel() {
    var popularData = Pager(PagingConfig(pageSize = 20)){
        TvShowsPageSource("popular")
    }.liveData

    var topRatedData = Pager(PagingConfig(pageSize = 20)){
        TvShowsPageSource("top_rated")
    }.liveData

}