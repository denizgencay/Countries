package com.example.countries.ui.country_detail

import android.content.Intent
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
import com.example.countries.R
import com.example.countries.databinding.FragmentCountryDetailBinding
import com.example.countries.domain.model.Country
import com.example.countries.domain.model.CountryDetail
import com.example.countries.util.Constants.WIKI_URL
import com.example.countries.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    val args: CountryDetailFragmentArgs by navArgs()
    private var savedCountries: List<Country> = listOf()

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
        observeLoading()
        countryDetailViewModel.getSelectedCountry(countryCode)
        val countryObserver = Observer<CountryDetail> { country ->
            binding.countryCode.text = country.code
            binding.countryNameDetail.text = country.name
            binding.imageViewFlag.loadUrl(country.flagImageUri?.replace("http","https"))
            binding.wikiButton.setOnClickListener { exploreWikiData(country) }
            detectSavedState(country)
            saveCountry(country)
        }
        countryDetailViewModel.country.observe(viewLifecycleOwner,countryObserver)
        val savedCountriesObserver = Observer<List<Country>>{ savedCountries = it }
        countryDetailViewModel.getSelectedCountries.observe(viewLifecycleOwner,savedCountriesObserver)

    }

    private fun exploreWikiData(country: CountryDetail){
        val queryUrl: Uri =
            Uri.parse("${WIKI_URL}${country.wikiDataId}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        context?.startActivity(intent)
    }

    private fun detectSavedState(country: CountryDetail){
        for (savedCountry in savedCountries){
            if (country.name == savedCountry.name){
                binding.likeButtonDetail.setImageResource(R.drawable.ic_baseline_bookmark_filled)
                deleteSavedCountry(country)
            }
        }
    }

    private fun saveCountry(country: CountryDetail){
        binding.likeButtonDetail.setOnClickListener{
            countryDetailViewModel.addCountry(Country(country.name!!, country.code!!, true))
            binding.likeButtonDetail.setImageResource(R.drawable.ic_baseline_bookmark_filled)
        }
    }

    private fun deleteSavedCountry(country: CountryDetail){
        binding.likeButtonDetail.setOnClickListener{
            countryDetailViewModel.deleteCountry(Country(
                country.name!!,
                country.code!!,
                false
            ))
            binding.likeButtonDetail.setImageResource(R.drawable.ic_baseline_bookmark_un_filled)
        }
    }

    private fun observeLoading(){
        val loadingObserver = Observer<Boolean>{
            when (it){
                true -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.constraintLayout.visibility = View.INVISIBLE
                }
                false -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.constraintLayout.visibility = View.VISIBLE
                }
            }
        }
        countryDetailViewModel.apiGetCountryDetailLoading.observe(viewLifecycleOwner,loadingObserver)
    }

}
















