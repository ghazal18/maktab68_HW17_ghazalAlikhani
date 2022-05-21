package com.example.myapplication.ui.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domin.Container
import com.example.myapplication.model.Movie
import kotlinx.coroutines.launch

class MovieListViewModel:ViewModel() {
    val status = MutableLiveData<ApiStatus>()
    val movieList = MutableLiveData<List<Movie>>()
    val comingSoonMovieList = MutableLiveData<List<Movie>>()
    val searchMovieList = MutableLiveData<List<Movie>>()

    init {
        getMovie()
        comingSoonMovie()
    }
    fun getMovie(){
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            val list = Container.movieRepository.getMovie()
            movieList.value = list
        }
    }
    fun searchMovie(query :String){
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            val list = Container.movieRepository.searchMovie(query)
            searchMovieList.value = list
        }
    }
    fun comingSoonMovie(){
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            val list = Container.movieRepository.comingSoonMovie()
            comingSoonMovieList.value = list
        }
    }
}
enum class ApiStatus{
    Loading,
    Done,
    Error
}
