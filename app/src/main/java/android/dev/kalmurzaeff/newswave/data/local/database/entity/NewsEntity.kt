package android.dev.kalmurzaeff.newswave.data.local.database.entity

import android.dev.kalmurzaeff.newswave.model.News
import android.dev.kalmurzaeff.newswave.utils.Constants.TABLE_NAME
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/4/2023
 */

@Entity(tableName = TABLE_NAME)
data class NewsEntity(
    @PrimaryKey(autoGenerate = false)
    val articleId: String,
    val title: String?,
    val content: String?,
    val description: String?,
    val imageUri: String?,
    val category: String?,
    val sourceId: String?,
    val isFavourite: Boolean = false
)

fun NewsEntity.toNews(): News = News(
    articleId = articleId,
    description = description,
    title = title,
    imageUri = imageUri,
    content = content,
    category = category,
    sourceId = sourceId,
    isFavourite = isFavourite
)
