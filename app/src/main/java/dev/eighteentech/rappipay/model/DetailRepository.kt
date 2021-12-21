package dev.eighteentech.rappipay.model

import dev.eighteentech.rappipay.entities.Detail
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.network.Api

class DetailRepository(private val api: Api)  {
    suspend fun getById(id: String, type: String): Response<Detail> {
        return try {
            val detail = api.getVideos(id, type)
            Response.Success(detail.results[0])
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}
