package com.example.countries.data.repository

import com.example.countries.data.remote.RapidApi
import com.example.countries.domain.model.CountriesResponse
import com.example.countries.domain.model.CountryDetailResponse
import com.example.countries.domain.repository.RemoteDataRepository
import com.example.countries.util.Constants.API_KEY
import com.example.countries.util.Constants.DATA_PER_TIME

class RemoteDataRepositoryImpl(
    private val rapidApi: RapidApi
): RemoteDataRepository {

    override suspend fun getAllCountries(): CountriesResponse {
        return rapidApi.getAllCountries(
            apiKey = API_KEY,
            limit = DATA_PER_TIME
        )
    }

    override suspend fun getSelectedCountry(countryCode: String): CountryDetailResponse {
        return rapidApi.getSelectedCountry(
            apiKey = API_KEY,
            countryCode = countryCode
        )
    }
}