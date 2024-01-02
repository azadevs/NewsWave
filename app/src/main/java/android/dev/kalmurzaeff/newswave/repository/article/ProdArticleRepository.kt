package android.dev.kalmurzaeff.newswave.repository.article

import android.dev.kalmurzaeff.newswave.data.local.database.dao.NewsDao
import android.dev.kalmurzaeff.newswave.model.News
import android.dev.kalmurzaeff.newswave.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/9/2023
 */

class ProdArticleRepository @Inject constructor(
    private val localSource: NewsDao
) : ArticleRepository {

    override suspend fun upsertNews(news: News) {
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