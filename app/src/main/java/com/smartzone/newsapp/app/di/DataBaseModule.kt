package com.smartzone.newsapp.app.di

import android.content.Context
import androidx.room.Room
import com.smartzone.newsapp.data.db.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDao(db: NewsDataBase) = db.getNewsDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): NewsDataBase {
        return Room.databaseBuilder(
            appContext,
            NewsDataBase::class.java,
            "article_.db"
        ).build()
    }

}