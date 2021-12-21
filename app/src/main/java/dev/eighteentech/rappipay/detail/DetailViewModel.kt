package dev.eighteentech.rappipay.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val repository: DetailRepository):ViewModel() {

    private val _detail = MutableLiveData<Response<Video>>(Response.NotStarted)
    val detail : LiveData<Response<Video>> get() = _detail

    fun loadDetail(movieId: String, type: String) {
        viewModelScope.launch {
            _detail.value = withContext(Dispatchers.IO){
                repository.getVideo(movieId,type)
            }
        }
    }
}