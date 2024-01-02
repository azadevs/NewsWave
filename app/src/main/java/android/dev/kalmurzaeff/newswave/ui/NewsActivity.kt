package android.dev.kalmurzaeff.newswave.ui

import android.content.Context
import android.dev.kalmurzaeff.newswave.R
import android.dev.kalmurzaeff.newswave.databinding.ActivityNewsBinding
import android.dev.kalmurzaeff.newswave.utils.LocaleHelper
import android.dev.kalmurzaeff.newswave.utils.extension.UiExtensions.gone
import android.dev.kalmurzaeff.newswave.utils.extension.UiExtensions.visible
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var controller: NavController
    private lateinit var listener: NavController.OnDestinationChangedListener


    override fun attachBaseContext(newBase: Context?) {
        LocaleHelper.onAttach(newBase!!)
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        controller = navHostFragment.navController

        binding.bottomNav.setupWithNavController(controller)

        listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.settingsFragment -> binding.bottomNav.visible()
                R.id.homeFragment -> binding.bottomNav.visible()
                R.id.categoriesFragment -> binding.bottomNav.visible()
                R.id.bookmarksFragment -> binding.bottomNav.visible()
                else -> binding.bottomNav.gone()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        controller.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        controller.removeOnDestinationChangedListener(listener)
    }

    fun restart() {
        recreate()
    }
}


