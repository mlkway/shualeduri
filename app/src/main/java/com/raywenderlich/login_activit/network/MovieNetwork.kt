package com.raywenderlich.login_activit.network

import com.raywenderlich.login_activit.data.Movie
import com.raywenderlich.login_activit.data.Results
import com.raywenderlich.login_activit.data.TvShows
import com.raywenderlich.login_activit.data.movieCast.Actors
import com.raywenderlich.login_activit.data.movieCast.Cast
import com.raywenderlich.login_activit.data.movieDetails.MovieDetails
import com.raywenderlich.login_activit.data.showdetails.DetailTvShows
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieNetwork {

    @GET("movie/{which}?api_key=f4a1da4ca928266cf21c866ca0cc7148")
    suspend fun getMovie(@Path("which")which: String, @Query("page")page:Int): Response<Movie<List<Results>>>


    @GET("tv/{which}?api_key=f4a1da4ca928266cf21c866ca0cc7148")
    suspend fun getTvShows(@Path("which")which: String, @Query("page")page: Int): Response<Movie<List<TvShows>>>

    @GET("movie/{id}?api_key=f4a1da4ca928266cf21c866ca0cc7148")
    suspend fun getDetailMovie(@Path("id")id: Int): Response<MovieDetails>

    @GET("tv/{id}?api_key=f4a1da4ca928266cf21c866ca0cc7148")
    suspend fun getDetailShows(@Path("id")id: Int): Response<MovieDetails>


    @GET("movie/{id}/credits?api_key=f4a1da4ca928266cf21c866ca0cc7148")
    suspend fun moveiCast(@Path("id")id: Int):Response<Actors<List<Cast>>>

    @GET("tv/{id}?api_key=f4a1da4ca928266cf21c866ca0cc7148")
    suspend fun getDetailTvShows(@Path("id")id: Int):Response<DetailTvShows>



}