package android.dev.kalmurzaeff.newswave.utils

import android.content.Context
import android.dev.kalmurzaeff.newswave.R

/**
 * Created by : KalmurzaeffDev_A
 * Date : 10/29/2023
 */

object FavouriteTopicsData {
    fun getTopicsWithEmoji(context: Context): List<String> {
        return arrayListOf(
            "\uD83C\uDFC8   ${context.getString(R.string.sports)}",
            "\u200E\u200D\uD83D\uDCBC   ${context.getString(R.string.business)}",
            "✈️   ${context.getString(R.string.tourism)}",
            "\uD83C\uDF54   ${context.getString(R.string.food)}",
            "\uD83D\uDCF1   ${context.getString(R.string.technology)}",
            "⚖️   ${context.getString(R.string.politics)}",
            "\uD83C\uDF0D   ${context.getString(R.string.environment)}",
            "\uD83C\uDFA6   ${context.getString(R.string.entertainment)}",
            "\uD83D\uDC68\u200D⚕️   ${context.getString(R.string.health)}",
            "\uD83D\uDD2C   ${context.getString(R.string.science)}",
        )
    }

    fun getCategories(): List<String> {
        return arrayListOf(
            "Sports",
            "Business",
            "Tourism",
            "Food",
            "Technology",
            "Politics",
            "Environment",
            "Entertainment",
            "Health",
            "Science",
        )
    }
}