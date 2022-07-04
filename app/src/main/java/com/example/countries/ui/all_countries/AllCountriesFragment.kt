package com.example.countries.ui.all_countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.databinding.FragmentAllCountriesBinding
import com.example.countries.domain.model.Country
import com.example.countries.ui.adapter.CountriesRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllCountriesFragment: Fragment() {
    private lateinit var binding: FragmentAllCountriesBinding
    private val recyclerAdapter = CountriesRecyclerAdapter()
    private val allCountriesViewModel: AllCountriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCountriesBinding.inflate(inflater, container, false)
        configureRecyclerView()
        initViewModel()
        return binding.root
    }

    private fun initViewModel(){
        allCountriesViewModel.getCountries()
        observeLoading()
        val countryObserver = Observer<List<Country>> { list ->
            recyclerAdapter.setCountryListData(list)
            recyclerAdapter.notifyDataSetChanged()
        }
        allCountriesViewModel.countries.observe(viewLifecycleOwner,countryObserver)
    }

    private fun observeLoading(){
        val loadingObserver = Observer<Boolean>{
            when (it){
                true -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.countriesText.visibility = View.INVISIBLE
                    binding.countriesRecyclerView.visibility = View.INVISIBLE
                }
                false -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.countriesText.visibility = View.VISIBLE
                    binding.countriesRecyclerView.visibility = View.VISIBLE
                }
            }
        }
        allCountriesViewModel.apiGetCountryLoading.observe(viewLifecycleOwner,loadingObserver)
    }

    private fun configureRecyclerView(){
        binding.countriesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.countriesRecyclerView.adapter = recyclerAdapter
    }

}























