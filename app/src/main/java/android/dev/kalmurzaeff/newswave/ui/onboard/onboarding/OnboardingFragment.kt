package android.dev.kalmurzaeff.newswave.ui.onboard.onboarding

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.FragmentOnboardingBinding
import android.dev.kalmurzaeff.newswave.ui.adapters.OnboardingAdapter
import android.dev.kalmurzaeff.newswave.ui.onboard.onboarding.transformer.CardTransformer
import android.dev.kalmurzaeff.newswave.ui.onboard.onboarding.transformer.HorizontalMarginItemDecoration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created by : KalmurzaeffDev_A
 * Date : 10/28/2023
 */

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentOnboardingBinding.bind(view)

        configurePagerWithTab()

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_welcomeFragment)
        }
    }

    private fun configurePagerWithTab() = with(binding) {
        val onboardingAdapter = OnboardingAdapter()
        viewPager.adapter = onboardingAdapter
        TabLayoutMediator(
            tab, viewPager
        ) { _, _ -> }.attach()
        viewPager.setPageTransformer(CardTransformer(requireContext()))
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(), R.dimen.horizontal_margin_pager
        )
        viewPager.addItemDecoration(itemDecoration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun getImages() = arrayListOf(
            R.drawable.content,
            R.drawable.online_article,
            R.drawable.online_everywhere,
            R.drawable.mobile_life,
            R.drawable.mobile_devices
        )
    }

}