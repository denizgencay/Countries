package com.example.countries.di

import android.content.Context
import androidx.room.Room
import com.example.countries.data.local.CountryDatabase
import com.example.countries.data.local.dao.CountryDao
import com.example.countries.data.repository.DatabaseRepositoryImpl
import com.example.countries.domain.model.Country
import com.example.countries.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): CountryDatabase {
        return Room.databaseBuilder(
            context,
            CountryDatabase::class.java,
            "country_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        countryDatabase: CountryDatabase
    ): DatabaseRepository {
        return DatabaseRepositoryImpl(countryDatabase)
    }
}