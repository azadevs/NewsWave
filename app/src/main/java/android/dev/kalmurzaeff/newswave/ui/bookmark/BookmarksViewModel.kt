package android.dev.kalmurzaeff.newswave.ui.bookmark

import android.dev.kalmurzaeff.newswave.model.News
import android.dev.kalmurzaeff.newswave.repository.bookmark.BookmarksRepository
import android.dev.kalmurzaeff.newswave.ui.model.NewsDisplayModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/6/2023
 */

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val repository: BookmarksRepository
) : ViewModel() {

    private val _favouriteNew =
        MutableStateFlow<BookmarksState<List<NewsDisplayModel>>>(BookmarksState.Empty)
    val favouriteNews = _favouriteNew.asStateFlow()

    private val _actionItemClicked = Channel<News>()
    val actionItemClicked = _actionItemClicked.receiveAsFlow()

    init {
        getFavouriteNews()
    }

    private fun getFavouriteNews() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNews().collect { newsList ->
                if (newsList.isEmpty()) {
                    _favouriteNew.value = BookmarksState.Empty
                } else {
                    _favouriteNew.value = BookmarksState.Success(newsList.map { news ->
                        NewsDisplayModel(
                            articleId = news.articleId,
                            title = news.title,
                            category = news.category,
                            imageUri = news.imageUri,
                            content = news.content,
                            isFavourite = news.isFavourite,
                            onNewsClicked = {
                                handleOnItemClicked(news)
                            })
                    })
                }
            }
        }
    }


    private fun handleOnItemClicked(news: News) {
        viewModelScope.launch {
            _actionItemClicked.send(news)
        }
    }

    sealed class BookmarksState<out T> {
        data class Success<T>(val data: T) : BookmarksState<T>()
        data object Empty : BookmarksState<Nothing>()
    }

}