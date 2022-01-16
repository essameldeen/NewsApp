package com.smartzone.newsapp.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.domain.usecase.GetAllNews
import com.smartzone.newsapp.domain.usecase.GetAllNewsDB
import com.smartzone.newsapp.domain.usecase.RemoveNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val usesCaseGet: GetAllNewsDB,
    private val usesCaseDelete: RemoveNews
) : ViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    private val _showErrorMessage = MutableLiveData<String>()
    val showErrorMessage: LiveData<String>
        get() = _showErrorMessage

    fun getAllNewsDb() = usesCaseGet.getAllNews()

    fun removeNews(article: Article) = viewModelScope.launch {
        try {
            val result = usesCaseDelete.removeNewsFromDB(article)
            _success.postValue(result)
        } catch (e: Exception) {
            _showErrorMessage.postValue(e.message)
        }


    }
}