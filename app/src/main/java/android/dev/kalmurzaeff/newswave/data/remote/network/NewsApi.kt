package android.dev.kalmurzaeff.newswave.data.remote.network

import android.dev.kalmurzaeff.newswave.data.remote.network.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/3/2023
 */

interface NewsApi {

    @GET("news")
    suspend fun getNewsByCategory(
        @Query("category") category: String,
        @Query("language") defaultLanguage: String = DEFAULT_LANGUAGE,
        @Query("image") image: Int = DEFAULT_IMAGE_TYPE
    ): NewsDto

    @GET("news")
    suspend fun getSearchNews(
        @Query("q") query: String,
        @Query("language") defaultLanguage: String = DEFAULT_LANGUAGE
    ): NewsDto

    companion object {
        const val DEFAULT_LANGUAGE = "en"
        const val DEFAULT_IMAGE_TYPE = 1
    }
}