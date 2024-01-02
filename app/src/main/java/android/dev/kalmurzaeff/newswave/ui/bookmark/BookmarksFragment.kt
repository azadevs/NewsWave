package android.dev.kalmurzaeff.newswave.ui.bookmark

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.FragmentBookmarksBinding
import android.dev.kalmurzaeff.newswave.ui.adapters.NewsAdapter
import android.dev.kalmurzaeff.newswave.utils.extension.UiExtensions.inVisible
import android.dev.kalmurzaeff.newswave.utils.extension.UiExtensions.visible
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/9/2023
 */

@AndroidEntryPoint
class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookmarksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentBookmarksBinding.bind(view)

        configureAdapterList()

        navigateToArticleFragment()

    }

    private fun navigateToArticleFragment() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actionItemClicked.collect {
                val actionToArticleFragment =
                    BookmarksFragmentDirections.actionBookmarksFragmentToArticleFragment(it)
                findNavController().navigate(actionToArticleFragment)
            }
        }
    }

    private fun configureAdapterList() {
        val adapter = NewsAdapter()
        binding.rvBookmarks.adapter = adapter
        viewModel.favouriteNews
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .distinctUntilChanged()
            .onEach { state ->
                when (state) {
                    BookmarksViewModel.BookmarksState.Empty -> {
                        binding.ltEmptyState.visible()
                        binding.rvBookmarks.inVisible()
                    }

                    is BookmarksViewModel.BookmarksState.Success -> {
                        adapter.submitList(state.data)
                        binding.ltEmptyState.inVisible()
                        binding.rvBookmarks.visible()
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}