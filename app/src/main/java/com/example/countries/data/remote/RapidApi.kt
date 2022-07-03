package com.example.countries.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RapidApi {
    @GET("/countries")
    suspend fun getAllCountries()

    @GET("/countries")
    suspend fun getSelectedCountry(
        @Query("code") code: String
    )
}