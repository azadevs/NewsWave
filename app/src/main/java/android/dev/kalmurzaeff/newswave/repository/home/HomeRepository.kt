package android.dev.kalmurzaeff.newswave.repository.home

import android.dev.kalmurzaeff.newswave.model.News

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/7/2023
 */

interface HomeRepository {


    suspend fun upsertFavouriteNews(news: News)

    suspend fun deleteNews(news: News)

    suspend fun getNewsByCategory(category: String,language:String): Result<List<News>>

}