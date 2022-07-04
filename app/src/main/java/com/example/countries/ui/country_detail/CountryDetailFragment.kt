package com.example.countries.ui.country_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.countries.MainActivity
import com.example.countries.databinding.FragmentCountryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryDetailBinding.inflate(inflater,container,false)
        (activity as MainActivity).hideNavigationBar()
        handleAppBarClickEvents()
        return binding.root
    }

    private fun handleAppBarClickEvents(){
        binding.backButtonDetail.setOnClickListener{
            (activity as MainActivity).showNavigationBar()
            findNavController().popBackStack()
        }
    }

}