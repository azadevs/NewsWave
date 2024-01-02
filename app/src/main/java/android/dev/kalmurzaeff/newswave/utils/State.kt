package android.dev.kalmurzaeff.newswave.utils

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/3/2023
 */

sealed interface State<out T> {

    data object Empty : State<Nothing>

    data object Loading : State<Nothing>

    data class Error(val message: String) : State<Nothing>

    data class Success<T>(val data: T) : State<T>
}
