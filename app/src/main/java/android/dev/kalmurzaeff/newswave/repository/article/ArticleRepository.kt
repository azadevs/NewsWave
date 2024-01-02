package android.dev.kalmurzaeff.newswave.repository.article

import android.dev.kalmurzaeff.newswave.model.News

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/9/2023
 */

interface ArticleRepository {

    suspend fun upsertNews(news: News)

    suspend fun deleteNews(news: News)
}