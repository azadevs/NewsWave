package android.dev.kalmurzaeff.newswave.ui.settings

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.data.local.preferences.NewsPreferences
import android.dev.kalmurzaeff.newswave.databinding.FragmentSettingsBinding
import android.dev.kalmurzaeff.newswave.utils.Constants.DARK_MODE
import android.dev.kalmurzaeff.newswave.utils.Constants.LIGHT_MODE
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/9/2023
 */

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var preferences: NewsPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSettingsBinding.bind(view)

        binding.btnLanguage.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_languageFragment)
        }

        binding.switchTheme.isChecked = preferences.theme

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            binding.switchTheme.text = updateThemeTitle(isChecked)
            updateTheme(isChecked)
        }
    }

    private fun updateThemeTitle(boolean: Boolean): String {
        return if (boolean) {
            getString(R.string.text_dark_mode)
        } else {
            getString(R.string.text_light_mode)
        }
    }

    private fun updateTheme(isDarkMode: Boolean) {
        val theme = if (isDarkMode) {
            DARK_MODE
        } else {
            LIGHT_MODE
        }
        AppCompatDelegate.setDefaultNightMode(theme)
        preferences.theme = isDarkMode
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}