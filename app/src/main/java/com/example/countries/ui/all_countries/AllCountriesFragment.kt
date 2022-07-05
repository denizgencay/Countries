package com.example.countries.ui.all_countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.MainActivity
import com.example.countries.R
import com.example.countries.databinding.FragmentAllCountriesBinding
import com.example.countries.domain.model.Country
import com.example.countries.ui.adapter.CountriesRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCountriesFragment: Fragment() {
    private lateinit var binding: FragmentAllCountriesBinding
    private val recyclerAdapter = CountriesRecyclerAdapter()
    private val allCountriesViewModel: AllCountriesViewModel by viewModels()
    private var countryList: List<Country> = listOf()
    private var savedCountries: List<Country> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCountriesBinding.inflate(inflater, container, false)
        configureRecyclerView()
        handleRecyclerViewClicks()
        initViewModel()
        return binding.root
    }

    private fun initViewModel(){
        allCountriesViewModel.getCountries()
        observeLoading()
        val countryObserver = Observer<List<Country>> { list ->
            countryList = list
            recyclerAdapter.setCountryListData(list)
            recyclerAdapter.notifyDataSetChanged()
        }
        allCountriesViewModel.countries.observe(viewLifecycleOwner,countryObserver)
        val savedCountriesObserver = Observer<List<Country>>{
            savedCountries = it
            recyclerAdapter.setSelectedCountryListData(it)
        }
        allCountriesViewModel.getSelectedCountries.observe(viewLifecycleOwner,savedCountriesObserver)
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

    private fun handleRecyclerViewClicks(){
        recyclerAdapter.setOnCardClickedListener(object: CountriesRecyclerAdapter.OnCardListener{
            override fun onCardClicked(position: Int) {
                view?.let { Navigation.findNavController(it).navigate(R.id.action_allCountriesFragment_to_countryDetailFragment) }
            }

            override fun onClicked(position: Int) {
                val country = Country(
                    countryList[position].name,
                    countryList[position].code,
                    true
                )
                for (savedCountry in savedCountries){
                    if (countryList[position].name == savedCountry.name){
                        allCountriesViewModel.deleteCountry(countryList[position])
                        recyclerAdapter.notifyItemChanged(position)
                        return
                    }
                }
                allCountriesViewModel.addCountry(country)
                recyclerAdapter.notifyItemChanged(position)

            }
        })
    }

}























