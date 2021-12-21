package dev.eighteentech.rappipay.network

import dev.eighteentech.rappipay.common.Constants.API_KEY
import dev.eighteentech.rappipay.entities.*
import dev.eighteentech.rappipay.entities.Detail.Movie
import dev.eighteentech.rappipay.entities.Detail.TV
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie/popular?language=en-US&api_key=$API_KEY")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): MovieDBResponse

    @GET("tv/popular?language=en-US&api_key=$API_KEY")
    suspend fun getPopularTvShows(@Query("page") page: Int = 1): MovieDBResponse

    @GET("movie/top_rated?language=en-US&api_key=$API_KEY")
    suspend fun getTopRatedMovies(@Query("page") page: Int = 1): MovieDBResponse

    @GET("tv/top_rated?language=en-US&api_key=$API_KEY")
    suspend fun getTopRatedTvShows(@Query("page") page: Int = 1): MovieDBResponse

    @GET("search/movie?language=en-US&api_key=$API_KEY")
    suspend fun searchMovies(@Query("query") query: String ): MovieDBResponse

    @GET("search/tv?language=en-US&api_key=$API_KEY")
    suspend fun searchShows(@Query("query") query: String ): MovieDBResponse

    @GET("{type}/{id}/videos?api_key=$API_KEY")
    suspend fun getVideos(
        @Path("id") id: String,
        @Path("type") type: String
    ): VideoResponse

    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun getMovie(
        @Path("id") id: String
    ): Movie

    @GET("tv/{id}?api_key=$API_KEY")
    suspend fun getShow(
        @Path("id") id: String
    ): TV
}