package com.example.countries.domain.repository

import com.example.countries.domain.model.Country
import com.example.countries.domain.model.CountryDetail
import com.example.countries.domain.model.CountryResponse
import com.example.countries.util.Resource
import kotlinx.coroutines.flow.Flow


interface RemoteDataRepository {
    suspend fun getAllCountries(): Flow<Resource<CountryResponse>>
    suspend fun getSelectedCountry(countryCode: String): Flow<Resource<CountryDetail>>
}