package com.example.myapplication.data

import com.example.myapplication.data.ComingSoonDataBase.ComingSoonDao
import com.example.myapplication.data.dataBase.MovieDao
import com.example.myapplication.model.Movie

class MovieLocalDataSource(val movieDao: MovieDao) {
    suspend fun setMovie(movie: Movie) {
        movieDao.insert(movie)
    }
    suspend fun getMovieFromDB(): List<Movie> {
        return movieDao.getPopular()
    }
    suspend fun setComingSoonMovie(movie: Movie) {
        movieDao.insert(movie)
    }
    suspend fun getComingSoonMovieFromDB(): List<Movie> {
        return movieDao.getComingSoon()
    }
    suspend fun searchMovie(title:String): Movie? {
        return movieDao.findMovie(title)
    }
    suspend fun getDetail(id :Int):Movie{
        return movieDao.getDetail(id)
    }


}