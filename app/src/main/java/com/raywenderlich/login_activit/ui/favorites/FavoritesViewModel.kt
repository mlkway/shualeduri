package com.raywenderlich.login_activit.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raywenderlich.login_activit.storage.DataInit
import com.raywenderlich.login_activit.storage.SavedMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel : ViewModel() {
    private var _favMoviesList = MutableLiveData<List<SavedMovies>>()
    val favMoviesList: LiveData<List<SavedMovies>> get() = _favMoviesList

    fun getDatabase(){
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO){
                DataInit.db.savedMoviesDao().getAll()
            }
            _favMoviesList.postValue(data)
        }
    }
}