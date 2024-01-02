package android.dev.kalmurzaeff.newswave.data.remote.network.dto


import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("results")
    val results: List<NewsArticleDto>,
    @SerializedName("nextPage")
    val page: String
)