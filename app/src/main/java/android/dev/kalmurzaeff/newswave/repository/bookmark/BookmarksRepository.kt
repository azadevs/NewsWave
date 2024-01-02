package android.dev.kalmurzaeff.newswave.repository.bookmark

import android.dev.kalmurzaeff.newswave.model.News
import kotlinx.coroutines.flow.Flow

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/6/2023
 */

interface BookmarksRepository {

    suspend fun getAllNews(): Flow<List<News>>


}