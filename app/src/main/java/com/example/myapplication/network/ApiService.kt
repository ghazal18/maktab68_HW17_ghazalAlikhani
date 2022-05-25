package com.example.myapplication.network

import com.example.myapplication.data.Video
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieListApiResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "8615e332ad100989dfaaba4d95fa88c7"
const val POSTER_PATH = "https://image.tmdb.org/t/p/w500"
const val YOUTUBE_URL = " youtube.com/watch?v="




interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") key: String = API_KEY,
        @Query("page") page: Int = 1
    ): MovieListApiResult

    @GET("movie/upcoming")
    suspend fun comingSoonMovie(
        @Query("api_key") key: String = API_KEY,
        @Query("page") page: Int = 1
    ): MovieListApiResult

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String
    ): MovieListApiResult

    @GET("movie/{movie_id}")
    suspend fun movieDetail(
        @Path(value = "movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Movie

    @GET("movie/{movie_id}/videos")
    suspend fun getVideo(
        @Path(value = "movie_id")movieId:Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Video


}

