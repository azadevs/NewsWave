package android.dev.kalmurzaeff.newswave.ui.article

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.FragmentArticleBinding
import android.dev.kalmurzaeff.newswave.model.News
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/19/2023
 */

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArticleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentArticleBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.ivShare.setOnClickListener {
            Toast.makeText(requireContext(), "Share news", Toast.LENGTH_SHORT).show()
        }

        viewModel.articleNews.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                it?.let { news ->
                    configureUi(news)
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun configureUi(news: News) {
        var isFavourite = news.isFavourite
        Glide.with(binding.root).load(news.imageUri).placeholder(R.drawable.placeholder)
            .into(binding.ivContent)
        binding.tvContent.text = news.content
        binding.btnSource.text = news.sourceId
        binding.tvTitle.text = news.title

        changeSaveIcon(isFavourite)

        binding.ivSave.setOnClickListener {
            isFavourite = !isFavourite
            viewModel.handleFavouriteNewsItem(isFavourite)
            changeSaveIcon(isFavourite)
        }
    }

    private fun changeSaveIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.ivSave.setImageResource(R.drawable.ic_dark_bookmark_fill)
        } else {
            binding.ivSave.setImageResource(R.drawable.ic_dark_bookmark_border)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}