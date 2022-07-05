package com.example.countries.ui.all_countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.domain.model.Country
import com.example.countries.domain.repository.DatabaseRepository
import com.example.countries.domain.repository.RemoteDataRepository
import com.example.countries.util.dispatcher_provider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCountriesViewModel @Inject constructor(
    private val remoteDataRepository: RemoteDataRepository,
    private val databaseRepository: DatabaseRepository,
    private val dispatcherProvider: DispatcherProvider
): ViewModel(){
    private var _getCountriesJob: Job? = null
    private var _addCountryJob: Job? = null
    private var _deleteCountryJob: Job? = null
    private var _countries = MutableLiveData<List<Country>>()
    var apiGetCountryLoading = MutableLiveData(false)
    val countries: LiveData<List<Country>> = _countries

    val getSelectedCountries: LiveData<List<Country>> = databaseRepository.readAllData()

    fun getCountries(){
        _getCountriesJob?.cancel()
        _getCountriesJob = viewModelScope.launch {
            remoteDataRepository.getAllCountries().collect{ res ->
                res.onSuccess {
                    apiGetCountryLoading.value = false
                    _countries.value = it.data
                }
                res.onLoading {
                    apiGetCountryLoading.value = true
                }
            }
        }
    }

    fun addCountry(country: Country){
        _addCountryJob?.cancel()
        _addCountryJob = viewModelScope.launch(dispatcherProvider.io) {
            databaseRepository.addCountry(country)
        }
    }

    fun deleteCountry(country: Country){
        _deleteCountryJob?.cancel()
        _deleteCountryJob = viewModelScope.launch(dispatcherProvider.io) {
            databaseRepository.deleteCountry(country)
        }
    }

}






















