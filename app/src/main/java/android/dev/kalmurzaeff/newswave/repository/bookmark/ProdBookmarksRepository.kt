package android.dev.kalmurzaeff.newswave.repository.bookmark

import android.dev.kalmurzaeff.newswave.data.local.database.dao.NewsDao
import android.dev.kalmurzaeff.newswave.data.local.database.entity.toNews
import android.dev.kalmurzaeff.newswave.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/6/2023
 */

class ProdBookmarksRepository @Inject constructor(private val localSource: NewsDao) :
    BookmarksRepository {

    override suspend fun getAllNews(): Flow<List<News>> =
        localSource.getAllFavouriteNews().map { newsEntities ->
            newsEntities.map {
                it.toNews()
            }
        }
}