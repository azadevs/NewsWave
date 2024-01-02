package android.dev.kalmurzaeff.newswave.ui.onboard.onboarding.transformer

import android.content.Context
import android.dev.kalmurzaeff.newswave.R
import android.view.View
import androidx.viewpager2.widget.ViewPager2


class CardTransformer(context: Context) : ViewPager2.PageTransformer {
    private val nextItemVisiblePx =
        context.resources.getDimension(R.dimen.viewpager_next_item_visible)
    private val currentItemHorizontalMarginPx =
        context.resources.getDimension(R.dimen.horizontal_margin_pager)
    private val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

    override fun transformPage(page: View, position: Float) {
        page.translationX = -pageTranslationX * position  /*dpHeight/(100)*/
        // Next line scales the item's height. You can remove it if you don't want this effect
        page.scaleY = 1 - (0.12f * kotlin.math.abs(position))
        // If you want a fading effect uncomment the next line:
        page.alpha = 0.5f + (1 - kotlin.math.abs(position))
    }

}