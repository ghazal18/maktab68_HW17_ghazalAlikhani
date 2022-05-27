package com.example.myapplication.ui.movieList

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.data.ResultVideo
import com.example.myapplication.model.Movie
import kotlinx.coroutines.launch
import java.io.IOException


class MovieListViewModel(val movieRepository: MovieRepository) : ViewModel() {
    val status = MutableLiveData<ApiStatus>()
    val movieList = MutableLiveData<List<Movie>>()
    val comingSoonMovieList = MutableLiveData<List<Movie>>()
    val searchMovieList = MutableLiveData<List<Movie>>()
    val movieLiveData = MutableLiveData<Movie>()
    val videoList = MutableLiveData<List<ResultVideo>>()


    init {
        getMovie()
        comingSoonMovie()
        setInformation()
    }

    fun setInformation() {
        viewModelScope.launch {
            if (isNetworkAvailable1()) {
                movieRepository.setMovie()
            } else {
                movieRepository.getMovieFromDb()
            }
        }
    }

    fun getMovie() {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            try {
                val list = movieRepository.getMovie()
                movieList.value = list
            } catch (e: Exception) {
                val list =
                    listOf(Movie(false, 0, "", "", "", "", 0.0, "", "", "", false, 0.0, 0))
                movieList.value = list
            }

        }
    }

    fun searchMovie(query: String) {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            try {
                val list = movieRepository.searchMovie(query)
                searchMovieList.value = list
            } catch (e: Exception) {
                val list =
                    listOf(Movie(false, 0, "", "", "", "", 0.0, "", "", "", false, 0.0, 0))
                movieList.value = list
            }
        }
    }

    fun comingSoonMovie() {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            val list = movieRepository.comingSoonMovie()
            comingSoonMovieList.value = list
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

            }
        }
    }

    enum class ApiStatus {
        Loading,
        Done,
        Error
    }

    fun isNetworkAvailable1(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return false
    }


}
