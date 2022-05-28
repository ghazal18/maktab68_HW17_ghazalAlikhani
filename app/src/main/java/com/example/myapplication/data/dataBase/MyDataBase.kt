package com.example.myapplication.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MYDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieComingSoonDao(): ComingSoonDao
    companion object {
        var INSTANCE: MYDataBase? = null

        fun getAppDataBase(context: Context): MYDataBase? {
            if (INSTANCE == null) {
                synchronized(MYDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MYDataBase::class.java, "MYDB"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
