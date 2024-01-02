package android.dev.kalmurzaeff.newswave.di

import android.content.Context
import android.dev.kalmurzaeff.newswave.utils.Constants
import android.dev.kalmurzaeff.newswave.data.local.database.NewsDatabase
import android.dev.kalmurzaeff.newswave.data.local.database.dao.NewsDao
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/4/2023
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room
            .databaseBuilder(
                context, NewsDatabase::class.java,
                Constants.DATABASE_NAME
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao {
        return database.dao()
    }

}