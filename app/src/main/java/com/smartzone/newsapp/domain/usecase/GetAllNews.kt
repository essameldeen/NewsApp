package com.smartzone.newsapp.domain.usecase

import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.domain.repoitory.NewsRepository

class GetAllNews constructor(private val repo: NewsRepository) {
    suspend fun getAllNews(county: String, pageNumber: Int): NewsResponse {
        return repo.getAllNews(county, pageNumber)
    }
}