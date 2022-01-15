package com.smartzone.newsapp.domain.usecase

import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.domain.repoitory.NewsRepository
import javax.inject.Inject

class RemoveNews @Inject constructor(private val repo: NewsRepository) {
    suspend fun removeNewsFromDB(article: Article):Boolean {
       return repo.deleteNews(article)
    }
}