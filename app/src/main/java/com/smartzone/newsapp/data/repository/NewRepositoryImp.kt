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
        return api.getBreakingNews(country, pageNumber)
    }

    override suspend fun searchByTopic(
        searchValue: String,
        pageNumber: Int
    ): NewsResponse {
            return api.searchForNews(searchValue, pageNumber)
    }

    override suspend fun insertNews(article: Article): Boolean {
        return try {
            db.getNewsDao().addArticle(article)
            true
        } catch (e: Exception) {
            false
        }

    }

    override suspend fun deleteNews(article: Article): Boolean {
        return try {
            db.getNewsDao().deleteArticle(article)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun getAllNewsFromDB() = db.getNewsDao().getAllNews()

}