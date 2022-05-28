package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dataBase.ComingSoonDao
import com.example.myapplication.data.dataBase.MovieDao
import com.example.myapplication.model.Movie

class MovieLocalDataSource(val movieDao: MovieDao,val movieComingSoon: ComingSoonDao) {
    suspend fun setMovie(movie: Movie) {
        movieDao.insert(movie)
    }
    suspend fun getMovieFromDB(): List<Movie> {
        return movieDao.getAll()
    }
    suspend fun setComingSoonMovie(movie: Movie) {
        movieComingSoon.insert(movie)
    }
    suspend fun getComingSoonMovieFromDB(): List<Movie> {
        return movieComingSoon.getAll()
    }

}