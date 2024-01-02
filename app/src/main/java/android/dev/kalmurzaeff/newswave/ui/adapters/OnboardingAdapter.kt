package android.dev.kalmurzaeff.newswave.ui.adapters

import android.dev.kalmurzaeff.newswave.databinding.ItemNewsOnboardingBinding
import android.dev.kalmurzaeff.newswave.ui.onboard.onboarding.OnboardingFragment.Companion.getImages
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by : KalmurzaeffDev_A
 * Date : 11/1/2023
 */

class OnboardingAdapter : RecyclerView.Adapter<OnboardingAdapter.OnboardingVh>() {

    private val images = getImages()

    class OnboardingVh(private val binding: ItemNewsOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageUrl: Int) {
            binding.imageView.setImageResource(imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingVh {
        return OnboardingVh(
            ItemNewsOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: OnboardingVh, position: Int) {
        holder.onBind(images[position])
    }
}