package com.example.myapplication.data

import com.example.myapplication.model.Movie
import com.example.myapplication.network.MovieApi

class MovieRemoteDataSource {
    suspend fun getMovie():List<Movie>{
        return MovieApi.retrofitService.getMovies().results
    }
    suspend fun searchMovie(query:String):List<Movie>{
        return MovieApi.retrofitService.searchMovie(query = query).results
    }
    suspend fun comingSoonMovie():List<Movie>{
        return MovieApi.retrofitService.comingSoonMovie().results
    }
    suspend fun movieDetail(movieId : Int):Movie{
        return MovieApi.retrofitService.movieDetail(movieId = movieId)
    }
    suspend fun getVideo(movieId: Int):List<Result>{
        return MovieApi.retrofitService.getVideo(movieId = movieId).results
    }

}