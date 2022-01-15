package com.smartzone.newsapp.domain.usecase

import com.smartzone.newsapp.domain.repoitory.NewsRepository
import javax.inject.Inject

class GetAllNewsDB @Inject constructor(private val repo: NewsRepository) {
     fun getAllNews() = repo.getAllNewsFromDB()

}