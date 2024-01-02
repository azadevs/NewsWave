package android.dev.kalmurzaeff.newswave.ui.adapters

import android.dev.kalmurzaeff.newswave.databinding.ItemNewsTopicBinding
import android.dev.kalmurzaeff.newswave.utils.FavouriteTopicsData.getCategories
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by : KalmurzaeffDev_A
 * Date : 10/29/2023
 */

class NewsTopicsAdapter(
    private val topics: List<String>
) : RecyclerView.Adapter<NewsTopicsAdapter.NewsTopicsViewHolder>() {

    private val selectedTopics = mutableListOf<String>()
    private val topicsWithText = getCategories()

    inner class NewsTopicsViewHolder(private val binding: ItemNewsTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(topic: String) = with(binding) {
            checkbox.text = topic
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedTopics.add(topicsWithText[adapterPosition])
                } else {
                    selectedTopics.remove(topicsWithText[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsTopicsViewHolder {
        return NewsTopicsViewHolder(
            ItemNewsTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = topics.size

    override fun onBindViewHolder(holder: NewsTopicsViewHolder, position: Int) {
        holder.bind(topics[position])
    }

    fun getSelectedTopics() = selectedTopics
}