package com.example.myapplication.ui.movieList

import androidx.lifecycle.*
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.data.ResultVideo
import com.example.myapplication.model.Movie
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException


class MovieListViewModel(val movieRepository: MovieRepository) : ViewModel() {

    val status = MutableLiveData<ApiStatus>()
    val movieList = MutableLiveData<List<Movie>>()
    val comingSoonMovieList = MutableLiveData<List<Movie>>()
    val searchMovieList = MutableLiveData<List<Movie?>>()
    val movieLiveData = MutableLiveData<Movie>()
    val videoList = MutableLiveData<List<ResultVideo>>()


    init {
        setInformation()
        setMovieInforamtion()
        setComingSoonInformation()
        setComingSoonMovieInforamtion()
        setVideo()
    }


    fun setInformation() {
        viewModelScope.launch {
            try {
                movieRepository.setMovie()
            } catch (e: Exception) {
                movieRepository.getMovieFromDb()
            }
        }
    }

    fun setMovieInforamtion() {
        viewModelScope.launch {
            try {
                val list = movieRepository.getMovie()
                movieList.value = list
            } catch (e: Exception) {
                val list = movieRepository.getMovieFromDb()
                movieList.value = list
            }
        }
    }

    fun setComingSoonInformation() {
        viewModelScope.launch {
            try {
                movieRepository.setComingSoonMovie()
            } catch (e: Exception) {
                movieRepository.getComingSoonMovieFromDb()
            }
        }
    }

    fun setComingSoonMovieInforamtion() {
        viewModelScope.launch {
            try {
                val list = movieRepository.comingSoonMovie()
                comingSoonMovieList.value = list
            } catch (e: Exception) {
                val list = movieRepository.getComingSoonMovieFromDb()
                comingSoonMovieList.value = list
            }
        }
    }
    fun setVideo(){
        viewModelScope.launch {
            try {
                movieRepository.setMovie()
            } catch (e: Exception) {
                movieRepository.getMovieFromDb()
            }
        }
    }
    fun setVideoInforamtion(id:Int) {
        viewModelScope.launch {
            try {
                val list = movieRepository.getVideo(id)
                videoList.value = list
            } catch (e: Exception) {
                val list = listOf<ResultVideo>(ResultVideo("","","","","",false,"","",0,""))
                videoList.value = list
            }
        }
    }

    fun searchMovieFromDB(title: String) {
        viewModelScope.launch {
            movieRepository.searchMovieFromDB(title)
            val list = movieRepository.searchMovieFromDB(title)
            searchMovieList.value = listOf(list)
        }
    }

    fun searchMovie(query: String) {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            var list = listOf<Movie?>()
            try {
                list = movieRepository.searchMovie(query)
                searchMovieList.value = list

            } catch (e: Exception) {
                var movie = movieRepository.searchMovieFromDB(query)
                list = listOf(movie)
                searchMovieList.value = list

            }
        }
    }

    fun video(id: Int) {
        status.value = ApiStatus.Loading
        viewModelScope.launch {

            movieRepository.getVideo(id)
            videoList.value = movieRepository.getVideo(id)
        }
    }

    fun movieDetail(id: Int) {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            try {
                val movie = movieRepository.movieDetail(id)
                movieLiveData.value = movie
            } catch (e: Exception) {
                val movie = movieRepository.getDetail(id)
                movieLiveData.value = movie
            }
        }
    }

    enum class ApiStatus {
        Loading,
        Done,
        Error
    }


}
