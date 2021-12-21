package dev.eighteentech.rappipay.entities

import com.google.gson.annotations.SerializedName
import dev.eighteentech.rappipay.common.Constants.IMAGE_BASE_PATH

data class Item(
    val id: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    val overview: String,
    val name: String?,
    val title: String?,
)

data class MovieDBResponse(
    val page: Int,
    val results: List<Item>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

sealed class Type {
    object Movie : Type()
    object Series : Type()

    fun getTypeName(): String = when (this) {
        is Movie -> "movie"
        is Series -> "tv"
    }

}

data class Video(
    val name: String,
    val key: String,
    val id: String
)

data class VideoResponse(
    val id: String,
    val results: List<Video>
)

data class Movie(
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") private var posterPath: String,
    val budget: Double,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Double,
    val runtime: Int,
    val tagline: String,
    @SerializedName("vote_average") val vote_average: Double,
) {
    val poster = IMAGE_BASE_PATH + posterPath
}
