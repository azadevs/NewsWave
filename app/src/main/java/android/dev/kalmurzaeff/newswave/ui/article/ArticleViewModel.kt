package android.dev.kalmurzaeff.newswave.ui.article

import android.dev.kalmurzaeff.newswave.model.News
import android.dev.kalmurzaeff.newswave.repository.article.ArticleRepository
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/7/2023
 */

@HiltViewModel
class ArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val repository: ArticleRepository
) : ViewModel() {

    val articleNews = MutableStateFlow(savedStateHandle.get<News>("news")).stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000), null
    )

    fun handleFavouriteNewsItem(isFavourite: Boolean) {
        viewModelScope.launch {
            if (isFavourite) {
                articleNews.value?.copy(isFavourite = true)?.let {
                    logcat("Bookmarks") {
                        "$it"
                    }
                    repository.upsertNews(it)
                }
            } else {
                articleNews.value?.let {
                    repository.deleteNews(it)
                }
            }
        }
    }
}