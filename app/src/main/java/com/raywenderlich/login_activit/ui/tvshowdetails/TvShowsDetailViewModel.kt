package com.raywenderlich.login_activit.ui.tvshowdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raywenderlich.login_activit.data.showdetails.DetailTvShows
import com.raywenderlich.login_activit.network.ApiService
import com.raywenderlich.login_activit.storage.DataInit
import com.raywenderlich.login_activit.storage.SavedMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowsDetailViewModel : ViewModel() {

    private var _tvShowDetails = MutableLiveData<DetailTvShows>()

    val tvShowDetails: LiveData<DetailTvShows> get() = _tvShowDetails

    private var _savedMovies = MutableLiveData<List<SavedMovies>>()
    val savedMovies: LiveData<List<SavedMovies>>  get() = _savedMovies

    private var _exsistent = MutableLiveData<Boolean>()
    val exsistent : LiveData<Boolean> get() = _exsistent

    private var _currentMovie = MutableLiveData<SavedMovies>()
    val currentMovie: LiveData<SavedMovies> get() = _currentMovie


    fun checker(movies: SavedMovies){
        getMoviesFromDataBase()
        _currentMovie.postValue(movies)
        _exsistent.postValue(savedMovies.value != null && savedMovies.value!!.contains(movies))

    }

    fun getMoviesFromDataBase(){
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO){
                DataInit.db.savedMoviesDao().getAll()

            }
            _savedMovies.postValue(data)
            currentMovie.value?.let { checker(it) }
        }
    }

    fun addMovies(savedMovies: SavedMovies){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                DataInit.db.savedMoviesDao().insertMovie(savedMovies)
                getMoviesFromDataBase()
            }

            checker(currentMovie.value!!)
        }
    }

    fun deleteMovies(savedMovies: SavedMovies){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                DataInit.db.savedMoviesDao().delete(savedMovies)
                getMoviesFromDataBase()
            }
            checker(currentMovie.value!!)

        }
    }


    fun loadShow(id: Int){

        viewModelScope.launch {
            val data = withContext(Dispatchers.IO){
                ApiService.movieService.getDetailTvShows(id)
            }
            _tvShowDetails.postValue(data.body())
        }

    }


}