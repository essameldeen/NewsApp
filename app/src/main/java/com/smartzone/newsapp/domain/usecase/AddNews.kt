package com.smartzone.newsapp.domain.usecase

import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.domain.repoitory.NewsRepository
import javax.inject.Inject

class AddNews @Inject constructor(private val repo: NewsRepository) {
    suspend fun addNewsToDB(article: Article): Boolean {
        return repo.insertNews(article)
    }
}