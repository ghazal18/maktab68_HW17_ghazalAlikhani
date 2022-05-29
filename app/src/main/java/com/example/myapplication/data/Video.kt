package com.example.myapplication.data

import androidx.room.Entity
import com.example.myapplication.data.ResultVideo

data class Video(
    val id: Int,
    val results: List<ResultVideo>
)