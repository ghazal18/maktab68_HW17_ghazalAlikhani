package com.example.myapplication.network

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

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

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
        @Query("api_key") apiKey: String = API_KEY,
        @Path(value = "movie_id") movieId: Int
    ): Movie


}

object MovieApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}