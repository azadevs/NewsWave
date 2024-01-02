package android.dev.kalmurzaeff.newswave.ui.adapters

import android.content.Context
import android.dev.kalmurzaeff.newswave.databinding.ItemNewsTopicBinding
import android.dev.kalmurzaeff.newswave.utils.FavouriteTopicsData.getCategories
import android.dev.kalmurzaeff.newswave.utils.FavouriteTopicsData.getTopicsWithEmoji
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/17/2023
 */

class CategoriesAdapter(
    context: Context
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var categoriesWithEmoji: List<String> = getTopicsWithEmoji(context)
    private var defaultSelectedCategories: List<String> = emptyList()
    private val selectedCategories = ArrayList<String>()

    private val categoriesWithText = getCategories()

    inner class CategoriesViewHolder(private val binding: ItemNewsTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: String) = with(binding) {
            checkbox.text = category

            if (defaultSelectedCategories.contains(categoriesWithText[adapterPosition])) {
                checkbox.isChecked = true
            }

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedCategories.add(categoriesWithText[adapterPosition])
                } else {
                    selectedCategories.remove(categoriesWithText[adapterPosition])
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemNewsTopicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categoriesWithEmoji.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.onBind(categoriesWithEmoji[position])
    }

    fun submitList(defaultSelectedCategories: List<String>) {
        this.defaultSelectedCategories = defaultSelectedCategories
        selectedCategories.addAll(defaultSelectedCategories)
        notifyDataSetChanged()
    }

    fun getSelectedTopics() = selectedCategories
}