package com.example.countries.data.remote

import com.example.countries.domain.model.CountriesResponse
import com.example.countries.domain.model.CountryDetailResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RapidApi {
    @GET("/countries")
    suspend fun getAllCountries(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Query("limit") limit: String
    ): CountriesResponse

    @GET("/countries")
    suspend fun getSelectedCountry(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Path("countryCode") countryCode: String
    ): CountryDetailResponse
}