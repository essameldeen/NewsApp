package com.smartzone.newsapp.app.di

import com.smartzone.newsapp.domain.repoitory.NewsRepository
import com.smartzone.newsapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllNews(repo: NewsRepository): GetAllNews {
        return GetAllNews(repo)
    }

    @Provides
    @Singleton
    fun provideInsertNews(repo: NewsRepository): AddNews {
        return AddNews(repo)
    }
    @Provides
    @Singleton
    fun provideSearchNews(repo: NewsRepository): SearchNews {
        return SearchNews(repo)
    }

    @Provides
    @Singleton
    fun provideRemoveNewsNews(repo: NewsRepository): RemoveNews {
        return RemoveNews(repo)
    }

    @Provides
    @Singleton
    fun provideAllNewsDb(repo: NewsRepository): GetAllNewsDB {
        return GetAllNewsDB(repo)
    }

}