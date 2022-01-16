package com.smartzone.newsapp.domain.usecase

import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.domain.repoitory.NewsRepository
import javax.inject.Inject

class SearchNews @Inject constructor(private val repo: NewsRepository) {
    suspend fun search(value: String, pageNumber: Int): NewsResponse {
        return repo.searchByTopic(value, pageNumber)
    }
}