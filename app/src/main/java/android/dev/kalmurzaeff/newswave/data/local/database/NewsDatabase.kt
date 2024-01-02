package android.dev.kalmurzaeff.newswave.data.local.database

import android.dev.kalmurzaeff.newswave.data.local.database.dao.NewsDao
import android.dev.kalmurzaeff.newswave.data.local.database.entity.NewsEntity
import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/3/2023
 */

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun dao(): NewsDao

}