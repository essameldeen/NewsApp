package com.smartzone.newsapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.domain.usecase.GetAllNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val usesCase: GetAllNews
) : ViewModel() {

    var breakingNewsPage = 1

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
        _showErrorMessage.value = ""
        _showProgress.value = false
        getAllNews()
    }

    private fun getAllNews() = viewModelScope.launch {
        _showProgress.postValue(true)
        try {

            val result = usesCase.getAllNews("us", pageNumber = breakingNewsPage)
            _allList.postValue(result)
            _showProgress.postValue(false)

        } catch (e: Exception) {
            _showProgress.postValue(false)
            _showErrorMessage.postValue(e.message)
        }
    }

}