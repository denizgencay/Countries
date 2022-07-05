package com.example.countries.domain.repository

import androidx.lifecycle.LiveData
import com.example.countries.domain.model.Country

interface DatabaseRepository {

    fun readAllData(): LiveData<List<Country>>
    suspend fun addCountry(country: Country)
}