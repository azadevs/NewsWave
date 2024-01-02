package android.dev.kalmurzaeff.newswave.ui.adapters

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.RowItemNewsBinding
import android.dev.kalmurzaeff.newswave.ui.model.NewsDisplayModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.Locale

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/9/2023
 */

class CategoryNewsAdapter :
    ListAdapter<NewsDisplayModel, CategoryNewsAdapter.CategoryNewsHolder>(CategoryNewsDiffUtil()) {


    inner class CategoryNewsHolder(private val binding: RowItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(news: NewsDisplayModel) {
            binding.apply {

                tvTitle.text = news.title
                val categories =
                    news.category?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                tvCategory.text = categories
                Glide.with(root).load(news.imageUri).placeholder(R.drawable.placeholder)
                    .into(ivNews)

                changeIconBookmark(news.isFavourite, ivSave)

                var isBookmarkClicked = news.isFavourite

                ivSave.setOnClickListener {
                    isBookmarkClicked = !isBookmarkClicked

                    changeIconBookmark(isBookmarkClicked, ivSave)

                    news.onBookmarkClicked?.invoke(isBookmarkClicked)
                }

                root.setOnClickListener {
                    news.onNewsClicked.invoke()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryNewsHolder {
        return CategoryNewsHolder(
            RowItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryNewsHolder, position: Int) {
        holder.onBind(getItem(position))

    }

    class CategoryNewsDiffUtil : DiffUtil.ItemCallback<NewsDisplayModel>() {
        override fun areItemsTheSame(
            oldItem: NewsDisplayModel, newItem: NewsDisplayModel
        ): Boolean {
            return oldItem.articleId == newItem.articleId
        }

        override fun areContentsTheSame(
            oldItem: NewsDisplayModel, newItem: NewsDisplayModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private fun changeIconBookmark(bool: Boolean, ivSave: ImageView) {
        ivSave.setImageResource(if (bool) R.drawable.ic_bookmark_fill else R.drawable.ic_bookmark_border)
    }
}