package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.countries.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.fragment))
    }

    fun hideNavigationBar(){
        binding.bottomNavigationView.visibility = View.INVISIBLE
        binding.bottomNavigationView.isEnabled = false
    }
    fun showNavigationBar(){
        binding.bottomNavigationView.visibility = View.VISIBLE
        binding.bottomNavigationView.isEnabled = true
    }
}




























