package android.dev.kalmurzaeff.newswave.ui.home

import android.dev.kalmurzaeff.newswave.data.local.preferences.NewsPreferences
import android.dev.kalmurzaeff.newswave.model.News
import android.dev.kalmurzaeff.newswave.repository.home.HomeRepository
import android.dev.kalmurzaeff.newswave.ui.model.NewsDisplayModel
import android.dev.kalmurzaeff.newswave.utils.State
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
 * Date : 11/7/2023
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val preferences: NewsPreferences,
) : ViewModel() {

    private val _actionItemClicked = Channel<News>()
    val actionItemClicked = _actionItemClicked.receiveAsFlow()

    private val _stateRecommendation =
        MutableStateFlow<State<List<NewsDisplayModel>>>(State.Empty)
    val stateRecommendation = _stateRecommendation.asStateFlow()

    private val _stateCategory =
        MutableStateFlow<State<List<NewsDisplayModel>>>(State.Empty)
    val stateCategory = _stateCategory.asStateFlow()


    fun getRecommendationNews(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateRecommendation.emit(State.Loading)
            repository.getNewsByCategory(
                category = preferences.getSelectedCategoriesFromPreference(),
                language = language
            )
                .fold(onSuccess = { newsList ->
                    _stateRecommendation.emit(State.Success(newsList.map { news ->
                        NewsDisplayModel(articleId = news.articleId,
                            title = news.title,
                            category = news.category,
                            imageUri = news.imageUri,
                            content = news.content,
                            isFavourite = news.isFavourite,
                            onNewsClicked = {
                                handleOnItemClicked(news)
                            })
                    }))
                }, onFailure = { error ->
                    _stateRecommendation.emit(
                        State.Error(error.localizedMessage ?: "Unknown error")
                    )
                })
        }
    }

    fun getNewsByCategory(category: String, language: String) {
        _stateCategory.value = State.Empty
        viewModelScope.launch {
            _stateCategory.emit(State.Loading)
            repository.getNewsByCategory(
                category = category,
                language = language
            ).fold(onSuccess = { newsList ->
                _stateCategory.emit(State.Success(newsList.map { news ->
                    NewsDisplayModel(articleId = news.articleId,
                        title = news.title,
                        category = news.category,
                        imageUri = news.imageUri,
                        content = news.content,
                        isFavourite = news.isFavourite,
                        onNewsClicked = {
                            handleOnItemClicked(news)
                        },
                        onBookmarkClicked = {
                            news.isFavourite = it
                            handleOnBookmarkClicked(
                                news, it
                            )
                        })
                }))
            }, onFailure = { error ->
                _stateCategory.emit(
                    State.Error(error.localizedMessage ?: "Unknown error")
                )
            })
        }
    }

    private fun handleOnBookmarkClicked(news: News, bool: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (bool) {
                repository.upsertFavouriteNews(news.copy(isFavourite = true))
            } else {
                repository.deleteNews(news.copy(isFavourite = false))
            }
        }
    }

    private fun handleOnItemClicked(news: News) {
        viewModelScope.launch {
            _actionItemClicked.send(news)
        }
    }


}

