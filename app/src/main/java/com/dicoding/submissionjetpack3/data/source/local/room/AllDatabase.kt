package com.dicoding.submissionjetpack3.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.submissionjetpack3.data.source.local.entity.MovieEntity
import com.dicoding.submissionjetpack3.data.source.local.entity.TvEntity

@Database(
        entities = [MovieEntity::class, TvEntity::class],
        version = 1,
        exportSchema = false
)
abstract class AllDatabase : RoomDatabase() {
    abstract fun filmDao(): AllDao

    companion object {
        @Volatile
        private var INSTANCE: AllDatabase? = null

        fun getInstance(context: Context): AllDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: Room.databaseBuilder(context.applicationContext, AllDatabase::class.java, "Film.db").build()
                }
    }
}