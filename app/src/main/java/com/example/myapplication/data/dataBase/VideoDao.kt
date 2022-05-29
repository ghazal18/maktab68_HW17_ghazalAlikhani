package com.example.myapplication.data.dataBase

import androidx.room.*
import com.example.myapplication.data.ResultVideo
import com.example.myapplication.model.Movie

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videoResult: ResultVideo)

    @Query("SELECT * FROM ResultVideo WHERE id = :id")
    suspend fun getVideo(id:Int): List<ResultVideo>

//    @Query("SELECT * FROM Video WHERE title = :title")
//    suspend fun findMovie(title:String): Movie?

}