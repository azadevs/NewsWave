package android.dev.kalmurzaeff.newswave.ui.language

import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.FragmentLanguageBinding
import android.dev.kalmurzaeff.newswave.ui.NewsActivity
import android.dev.kalmurzaeff.newswave.utils.LocaleHelper
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


/**
 * Created by : KalmurzaeffDev_A
 * Date : 12/12/2023
 */

class LanguageFragment : Fragment(R.layout.fragment_language) {

    private var _binding: FragmentLanguageBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLanguageBinding.bind(view)

        val currentLocale = LocaleHelper.getLanguage(binding.root.context)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        when (currentLocale) {
            "en" -> binding.rbEng.isChecked = true
            "ru" -> binding.rbRus.isChecked = true
        }

        binding.rbGroup.setOnCheckedChangeListener { _, checkedId ->
            val language = when (checkedId) {
                R.id.rb_eng -> "en"
                R.id.rb_rus -> "ru"
                else -> ""
            }
            if (language != currentLocale) {
                LocaleHelper.setLocale(binding.root.context, language)
                (activity as NewsActivity).recreate()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}