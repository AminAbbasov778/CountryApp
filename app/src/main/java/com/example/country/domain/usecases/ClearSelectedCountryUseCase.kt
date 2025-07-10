package com.example.country.domain.usecases

import com.example.country.domain.interfaces.CountriesRepository
import javax.inject.Inject

class ClearSelectedCountryUseCase @Inject constructor (private val repository: CountriesRepository) {
     operator fun invoke() {
        repository.clearSelectedCountry()
    }
}
