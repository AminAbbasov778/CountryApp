package com.example.country.domain.interfaces

import com.example.country.data.model.Country
import com.example.country.domain.model.CountryModel

interface CountriesRepository {
  suspend fun getCountries(): Result<List<CountryModel>>
   fun getSelectedCountry(): CountryModel?
   fun setSelectedCountry(country: CountryModel)
   fun clearSelectedCountry()
}