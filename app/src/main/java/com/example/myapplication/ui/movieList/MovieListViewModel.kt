package com.example.myapplication.ui.movieList

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.data.ResultVideo
import com.example.myapplication.model.Movie
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException


class MovieListViewModel(val movieRepository: MovieRepository) : ViewModel() {


    // private val context = getApplication<Application>().applicationContext
    val status = MutableLiveData<ApiStatus>()
    val movieList = MutableLiveData<List<Movie>>()
    val comingSoonMovieList = MutableLiveData<List<Movie>>()
    val searchMovieList = MutableLiveData<List<Movie>>()
    val movieLiveData = MutableLiveData<Movie>()
    val videoList = MutableLiveData<List<ResultVideo>>()


    init {
        //getMovieFromDB()
        //getMovie()
       // comingSoonMovie()
        //setInformation()
        setInformation()
        setMovieInforamtion()
    }

    fun getMovieFromDB() {
        viewModelScope.launch {
            val list = movieRepository.getMovieFromDb()
            movieList.value = list
        }
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


    fun comingSoonMovie() {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            val list = movieRepository.comingSoonMovie()
            comingSoonMovieList.value = list
        }
    }

    fun getMovie() {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            try {
                val list = movieRepository.getMovie()
                movieList.value = list
            } catch (e: SocketTimeoutException) {
                val list = movieRepository.getMovieFromDb()
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

//    private fun checkForInternet(context: Context): Boolean {
//
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            val network = connectivityManager.activeNetwork ?: return false
//
//            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
//
//            return when {
//                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                else -> false
//            }
//        } else {
//            @Suppress("DEPRECATION") val networkInfo =
//                connectivityManager.activeNetworkInfo ?: return false
//            @Suppress("DEPRECATION")
//            return networkInfo.isConnected
//        }
//    }

}
