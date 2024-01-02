package android.dev.kalmurzaeff.newswave.utils.extension

import android.view.View
import com.google.android.material.tabs.TabLayout

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/7/2023
 */

object UiExtensions {

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.inVisible() {
        visibility = View.INVISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }


    fun TabLayout.addOnTabSelected(onTabSelected: (tab: TabLayout.Tab) -> Unit) {
        this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                onTabSelected(tab ?: newTab())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}