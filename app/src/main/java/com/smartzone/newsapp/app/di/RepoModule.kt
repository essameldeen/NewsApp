package com.smartzone.newsapp.app.di

import com.smartzone.newsapp.data.api.NewsApi
import com.smartzone.newsapp.data.db.NewsDataBase
import com.smartzone.newsapp.data.repository.NewRepositoryImp
import com.smartzone.newsapp.domain.repoitory.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Singleton
    @Provides
    fun provideNewsRepo(
        retrofit: Retrofit,
        dataBase: NewsDataBase,
    ): NewsRepository {
        return NewRepositoryImp(
            db = dataBase,
            retrofit = retrofit
        )
    }
}