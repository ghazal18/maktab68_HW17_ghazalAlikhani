package com.example.myapplication.ui.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.model.Movie
import kotlinx.coroutines.launch
class MovieListViewModel (val movieRepository: MovieRepository): ViewModel() {
    val status = MutableLiveData<ApiStatus>()
    val movieList = MutableLiveData<List<Movie>>()
    val comingSoonMovieList = MutableLiveData<List<Movie>>()
    val searchMovieList = MutableLiveData<List<Movie>>()
    val movieLiveData = MutableLiveData<Movie>()

    init {
        getMovie()
        comingSoonMovie()
    }

    fun getMovie() {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            val list = movieRepository.getMovie()
            movieList.value = list
        }
    }

    fun searchMovie(query: String) {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            val list = movieRepository.searchMovie(query)
            searchMovieList.value = list
        }
    }

    fun comingSoonMovie() {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            val list = movieRepository.comingSoonMovie()
            comingSoonMovieList.value = list
        }
    }

    fun video() {
        status.value = ApiStatus.Loading
        viewModelScope.launch {

        }
    }

    fun movieDetail(id: Int) {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            try {
                val movie = movieRepository.movieDetail(id)
                movieLiveData.value = movie
            } catch (e: Exception) {

            }
        }
    }

    enum class ApiStatus {
        Loading,
        Done,
        Error
    }
}
