package com.example.country.data.local

import com.example.country.data.model.Country
import com.example.country.domain.model.CountryModel
import javax.inject.Inject

class CountrySelectionDataSource @Inject constructor() {

    private var selectedCountry: Country? = null

    fun getSelected(): Country? = selectedCountry

    fun setSelected(country: Country) {
        selectedCountry = country
    }

    fun clearSelected() {
        selectedCountry = null
    }
}
