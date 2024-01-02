package android.dev.kalmurzaeff.newswave.repository.home

import android.dev.kalmurzaeff.newswave.data.local.database.dao.NewsDao
import android.dev.kalmurzaeff.newswave.data.remote.network.NewsApi
import android.dev.kalmurzaeff.newswave.data.remote.network.dto.toNews
import android.dev.kalmurzaeff.newswave.model.News
import android.dev.kalmurzaeff.newswave.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/7/2023
 */

class ProdHomeRepository @Inject constructor(
    private val remoteSource: NewsApi,
    private val localSource: NewsDao,
) : HomeRepository {

    override suspend fun getNewsByCategory(category: String, language: String): Result<List<News>> =
        runCatching {
            withContext(Dispatchers.IO) {
                val remoteResults = remoteSource.getNewsByCategory(category,language).results
                val localFavourites = localSource.getAllFavouriteNews()
                remoteResults.map { remoteResult ->
                    val isFavourite = localFavourites.first().any { localEntity ->
                        remoteResult.articleId == localEntity.articleId
                    }
                    remoteResult.toNews().copy(isFavourite = isFavourite)
                }
            }
        }

    override suspend fun upsertFavouriteNews(news: News) {
        withContext(Dispatchers.IO) {
            localSource.upsertFavouriteNews(news.toEntity())
        }
    }

    override suspend fun deleteNews(news: News) {
        withContext(Dispatchers.IO) {
            localSource.deleteNews(news.toEntity())
        }
    }

}