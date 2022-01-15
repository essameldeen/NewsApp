package com.smartzone.newsapp.app.di

import com.smartzone.newsapp.domain.repoitory.NewsRepository
import com.smartzone.newsapp.domain.usecase.GetAllNews
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
    fun provideUseCase(repo: NewsRepository): GetAllNews {
        return GetAllNews(repo)
    }

}