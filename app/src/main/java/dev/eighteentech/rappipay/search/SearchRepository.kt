package dev.eighteentech.rappipay.search

import dev.eighteentech.rappipay.common.mix
import dev.eighteentech.rappipay.entities.Item
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.network.Api

class SearchRepository(private val api: Api)  {
    suspend fun search(query: String): Response<List<Item>> {
        return try {
            val movies: MutableList<Item> = api.searchMovies(query).results.toMutableList()
            val shows = api.searchShows(query).results.toMutableList()
            if (movies.isEmpty())
                Response.Error(Exception(""))
            else {
                Response.Success(movies.mix(shows))
            }
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}