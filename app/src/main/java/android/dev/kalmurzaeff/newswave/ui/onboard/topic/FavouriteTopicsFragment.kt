package android.dev.kalmurzaeff.newswave.ui.onboard.topic

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.data.local.preferences.NewsPreferences
import android.dev.kalmurzaeff.newswave.databinding.FragmentFavouriteTopicsBinding
import android.dev.kalmurzaeff.newswave.ui.adapters.NewsTopicsAdapter
import android.dev.kalmurzaeff.newswave.utils.FavouriteTopicsData.getTopicsWithEmoji
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 10/29/2023
 */

@AndroidEntryPoint
class FavouriteTopicsFragment : Fragment(R.layout.fragment_favourite_topics) {

    private var _binding: FragmentFavouriteTopicsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var preferences: NewsPreferences

    @Inject
    lateinit var gson: Gson

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouriteTopicsBinding.bind(view)

        val topicsAdapter = NewsTopicsAdapter(getTopicsWithEmoji(requireContext()))
        binding.rvCategory.adapter = topicsAdapter

        val selectedTopics = topicsAdapter.getSelectedTopics()

        binding.btnNext.setOnClickListener {
            if (selectedTopics.isEmpty()) {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.select_topics_message), Toast.LENGTH_SHORT
                ).show()
            } else {
                preferences.categories = gson.toJson(selectedTopics)
                preferences.isHave = true
                findNavController().navigate(R.id.action_favouriteTopicsFragment_to_homeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}