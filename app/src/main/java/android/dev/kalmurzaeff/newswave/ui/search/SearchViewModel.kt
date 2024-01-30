package android.dev.kalmurzaeff.newswave.ui.search

import android.dev.kalmurzaeff.newswave.model.News
import android.dev.kalmurzaeff.newswave.repository.NewsRepository
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
 * Date : 12/3/2023
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State<List<NewsDisplayModel>>>(State.Empty)
    val state = _state.asStateFlow()

    private val _actionItemClicked = Channel<News>()
    val actionItemClicked = _actionItemClicked.receiveAsFlow()

    fun getSearchNews(query: String, language: String) {
        _state.value = State.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSearchNews(query, language)
                .fold(onSuccess = { newsList: List<News> ->
                    _state.value = State.Success(
                        newsList.map { news ->
                            NewsDisplayModel(articleId = news.articleId,
                                title = news.title,
                                category = news.category,
                                imageUri = news.imageUri,
                                content = news.content,
                                isFavourite = news.isFavourite,
                                onNewsClicked = {
                                    handleOnItemClicked(news)
                                })
                        },
                    )
                }, onFailure = { error ->
                    _state.value = State.Error(error.localizedMessage ?: "Unknown error")
                })
        }
    }

    private fun handleOnItemClicked(news: News) {
        viewModelScope.launch {
            _actionItemClicked.send(news)
        }
    }

}