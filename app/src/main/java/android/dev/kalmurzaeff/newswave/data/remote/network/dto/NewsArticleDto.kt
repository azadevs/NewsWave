package android.dev.kalmurzaeff.newswave.data.remote.network.dto


import android.dev.kalmurzaeff.newswave.data.local.database.entity.NewsEntity
import android.dev.kalmurzaeff.newswave.model.News
import com.google.gson.annotations.SerializedName

data class NewsArticleDto(
    @SerializedName("article_id") val articleId: String,
    @SerializedName("category") val category: List<String>,
    @SerializedName("content") val content: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("image_url") val imageUri: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("source_id") val sourceId: String?
)

fun NewsArticleDto.toNews(): News =
    News(
        articleId = articleId,
        description = description,
        title = title,
        imageUri = imageUri,
        content = content,
        category = category.first(),
        sourceId = sourceId
    )

fun NewsArticleDto.toEntity(): NewsEntity =
    NewsEntity(
        articleId = articleId,
        description = description,
        title = title,
        imageUri = imageUri,
        content = content,
        category = category.first(),
        sourceId = sourceId
    )
