package dev.eighteentech.rappipay.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eighteentech.rappipay.entities.Detail
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Type
import dev.eighteentech.rappipay.entities.Type.Movie
import dev.eighteentech.rappipay.entities.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val repository: DetailRepository) : ViewModel() {

    private val _video = MutableLiveData<Response<Video>>(Response.NotStarted)
    val video: LiveData<Response<Video>> get() = _video

    private val _detail = MutableLiveData<Response<Detail>>(Response.NotStarted)
    val detail: LiveData<Response<Detail>> get() = _detail

    fun loadDetail(id: String, type: Type) {
        viewModelScope.launch {
            _detail.value = withContext(Dispatchers.IO) {
                if (type is Movie)
                    repository.getMovie(id)
                else
                    repository.getTV(id)
            }
            _video.value = withContext(Dispatchers.IO) {
                repository.getVideo(id, type.getTypeName())
            }
        }
    }


}