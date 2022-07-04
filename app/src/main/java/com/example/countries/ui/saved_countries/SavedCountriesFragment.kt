package com.example.countries.ui.saved_countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.MainActivity
import com.example.countries.databinding.FragmentSavedCountriesBinding
import com.example.countries.ui.adapter.CountriesRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SavedCountriesFragment : Fragment() {

    private lateinit var binding: FragmentSavedCountriesBinding
    private var recyclerAdapter = CountriesRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedCountriesBinding.inflate(inflater,container,false)
        configureRecyclerView()
        return binding.root
    }

    private fun configureRecyclerView(){
        binding.savedCountriesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.savedCountriesRecyclerView.adapter = recyclerAdapter
    }
}