package com.smartzone.newsapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.domain.usecase.SearchNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchNews) : ViewModel() {
    private var newsPageNumber = 1

    private val _allNews = MutableLiveData<NewsResponse>()
    val allNews: LiveData<NewsResponse>
        get() = _allNews

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private val _showErrorMessage = MutableLiveData<String>()
    val showErrorMessage: LiveData<String>
        get() = _showErrorMessage

    init {
        _showProgress.postValue(false)
    }

    fun searchNews(searchValue: String) = viewModelScope.launch {
        _showProgress.postValue(true)
        try {
            delay(500L)
            _showProgress.postValue(false)
            val result = useCase.search(searchValue, pageNumber = newsPageNumber)
            _allNews.postValue(result)
        } catch (e: Exception) {
            _showProgress.postValue(false)
            _showErrorMessage.postValue(e.message)
        }

    }
}