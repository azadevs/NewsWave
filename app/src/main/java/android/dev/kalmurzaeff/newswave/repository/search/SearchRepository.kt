package android.dev.kalmurzaeff.newswave.repository.search

import android.dev.kalmurzaeff.newswave.model.News

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/3/2023
 */

interface SearchRepository {

    suspend fun getSearchNews(query: String, language: String): Result<List<News>>

}