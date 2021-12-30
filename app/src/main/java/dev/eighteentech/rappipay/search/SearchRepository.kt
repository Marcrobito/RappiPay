package dev.eighteentech.rappipay.search

import dev.eighteentech.rappipay.entities.Item
import dev.eighteentech.rappipay.entities.Response

interface SearchRepository {
    suspend fun search(query: String): Response<List<Item>>
}