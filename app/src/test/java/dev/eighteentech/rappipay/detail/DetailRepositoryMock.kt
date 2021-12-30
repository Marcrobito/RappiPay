package dev.eighteentech.rappipay.detail

import dev.eighteentech.rappipay.entities.Detail
import dev.eighteentech.rappipay.entities.Detail.Movie
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Video
import kotlinx.coroutines.delay

class DetailRepositoryMock(var willSucceed: Boolean = true) : DetailRepository {

    override suspend fun getVideo(id: String, type: String): Response<Video> {
        delay(1000)
        return if (willSucceed){
            Response.Success(video)
        }else{
            Response.Error(exception)
        }
    }

    override suspend fun getMovie(id: String): Response<Movie> {
        delay(1000)
        return if (willSucceed){
            Response.Success(movie)
        }else{
            Response.Error(exception)
        }
    }

    override suspend fun getTV(id: String): Response<Detail.TV> {
        delay(1000)
        return if (willSucceed){
            TODO()
            //Response.Success(tv)
        }else{
            Response.Error(exception)
        }
    }

    companion object{
        val exception = Exception("delivered exception")
        val video = Video("video1", "Video", "1")
        val movie = Movie("video1", "Video", "1",7.3, "","",1000.0,1000.0,142)

    }
}