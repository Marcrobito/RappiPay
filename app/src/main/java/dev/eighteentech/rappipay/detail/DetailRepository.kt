package dev.eighteentech.rappipay.detail

import dev.eighteentech.rappipay.entities.Detail
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Video

interface DetailRepository {
    suspend fun getVideo(id: String, type: String): Response<Video>
    suspend fun getMovie(id: String): Response<Detail.Movie>
    suspend fun getTV(id: String): Response<Detail.TV>
}