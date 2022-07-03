package com.example.countries.ui.all_countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.databinding.FragmentAllCountriesBinding
import com.example.countries.ui.adapter.CountriesRecyclerAdapter

class AllCountriesFragment : Fragment() {

    private lateinit var binding: FragmentAllCountriesBinding
    private val recyclerAdapter = CountriesRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCountriesBinding.inflate(inflater, container, false)
        configureRecyclerView()
        return binding.root
    }

    private fun configureRecyclerView(){
        binding.countriesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.countriesRecyclerView.adapter = recyclerAdapter
    }

}