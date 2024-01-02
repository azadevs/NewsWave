package android.dev.kalmurzaeff.newswave.ui.model

data class NewsDisplayModel(
    val articleId: String,
    val title: String?,
    val category: String?,
    val imageUri: String?,
    val content: String?,
    val isFavourite: Boolean = false,
    val onNewsClicked: () -> Unit,
    val onBookmarkClicked: ((Boolean) -> Unit)? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as NewsDisplayModel
        if (title != other.title) return false
        if (category != other.category) return false
        if (imageUri != other.imageUri) return false
        if (content != other.content) return false
        if (articleId != other.articleId) return false
        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + imageUri.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + articleId.hashCode()
        return result
    }

}