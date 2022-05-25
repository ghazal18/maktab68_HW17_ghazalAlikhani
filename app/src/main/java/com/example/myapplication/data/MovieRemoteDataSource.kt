package com.example.myapplication.data

import com.example.myapplication.model.Movie
import com.example.myapplication.network.ApiService


class MovieRemoteDataSource(val apiService: ApiService) {
    suspend fun getMovie(): List<Movie> {
        return apiService.getMovies().results
    }

    suspend fun searchMovie(query: String): List<Movie> {
        return apiService.searchMovie(query = query).results
    }

    suspend fun comingSoonMovie(): List<Movie> {
        return apiService.comingSoonMovie().results
    }

    suspend fun movieDetail(movieId: Int): Movie {
        return apiService.movieDetail(movieId = movieId)
    }

    suspend fun getVideo(movieId: Int): List<ResultVideo> {
        return apiService.getVideo(movieId = movieId).results
    }

}