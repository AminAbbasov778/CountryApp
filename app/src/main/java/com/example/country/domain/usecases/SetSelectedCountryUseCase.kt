package com.example.country.domain.usecases

import com.example.country.data.model.Country
import com.example.country.domain.interfaces.CountriesRepository
import com.example.country.domain.model.CountryModel
import javax.inject.Inject

class SetSelectedCountryUseCase @Inject constructor  (private val repository: CountriesRepository) {
     operator fun invoke(country: CountryModel) {
        repository.setSelectedCountry(country)
    }
}
