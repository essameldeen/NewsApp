package com.smartzone.newsapp.data.repository

import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.data.api.NewsApi
import com.smartzone.newsapp.data.db.NewsDataBase
import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.domain.repoitory.NewsRepository
import retrofit2.Retrofit
import javax.inject.Inject

class NewRepositoryImp @Inject constructor(private val db: NewsDataBase, retrofit: Retrofit) :
    NewsRepository {
    private val api = retrofit.create(NewsApi::class.java)
    override suspend fun getAllNews(country: String, pageNumber: Int): NewsResponse {
        return  api.getBreakingNews(country, pageNumber)
    }

    override suspend fun searchByTopic(
        searchValue: String,
        pageNumber: Int
    ): MutableList<NewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun insertNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNewsFromDB() {
        TODO("Not yet implemented")
    }
}