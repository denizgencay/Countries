package com.example.countries.domain.repository

import com.example.countries.domain.model.CountriesResponse
import com.example.countries.domain.model.CountryDetailResponse

interface RemoteDataRepository {
    suspend fun getAllCountries(): CountriesResponse
    suspend fun getSelectedCountry(countryCode: String): CountryDetailResponse
}