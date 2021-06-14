package com.raywenderlich.login_activit.ui.tvShows

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raywenderlich.login_activit.data.TvShows
import com.raywenderlich.login_activit.network.ApiService

class TvShowsPageSource(private val which:String): PagingSource<Int, TvShows>() {
    override fun getRefreshKey(state: PagingState<Int, TvShows>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShows> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = ApiService.movieService.getTvShows(which,currentLoadingPageKey)
            val responseData = mutableListOf<TvShows>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey -1
            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = if (data.isEmpty()) null else currentLoadingPageKey +1
            )
        }catch (e: Exception){
            return LoadResult.Error(e)
        }
    }
}