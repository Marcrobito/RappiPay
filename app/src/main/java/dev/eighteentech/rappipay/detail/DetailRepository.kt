package dev.eighteentech.rappipay.detail

import android.util.Log
import dev.eighteentech.rappipay.entities.Detail.Movie
import dev.eighteentech.rappipay.entities.Detail.TV
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Video
import dev.eighteentech.rappipay.network.Api

class DetailRepository(private val api: Api)  {
    suspend fun getVideo(id: String, type: String): Response<Video> {
        return try {
            val detail = api.getVideos(id, type)
            Response.Success(detail.results[0])
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    suspend fun getMovie(id: String): Response<Movie>{
        return try {
            Response.Success(api.getMovie(id))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    suspend fun getTV(id: String): Response<TV>{
        return try {
            Response.Success(api.getShow(id))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}
