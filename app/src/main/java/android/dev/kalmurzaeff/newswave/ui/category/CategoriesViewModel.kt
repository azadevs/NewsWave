package android.dev.kalmurzaeff.newswave.ui.category

import android.dev.kalmurzaeff.newswave.data.local.preferences.NewsPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/4/2023
 */

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val preferences: NewsPreferences,
    private val gson: Gson
) : ViewModel() {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()


    fun saveCategoriesToPreference(categories: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            preferences.categories = gson.toJson(categories)
        }
    }


    fun getSelectedCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categories.emit(
                gson.fromJson(
                    preferences.categories,
                    object : TypeToken<List<String>>() {}.type
                )
            )
        }
    }

}