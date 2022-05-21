package com.example.myapplication.domin

import com.example.myapplication.data.MovieRemoteDataSource
import com.example.myapplication.data.MovieRepository


object Container {
    val movieRemoteDataSource = MovieRemoteDataSource()
    val movieRepository = MovieRepository(movieRemoteDataSource)
}