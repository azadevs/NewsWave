package android.dev.kalmurzaeff.newswave.ui.category

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.FragmentCategoriesBinding
import android.dev.kalmurzaeff.newswave.ui.adapters.CategoriesAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/18/2023
 */
@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private var _binding: FragmentCategoriesBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: CategoriesAdapter

    private val viewModel: CategoriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentCategoriesBinding.bind(view)

        configureAdapterList()

        viewModel.getSelectedCategories()

    }

    private fun configureAdapterList() {
        adapter = CategoriesAdapter(requireContext())
        binding.rvCategories.adapter = adapter
        viewModel.categories.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { categories ->
                adapter.submitList(categories)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onStop() {
        super.onStop()
        if (adapter.getSelectedTopics().isNotEmpty()) {
            viewModel.saveCategoriesToPreference(adapter.getSelectedTopics())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}