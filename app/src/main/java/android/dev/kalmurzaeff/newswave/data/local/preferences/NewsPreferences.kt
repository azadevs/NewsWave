package android.dev.kalmurzaeff.newswave.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import android.dev.kalmurzaeff.newswave.utils.Constants.PREFERENCE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class NewsPreferences @Inject constructor(
    @ApplicationContext context: Context,
    private val gson: Gson
) : Preferences {
    private val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    override var isHave: Boolean by preferences(default = false)

    override var categories: String by preferences("")

    override var theme: Boolean by preferences(false)
    override var language: String by preferences("en")
    override fun getSelectedCategoriesFromPreference(): String {
        val topics = gson.fromJson<List<String>>(
            categories, object : TypeToken<List<String>>() {}.type
        )
        return topics.joinToString(",")
    }
}

operator fun SharedPreferences.invoke(
    default: Boolean,
    key: String? = null
) = create(default, key, this::getBoolean, edit()::putBoolean)

operator fun SharedPreferences.invoke(
    default: String,
    key: String? = null
) = create(default, key, this::getString, edit()::putString)


private fun <T> create(
    default: T,
    key: String? = null,
    getter: (key: String, default: T) -> T?,
    setter: (key: String, value: T) -> SharedPreferences.Editor
) = object : ReadWriteProperty<Any, T> {

    private inline val KProperty<*>.key: String
        get() = key ?: name

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return getter(property.key, default) ?: default
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        setter(property.key, value).apply()
    }
}
