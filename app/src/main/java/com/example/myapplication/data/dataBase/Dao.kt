package com.example.myapplication.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun deleteDB(movie:Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movie: Movie)

    @Query("SELECT * FROM Movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM Movie WHERE title = :title")
    suspend fun findMovie(title:String):Movie?

    @Query("SELECT * FROM Movie WHERE Classification = :ComingSoon")
    suspend fun getComingSoon(ComingSoon:String = "ComingSoon"): List<Movie>

    @Query("SELECT * FROM Movie WHERE Classification = :Popular")
    suspend fun getPopular(Popular:String = "Popular"): List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :ID")
    suspend fun getDetail(ID :Int): Movie



}