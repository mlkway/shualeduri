package com.raywenderlich.login_activit.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raywenderlich.login_activit.data.Results
import com.raywenderlich.login_activit.network.ApiService
import java.lang.Exception

class MoviePageSource(private val which:String) : PagingSource<Int, Results>() {

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = ApiService.movieService.getMovie(which,currentLoadingPageKey)
            val responseData = mutableListOf<Results>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey -1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = if (data.isEmpty()) null else currentLoadingPageKey +1
            )
        }catch (e : Exception){
            return LoadResult.Error(e)
        }
    }
}