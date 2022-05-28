package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dataBase.MovieDao
import com.example.myapplication.model.Movie

class MovieLocalDataSource(val movieDao: MovieDao) {
    suspend fun setMovie(movie: Movie) {
        movieDao.insert(movie)
    }
    suspend fun getMovieFromDB(): List<Movie> {
        return movieDao.getAll()
    }

}