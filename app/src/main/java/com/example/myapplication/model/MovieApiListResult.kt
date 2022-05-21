package com.example.myapplication.model

data class MovieListApiResult(
    val page: Long,
    val results: List<Movie>

)