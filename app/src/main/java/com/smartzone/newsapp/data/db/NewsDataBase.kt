package com.smartzone.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.smartzone.newsapp.data.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}