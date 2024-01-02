package android.dev.kalmurzaeff.newswave.data.local.database.dao

import android.dev.kalmurzaeff.newswave.data.local.database.entity.NewsEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/4/2023
 */

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllFavouriteNews(): Flow<List<NewsEntity>>

    @Upsert
    suspend fun upsertFavouriteNews(newsEntity: NewsEntity)

    @Delete
    suspend fun deleteNews(newsEntity: NewsEntity)

}