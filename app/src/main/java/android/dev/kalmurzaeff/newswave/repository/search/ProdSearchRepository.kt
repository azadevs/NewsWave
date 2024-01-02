package android.dev.kalmurzaeff.newswave.repository.search

import android.dev.kalmurzaeff.newswave.data.remote.network.NewsApi
import android.dev.kalmurzaeff.newswave.data.remote.network.dto.toNews
import android.dev.kalmurzaeff.newswave.model.News
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/3/2023
 */

class ProdSearchRepository @Inject constructor(
    private val remoteSource: NewsApi
) : SearchRepository {

    override suspend fun getSearchNews(query: String, language: String): Result<List<News>> =
        runCatching {
            val result = remoteSource.getSearchNews(query, language).results
            result.map {
                it.toNews()
            }
        }
}