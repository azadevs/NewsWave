package android.dev.kalmurzaeff.newswave.application

import android.app.Application
import android.content.Context
import android.dev.kalmurzaeff.newswave.data.local.preferences.NewsPreferences
import android.dev.kalmurzaeff.newswave.utils.Constants.DARK_MODE
import android.dev.kalmurzaeff.newswave.utils.Constants.LIGHT_MODE
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import logcat.AndroidLogcatLogger
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/4/2023
 */

@HiltAndroidApp
class NewsApplication : Application() {

    @Inject
    lateinit var preferences: NewsPreferences

    override fun onCreate() {
        super.onCreate()
        AndroidLogcatLogger.installOnDebuggableApp(this)
        if (preferences.theme) {
            AppCompatDelegate.setDefaultNightMode(DARK_MODE)
        } else {
            AppCompatDelegate.setDefaultNightMode(LIGHT_MODE)
        }
    }



}