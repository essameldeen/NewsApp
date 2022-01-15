package com.smartzone.newsapp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.domain.usecase.AddNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val usesCase: AddNews
) : ViewModel() {

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    private val _showErrorMessage = MutableLiveData<String>()
    val showErrorMessage: LiveData<String>
        get() = _showErrorMessage

    fun insertNews(article: Article) = viewModelScope.launch {
        _showProgress.postValue(true)
        try {
            _showProgress.postValue(false)
            val result = usesCase.addNewsToDB(article)
            _success.postValue(result)
        } catch (e: Exception) {
            _showProgress.postValue(false)
            _showErrorMessage.postValue(e.message)
        }


    }
}