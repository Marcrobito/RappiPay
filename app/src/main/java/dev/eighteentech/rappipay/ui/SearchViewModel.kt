package dev.eighteentech.rappipay.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eighteentech.rappipay.entities.Item
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.model.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _searchResults = MutableLiveData<Response<List<Item>>>(Response.NotStarted)
    val searchResults: LiveData<Response<List<Item>>> get() = _searchResults

    fun search(query: String) {
        _searchResults.value = Response.Loading
        viewModelScope.launch {
            _searchResults.value = withContext(Dispatchers.IO) {
                repository.search(query)
            }
        }
    }

}