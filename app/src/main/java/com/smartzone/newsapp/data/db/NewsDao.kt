package com.smartzone.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.smartzone.newsapp.data.model.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article : Article):Long

    @Query("SELECT * FROM articles")
    fun  getAllNews() : LiveData<List<Article>>

    @Delete
    suspend fun  deleteArticle(article: Article)
}