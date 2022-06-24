package org.d3if2103.mitibleday.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import org.d3if2103.mitibleday.R
import org.d3if2103.mitibleday.databinding.FragmentAboutBinding
import org.d3if2103.mitibleday.model.TentangAplikasi
import org.d3if2103.mitibleday.network.ApiStatus
import org.d3if2103.mitibleday.network.ZakatApi

class AboutFragment : Fragment(){
    private val viewModel: AboutViewModel by lazy {
        ViewModelProvider(this)[AboutViewModel::class.java]
    }

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStatus().observe(viewLifecycleOwner){
            updateProgress(it)
        }
        viewModel.getTentangAplikasiZakat().observe(viewLifecycleOwner){
            tentangAplikasiZakatFitrah(it)
        }
        Glide.with(binding.imageView)
            .load(ZakatApi.getImageUrl())
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.imageView)
    }

    private fun tentangAplikasiZakatFitrah(tentangAplikasiZakatFitrah: TentangAplikasi?) {
        if (tentangAplikasiZakatFitrah != null) {
            binding.aboutMe.text = tentangAplikasiZakatFitrah.tentangAplikasi
        }
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}