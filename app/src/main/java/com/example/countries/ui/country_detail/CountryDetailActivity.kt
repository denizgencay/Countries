package com.example.countries.ui.country_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countries.R
import com.example.countries.databinding.ActivityCountryDetailBinding

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}