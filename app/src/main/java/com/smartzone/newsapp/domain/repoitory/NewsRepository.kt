package com.smartzone.newsapp.domain.repoitory

import androidx.lifecycle.LiveData
import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.data.model.NewsResponse

interface NewsRepository {

    suspend fun getAllNews(country: String, pageNumber: Int): NewsResponse
    suspend fun searchByTopic(searchValue: String, pageNumber: Int): NewsResponse

    suspend fun insertNews(article: Article): Boolean
    suspend fun deleteNews(article: Article): Boolean
    fun getAllNewsFromDB(): LiveData<List<Article>>
}