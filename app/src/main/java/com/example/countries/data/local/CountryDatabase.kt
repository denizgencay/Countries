package com.example.countries.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.countries.data.local.dao.CountryDao
import com.example.countries.domain.model.Country

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class CountryDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDao
}