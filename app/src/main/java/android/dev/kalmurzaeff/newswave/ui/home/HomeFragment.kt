package android.dev.kalmurzaeff.newswave.ui.home

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.data.local.preferences.NewsPreferences
import android.dev.kalmurzaeff.newswave.databinding.FragmentHomeBinding
import android.dev.kalmurzaeff.newswave.ui.adapters.CategoryNewsAdapter
import android.dev.kalmurzaeff.newswave.ui.adapters.NewsAdapter
import android.dev.kalmurzaeff.newswave.utils.LocaleHelper
import android.dev.kalmurzaeff.newswave.utils.State
import android.dev.kalmurzaeff.newswave.utils.extension.UiExtensions.addOnTabSelected
import android.dev.kalmurzaeff.newswave.utils.extension.UiExtensions.inVisible
import android.dev.kalmurzaeff.newswave.utils.extension.UiExtensions.visible
import android.os.Bundle
import android.view.View
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
import javax.inject.Inject


/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/2/2023
 */

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var preferences: NewsPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!preferences.isHave) {
            findNavController().navigate(R.id.action_homeFragment_to_onboardingFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)

        configureCategoryState()

        configureRecommendationState()

        configureTabs()

        binding.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        navigateToArticleFragment()

    }

    private fun configureRecommendationState() = with(binding) {

        val recommendationNewsAdapter = NewsAdapter()

        rvRecommendation.adapter = recommendationNewsAdapter

        viewModel.stateRecommendation.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state ->
                when (state) {
                    State.Empty -> {
                        progressBarCategory.visible()
                    }

                    is State.Error -> {
                        progressBarRecommendation.inVisible()
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }

                    State.Loading -> {
                        progressBarRecommendation.visible()
                        rvRecommendation.inVisible()
                    }

                    is State.Success -> {
                        recommendationNewsAdapter.submitList(state.data)
                        progressBarRecommendation.inVisible()
                        rvRecommendation.visible()
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun configureCategoryState() = with(binding) {
        val categoryNewsAdapter = CategoryNewsAdapter()
        rvCategory.adapter = categoryNewsAdapter
        viewModel.stateCategory.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .distinctUntilChanged()
            .onEach { state ->
                when (state) {
                    State.Empty -> {
                        categoryNewsAdapter.submitList(emptyList())
                        progressBarCategory.visible()
                    }

                    is State.Error -> {
                        progressBarCategory.inVisible()
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }

                    State.Loading -> {
                        binding.progressBarCategory.visible()
                        rvCategory.inVisible()
                    }

                    is State.Success -> {
                        categoryNewsAdapter.submitList(state.data)
                        progressBarCategory.inVisible()
                        rvCategory.visible()
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun navigateToArticleFragment() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actionItemClicked.collectLatest {
                val actionHomeFragmentToArticleFragment =
                    HomeFragmentDirections.actionHomeFragmentToArticleFragment(it)
                findNavController().navigate(actionHomeFragmentToArticleFragment)
            }
        }
    }

    private fun configureTabs() {

        val translatableCategories = resources.getStringArray(R.array.categories)

        val categories = resources.getStringArray(R.array.categories_server)

        binding.apply {
            for (element in translatableCategories) {
                tab.addTab(tab.newTab().setText(element))
            }
        }

        var language = LocaleHelper.getLanguage(requireContext())
        if (language!!.isEmpty()) {
            language = "en"
        }

        viewModel.getRecommendationNews(language)

        viewModel.getNewsByCategory(categories[0].lowercase(), language)

        binding.tab.addOnTabSelected {
            viewModel.getNewsByCategory(
                categories[binding.tab.selectedTabPosition].lowercase(),
                language
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}