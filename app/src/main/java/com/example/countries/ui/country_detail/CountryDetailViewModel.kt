package com.example.countries.ui.country_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.domain.model.CountryDetail
import com.example.countries.domain.repository.RemoteDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val remoteDataRepository: RemoteDataRepository
): ViewModel() {

    private var _getCountriesJob: Job? = null
    private var _countries = MutableLiveData<CountryDetail>()
    val countries: LiveData<CountryDetail> = _countries

    fun getCountries(){
        _getCountriesJob?.cancel()
        _getCountriesJob = viewModelScope.launch {
            remoteDataRepository.getSelectedCountry("US").collect{ res ->
                res.onSuccess {
                    _countries.value = it
                }
                res.onLoading {

                }
            }
        }
    }
}