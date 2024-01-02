package android.dev.kalmurzaeff.newswave.di

import android.dev.kalmurzaeff.newswave.repository.article.ArticleRepository
import android.dev.kalmurzaeff.newswave.repository.article.ProdArticleRepository
import android.dev.kalmurzaeff.newswave.repository.bookmark.BookmarksRepository
import android.dev.kalmurzaeff.newswave.repository.bookmark.ProdBookmarksRepository
import android.dev.kalmurzaeff.newswave.repository.home.HomeRepository
import android.dev.kalmurzaeff.newswave.repository.home.ProdHomeRepository
import android.dev.kalmurzaeff.newswave.repository.search.ProdSearchRepository
import android.dev.kalmurzaeff.newswave.repository.search.SearchRepository
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
    abstract fun bindHomeRepository(repository: ProdHomeRepository): HomeRepository


    @Singleton
    @Binds
    abstract fun bindSearchRepository(repository: ProdSearchRepository): SearchRepository

    @Singleton
    @Binds
    abstract fun bindBookmarksRepository(repository: ProdBookmarksRepository): BookmarksRepository

    @Singleton
    @Binds
    abstract fun bindArticleRepository(repository: ProdArticleRepository): ArticleRepository
}