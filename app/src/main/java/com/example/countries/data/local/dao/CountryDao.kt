package com.example.countries.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.countries.domain.model.Country

@Dao
interface CountryDao {
    @Query("SELECT * FROM country_table")
    fun getLikedCountries(): LiveData<List<Country>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCountry(country: Country)

    @Delete
    fun delete(country: Country)
}