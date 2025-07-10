package com.example.country.domain.usecases

import com.example.country.data.model.Country
import com.example.country.domain.interfaces.CountriesRepository
import com.example.country.domain.model.CountryModel
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(val countriesRepository: CountriesRepository) {

   suspend operator fun invoke(): Result<List<CountryModel>>  = countriesRepository.getCountries()
}