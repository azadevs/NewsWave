package android.dev.kalmurzaeff.newswave.repository

import android.dev.kalmurzaeff.newswave.model.News
import kotlinx.coroutines.flow.Flow

/**
 * Created by : KalmurzaeffDev_A
 * Date : 1/31/2024
 */

interface NewsRepository {

    suspend fun getSearchNews(query: String, language: String): Result<List<News>>

    suspend fun upsertNews(news: News)

    suspend fun deleteNews(news: News)

    suspend fun getNewsByCategory(category: String, language: String): Result<List<News>>

    fun getAllNews(): Flow<List<News>>

}