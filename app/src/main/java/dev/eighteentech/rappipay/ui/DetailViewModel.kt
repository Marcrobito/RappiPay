package dev.eighteentech.rappipay.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eighteentech.rappipay.entities.Detail
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.model.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val repository: MovieRepository):ViewModel() {

    private val _detail = MutableLiveData<Response<Detail>>(Response.NotStarted)
    val detail : LiveData<Response<Detail>> get() = _detail

    fun loadDetail(movieId: String, type: String) {
        viewModelScope.launch {
            _detail.value = withContext(Dispatchers.IO){
                repository.getById(movieId,type)
            }
        }
    }
}