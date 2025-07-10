package com.example.country.domain.usecases

import com.example.country.domain.model.CountryModel
import javax.inject.Inject

class SearchCountriesUseCase @Inject constructor() {
    operator fun invoke(
        countries: List<CountryModel>,
        query: String
    ): List<CountryModel> {
        return if (query.isBlank()) {
            countries
        } else {
            countries.filter {
                it.name?.common?.contains(query.trim(), ignoreCase = true) == true
            }
        }
    }
}