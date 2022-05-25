package com.example.myapplication.data

import com.example.myapplication.data.dataBase.MovieDao
import com.example.myapplication.model.Movie

class MovieRepository(val movieRemoteDataSource: MovieRemoteDataSource, val movieLocalDataSource: MovieLocalDataSource) {
    suspend fun getMovie(): List<Movie> {
        return movieRemoteDataSource.getMovie()
    }

    suspend fun searchMovie(query: String): List<Movie> {
        return movieRemoteDataSource.searchMovie(query)
    }

    suspend fun comingSoonMovie(): List<Movie> {
        return movieRemoteDataSource.comingSoonMovie()
    }

    suspend fun movieDetail(movieId: Int): Movie {
        return movieRemoteDataSource.movieDetail(movieId)
    }

    suspend fun getVideo(movieId: Int): List<ResultVideo> {
        return movieRemoteDataSource.getVideo(movieId)
    }

    suspend fun setMovie() {
        for (i in movieRemoteDataSource.getMovie()) {
       //     movieDao.insert(i)
        }
    }
}