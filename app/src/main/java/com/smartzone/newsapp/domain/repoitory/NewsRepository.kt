package com.smartzone.newsapp.domain.repoitory

import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.data.model.NewsResponse

interface NewsRepository {

    suspend fun getAllNews(country: String, pageNumber: Int): NewsResponse
    suspend fun searchByTopic(searchValue: String, pageNumber: Int): MutableList<NewsResponse>

    suspend fun insertNews(article: Article):Boolean
    suspend fun deleteNews(article: Article)
    suspend fun getAllNewsFromDB()
}