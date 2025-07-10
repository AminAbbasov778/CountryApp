package com.example.country.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.country.domain.model.CountryModel
import com.example.country.domain.usecases.ClearSelectedCountryUseCase
import com.example.country.domain.usecases.GetSelectedCountryUseCase
import com.example.country.domain.usecases.SetSelectedCountryUseCase
import com.example.country.presentation.mappers.toDomain
import com.example.country.presentation.mappers.toUi
import com.example.country.presentation.models.CountryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getSelectedCountryUseCase: GetSelectedCountryUseCase,
                                        private val clearSelectedCountryUseCase: ClearSelectedCountryUseCase,) : ViewModel() {
    private val _selectedCountry = MutableLiveData<CountryUi?>()
    val selectedCountry: LiveData<CountryUi?> get()  = _selectedCountry

     fun getSelectedCountry() {
         val country = getSelectedCountryUseCase()
         _selectedCountry.value = country?.let { it.toUi() }
     }


    fun clearSelection() {
            clearSelectedCountryUseCase()
            _selectedCountry.value = null
    }

}