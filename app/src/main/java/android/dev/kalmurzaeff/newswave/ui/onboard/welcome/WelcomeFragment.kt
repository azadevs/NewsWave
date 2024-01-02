package android.dev.kalmurzaeff.newswave.ui.onboard.welcome

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.FragmentWelcomeBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * Created by : KalmurzaeffDev_A
 * Date : 10/28/2023
 */

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentWelcomeBinding.bind(view)

        binding.btnStarted.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_favouriteTopicsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}