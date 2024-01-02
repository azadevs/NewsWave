package android.dev.kalmurzaeff.newswave.model

import android.dev.kalmurzaeff.newswave.data.local.database.entity.NewsEntity
import java.io.Serializable

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/5/2023
 */

data class News(
    val articleId: String,
    val description: String?,
    val title: String?,
    val imageUri: String?,
    val content: String?,
    val category: String?,
    val sourceId: String?,
    var isFavourite: Boolean = false
) : Serializable

fun News.toEntity(): NewsEntity = NewsEntity(
    articleId = articleId,
    title = title,
    content = content,
    description = description,
    imageUri = imageUri,
    category = category,
    sourceId = sourceId,
    isFavourite = isFavourite
)
