package android.dev.kalmurzaeff.newswave.repository

import android.dev.kalmurzaeff.newswave.data.local.database.dao.NewsDao
import android.dev.kalmurzaeff.newswave.data.local.database.entity.toNews
import android.dev.kalmurzaeff.newswave.data.remote.network.NewsApi
import android.dev.kalmurzaeff.newswave.data.remote.network.dto.toNews
import android.dev.kalmurzaeff.newswave.model.News
import android.dev.kalmurzaeff.newswave.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 1/31/2024
 */

class ProdNewsRepository @Inject constructor(
    private val remoteSource: NewsApi,
    private val localSource: NewsDao
) : NewsRepository {

    override suspend fun getSearchNews(query: String, language: String): Result<List<News>> =
        runCatching {
            val result = remoteSource.getSearchNews(query, language).results
            result.map {
                it.toNews()
            }
        }

    override suspend fun upsertNews(news: News) = withContext(Dispatchers.IO) {
        localSource.upsertFavouriteNews(news.toEntity())
    }

    override suspend fun deleteNews(news: News) = withContext(Dispatchers.IO) {
        localSource.deleteNews(news.toEntity())
    }

    override suspend fun getNewsByCategory(category: String, language: String): Result<List<News>> =
        runCatching {
            withContext(Dispatchers.IO) {
                val remoteResults = remoteSource.getNewsByCategory(category, language).results
                val localFavourites = localSource.getAllFavouriteNews()
                remoteResults.map { remoteResult ->
                    val isFavourite = localFavourites.first().any { localEntity ->
                        remoteResult.articleId == localEntity.articleId
                    }
                    remoteResult.toNews().copy(isFavourite = isFavourite)
                }
            }
        }

    override fun getAllNews(): Flow<List<News>> =
        localSource.getAllFavouriteNews().map { newsEntities ->
            newsEntities.map {
                it.toNews()
            }
        }
}