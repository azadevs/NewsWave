package android.dev.kalmurzaeff.newswave.di

import android.dev.kalmurzaeff.newswave.repository.NewsRepository
import android.dev.kalmurzaeff.newswave.repository.ProdNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/7/2023
 */

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(repository: ProdNewsRepository): NewsRepository

}