package android.dev.kalmurzaeff.newswave.ui.adapters

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.ItemNewsBinding
import android.dev.kalmurzaeff.newswave.ui.model.NewsDisplayModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.Locale

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/9/2023
 */

class NewsAdapter :
    ListAdapter<NewsDisplayModel, NewsAdapter.NewsViewHolder>(NewsDiffUtil()) {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(news: NewsDisplayModel) {
            binding.apply {
                tvTitle.text = news.title
                val categories =
                    news.category?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                tvCategory.text = categories
                Glide.with(root).load(news.imageUri).placeholder(R.drawable.placeholder)
                    .into(ivNews)

                root.setOnClickListener {
                    news.onNewsClicked.invoke()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class NewsDiffUtil : DiffUtil.ItemCallback<NewsDisplayModel>() {
        override fun areItemsTheSame(
            oldItem: NewsDisplayModel,
            newItem: NewsDisplayModel
        ): Boolean {
            return oldItem.articleId == newItem.articleId
        }

        override fun areContentsTheSame(
            oldItem: NewsDisplayModel,
            newItem: NewsDisplayModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}