package com.example.country.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.country.R
import com.example.country.domain.usecases.GetCountriesUseCase
import com.example.country.domain.usecases.SearchCountriesUseCase
import com.example.country.domain.usecases.SetSelectedCountryUseCase
import com.example.country.presentation.mappers.toDomain
import com.example.country.presentation.mappers.toUi
import com.example.country.presentation.models.CountryUi
import com.example.country.presentation.states.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCountryViewModel @Inject constructor(
    private val setSelectedCountryUseCase: SetSelectedCountryUseCase,
    private val getCountriesUseCase: GetCountriesUseCase,
    private val searchCountriesUseCase: SearchCountriesUseCase
) : ViewModel() {

    private val _countries = MutableLiveData<UiState>()
    val countries: LiveData<UiState> get() = _countries

    private var cachedCountries: List<CountryUi> = emptyList()


    init {
        getCountries()
    }

    fun selectCountry(country: CountryUi) {
        setSelectedCountryUseCase(country.toDomain())

    }

    fun getCountries() {
        _countries.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCountriesUseCase()
            if (result.isSuccess) {
                cachedCountries = result.getOrNull()?.map { it.toUi() } ?: emptyList()
                _countries.postValue(UiState.Success(cachedCountries))
            } else {
                _countries.postValue(UiState.Error(R.string.failed_get_countries))
            }
        }
    }

    fun searchCountry(query: String) {
        val filtered = searchCountriesUseCase(cachedCountries.map { it.toDomain() }, query)
        _countries.value = UiState.Success(filtered.map { it.toUi() })
    }

}