package android.dev.kalmurzaeff.newswave.data.local.preferences

interface Preferences {
    var isHave: Boolean
    var categories: String
    var theme: Boolean
    var language: String

    fun getSelectedCategoriesFromPreference(): String
}