package com.example.countries.ui.country_detail

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.countries.MainActivity
import com.example.countries.databinding.FragmentCountryDetailBinding
import com.example.countries.domain.model.CountryDetail
import com.example.countries.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    val args: CountryDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentCountryDetailBinding
    private val countryDetailViewModel: CountryDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryDetailBinding.inflate(inflater,container,false)
        (activity as MainActivity).hideNavigationBar()
        handleAppBarClickEvents()
        args.countryCode?.let { initViewModel(it) }
        return binding.root
    }

    private fun handleAppBarClickEvents(){
        binding.backButtonDetail.setOnClickListener{
            (activity as MainActivity).showNavigationBar()
            findNavController().popBackStack()
        }
    }

    private fun initViewModel(countryCode: String){
        countryDetailViewModel.getSelectedCountry(countryCode)
        val countryObserver = Observer<CountryDetail> { country ->
            binding.countryCode.text = country.code
            binding.countryNameDetail.text = country.name
            binding.imageViewFlag.loadUrl(country.flagImageUri?.replace("http","https"))
        }
        countryDetailViewModel.country.observe(viewLifecycleOwner,countryObserver)

    }

}
















