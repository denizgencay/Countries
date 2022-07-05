package com.example.countries.data.repository

import androidx.lifecycle.LiveData
import com.example.countries.data.local.CountryDatabase
import com.example.countries.data.local.dao.CountryDao
import com.example.countries.domain.model.Country
import com.example.countries.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val countryDatabase: CountryDatabase
): DatabaseRepository {

    private val countryDao = countryDatabase.countryDao()

    override fun readAllData(): LiveData<List<Country>> = countryDao.getLikedCountries()

    override suspend fun addCountry(country: Country) {
        countryDao.addCountry(country)
    }

    override suspend fun deleteCountry(country: Country) {
        countryDao.delete(country)
    }
}