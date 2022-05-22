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

    @Query("SELECT COUNT(*) FROM Movie")
    fun getCount(): LiveData<Int>

    @Query("SELECT * FROM Movie")
    fun getAll(): LiveData<List<Movie>>

}