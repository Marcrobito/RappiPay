package dev.eighteentech.rappipay.home

import dev.eighteentech.rappipay.entities.Item
import dev.eighteentech.rappipay.entities.Response

interface MovieRepository {
    suspend fun getPopular(page: Int): Response<List<Item>>
    suspend fun getTopRated(page: Int): Response<List<Item>>
}