package com.example.countries.ui.saved_countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.local.CountryDatabase
import com.example.countries.domain.model.Country
import com.example.countries.domain.repository.DatabaseRepository
import com.example.countries.util.dispatcher_provider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedCountriesViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val dispatcherProvider: DispatcherProvider
): ViewModel(){
    private var _addCountryJob: Job? = null

    val getSelectedCountries: LiveData<List<Country>> = databaseRepository.readAllData()

    fun deleteCountry(country: Country){
        _addCountryJob?.cancel()
        _addCountryJob = viewModelScope.launch(dispatcherProvider.io) {
            databaseRepository.deleteCountry(country)
        }
    }
}