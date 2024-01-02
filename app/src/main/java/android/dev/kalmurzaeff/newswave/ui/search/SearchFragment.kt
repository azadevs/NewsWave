package android.dev.kalmurzaeff.newswave.ui.search

import android.content.Context
import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.FragmentSearchBinding
import android.dev.kalmurzaeff.newswave.ui.adapters.NewsAdapter
import android.dev.kalmurzaeff.newswave.utils.LocaleHelper
import android.dev.kalmurzaeff.newswave.utils.State
import android.dev.kalmurzaeff.newswave.utils.extension.UiExtensions.inVisible
import android.dev.kalmurzaeff.newswave.utils.extension.UiExtensions.visible
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/2/2023
 */

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchBinding.bind(view)
        configureEditText()

        configureState()

        navigateToArticleFragment()
    }

    private fun configureState() = with(binding) {
        val newsAdapter = NewsAdapter()
        rvSearch.adapter = newsAdapter

        viewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle).distinctUntilChanged()
            .onEach { state ->
                when (state) {
                    is State.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        progressBar.inVisible()
                    }

                    State.Loading -> {
                        binding.progressBar.visible()
                        rvSearch.inVisible()
                    }

                    is State.Success -> {
                        newsAdapter.submitList(state.data)
                        progressBar.inVisible()
                        rvSearch.visible()
                    }

                    State.Empty -> {
                        newsAdapter.submitList(emptyList())
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun configureEditText() {
        var language = LocaleHelper.getLanguage(binding.root.context)
        if (language!!.isEmpty()) {
            language = "en"
        }
        binding.edtSearch.requestFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.showSoftInput(binding.edtSearch, InputMethodManager.SHOW_IMPLICIT)

        binding.edtSearch.setOnEditorActionListener { a, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.getSearchNews(a.text.toString(),language)
            }
            false
        }
    }

    private fun navigateToArticleFragment() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actionItemClicked.collectLatest {
                val actionToArticleFragment =
                    SearchFragmentDirections.actionSearchFragmentToArticleFragment(it)
                findNavController().navigate(actionToArticleFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}