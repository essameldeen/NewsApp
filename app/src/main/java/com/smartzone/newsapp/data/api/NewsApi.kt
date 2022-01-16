package com.smartzone.newsapp.data.api

import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.utils.Constant.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        county: String = "us",
        @Query("page")
        pageCounter: Int = 1,
        @Query("apikey")
        apiKey: String = API_KEY
    ):NewsResponse

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchValue: String,
        @Query("page")
        pageCounter: Int = 1,
        @Query("apikey")
        apiKey: String = API_KEY
    ):NewsResponse
}