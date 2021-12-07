package dev.eighteentech.rappipay.entities

import java.sql.Timestamp

data class Item(
    val id: String,
    val poster_path: String,
    val release_date: String?,
    val first_air_date: String?,
    val overview: String,
    val name: String?,
    val title: String?,

    )

data class MovieDBResponse(
    val page: Int,
    val results: List<Item>,
    val total_pages: Int,
    val total_results: Int
)

sealed class Type {
    object Movie : Type()
    object Series : Type()

    fun getTypeName(): String = when (this) {
        is Movie -> "movie"
        is Series -> "tv"
    }

}

data class Detail(
    val name: String,
    val key: String,
    val id: String
)


data class DetailResponse(
    val id: String,
    val results: List<Detail>
)
