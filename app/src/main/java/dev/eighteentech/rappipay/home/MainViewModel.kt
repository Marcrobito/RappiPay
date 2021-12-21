package dev.eighteentech.rappipay.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eighteentech.rappipay.entities.Item
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Response.Loading
import dev.eighteentech.rappipay.entities.Response.NotStarted
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MovieRepository):ViewModel() {

    private val _items = MutableLiveData<Response<List<Item>>>(NotStarted)
    val items : LiveData<Response<List<Item>>> get() = _items

    private var currentPage = 1
    private var isPopular = true

    fun getPopular(){
        _items.value = Loading
        if (!isPopular){
            isPopular = true
            currentPage = 1
        }
        viewModelScope.launch {
            _items.value = withContext(Dispatchers.IO){
                repository.getPopular(currentPage)
            }
        }
    }

    fun getTopRated(){
        _items.value = Loading
        if (isPopular){
            isPopular = false
            currentPage = 1
        }
        viewModelScope.launch {
            _items.value = withContext(Dispatchers.IO){
                repository.getTopRated(currentPage)
            }
        }
    }



    fun nextPage() {
        currentPage ++
        if (isPopular){
            getPopular()
            return
        }
        getTopRated()
    }

}