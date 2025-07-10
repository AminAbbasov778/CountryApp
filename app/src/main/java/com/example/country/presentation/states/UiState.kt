package com.example.country.presentation.states

import com.example.country.presentation.models.CountryUi

sealed class UiState  {
    data class Success(val data: List<CountryUi>) : UiState()
    data class Error(val message: Int) : UiState()
    object Loading : UiState()
}