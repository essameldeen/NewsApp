package com.smartzone.newsapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.domain.usecase.SearchNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchNews) : ViewModel() {
    private var newsPageNumber = 1

    private val _allList = MutableLiveData<NewsResponse>()
    val allList: LiveData<NewsResponse>
        get() = _allList

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
            _showProgress.postValue(false)
            val result = useCase.search(searchValue, pageNumber = newsPageNumber)
            _allList.postValue(result)
        } catch (e: Exception) {
            _showProgress.postValue(false)
            _showErrorMessage.postValue(e.message)
        }

    }
}