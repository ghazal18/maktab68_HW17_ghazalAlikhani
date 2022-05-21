package com.example.myapplication.data

import com.example.myapplication.model.Movie

class MovieRepository(val movieRemoteDataSource: MovieRemoteDataSource) {
    suspend fun getMovie(): List<Movie> {
        return movieRemoteDataSource.getMovie()
    }

    suspend fun searchMovie(query:String): List<Movie> {
        return movieRemoteDataSource.searchMovie(query)
    }
    suspend fun comingSoonMovie():List<Movie>{
        return movieRemoteDataSource.comingSoonMovie()
    }
}