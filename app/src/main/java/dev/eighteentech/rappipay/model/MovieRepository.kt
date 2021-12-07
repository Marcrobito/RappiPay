package dev.eighteentech.rappipay.model

import android.util.Log
import dev.eighteentech.rappipay.common.mix
import dev.eighteentech.rappipay.entities.Detail
import dev.eighteentech.rappipay.entities.Item
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.network.Api

class MovieRepository(private val api: Api) {

    private var list = mutableListOf<Item>()
    private var isPopular = true

    suspend fun getPopular(page: Int = 1): Response<List<Item>> {
        return try {
            val movies: MutableList<Item> = api.getPopularMovies(page).results.toMutableList()
            val shows = api.getPopularTvShows(page).results.toMutableList()
            if (movies.isEmpty())
                Response.Error(Exception(""))
            else {
                if (!isPopular) {
                    list.clear()
                    isPopular = true
                }
                val content = movies.mix(shows)
                list = (list + content) as MutableList<Item>
                Response.Success(list)
            }


        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    suspend fun getTopRated(page: Int = 1): Response<List<Item>> {
        return try {
            val movies: MutableList<Item> = api.getTopRatedMovies(page).results.toMutableList()
            val shows = api.getTopRatedTvShows(page).results.toMutableList()
            if (movies.isEmpty())
                Response.Error(Exception(""))
            else {
                if (isPopular) {
                    list.clear()
                    isPopular = false
                }
                val content = movies.mix(shows)
                list = (list + content) as MutableList<Item>
                Response.Success(list)
            }

        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    suspend fun getById(id: String, type: String): Response<Detail> {
        return try {
            val detail = api.getDetail(id, type)
            Log.d("Detail", detail.toString())
            Response.Success(detail.results[0])
        } catch (e: Exception) {
            Log.d("Detail", e.toString())
            Response.Error(e)
        }
    }

}
