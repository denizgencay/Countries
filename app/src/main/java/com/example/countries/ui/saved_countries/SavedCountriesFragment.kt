package com.example.countries.ui.saved_countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.MainActivity
import com.example.countries.R
import com.example.countries.databinding.FragmentSavedCountriesBinding
import com.example.countries.domain.model.Country
import com.example.countries.ui.adapter.CountriesRecyclerAdapter
import com.example.countries.ui.all_countries.AllCountriesFragmentDirections
import com.example.countries.ui.all_countries.AllCountriesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SavedCountriesFragment : Fragment() {

    private lateinit var binding: FragmentSavedCountriesBinding
    private var recyclerAdapter = CountriesRecyclerAdapter()
    private val savedCountriesViewModel: SavedCountriesViewModel by viewModels()
    private var savedCountries: List<Country> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedCountriesBinding.inflate(inflater,container,false)
        configureRecyclerView()
        handleRecyclerViewClicks()
        initViewModel()
        return binding.root
    }

    private fun configureRecyclerView(){
        binding.savedCountriesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.savedCountriesRecyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel(){
        val savedCountriesObserver = Observer<List<Country>>{
            savedCountries = it
            recyclerAdapter.setCountryListData(it)
            recyclerAdapter.notifyDataSetChanged()
        }
        savedCountriesViewModel.getSelectedCountries.observe(viewLifecycleOwner,savedCountriesObserver)
    }

    private fun handleRecyclerViewClicks(){
        recyclerAdapter.setOnCardClickedListener(object: CountriesRecyclerAdapter.OnCardListener{
            override fun onCardClicked(position: Int) {
                val action = SavedCountriesFragmentDirections.actionSavedCountriesFragmentToCountryDetailFragment()
                action.countryCode = savedCountries[position].code
                view!!.findNavController().navigate(action)
            }
            override fun onClicked(position: Int) {
                savedCountriesViewModel.deleteCountry(savedCountries[position])
            }
        })
    }
}