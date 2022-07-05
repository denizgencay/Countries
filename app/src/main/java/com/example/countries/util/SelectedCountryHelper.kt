package com.example.countries.util

import com.example.countries.domain.model.Country


class SelectedCountryHelper {
    fun isSelected(selectedCountries: List<Country>,country: Country): Boolean{
        for (selectedCountry in selectedCountries){
            if (selectedCountry.code == country.code){
                return true
            }
        }
        return false
    }
}