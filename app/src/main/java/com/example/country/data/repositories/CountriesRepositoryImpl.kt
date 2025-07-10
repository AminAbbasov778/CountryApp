package com.example.country.data.repositories

import android.util.Log
import com.example.country.data.local.CountrySelectionDataSource
import com.example.country.data.model.Country
import com.example.country.data.remote.RequestService
import com.example.country.domain.interfaces.CountriesRepository
import com.example.country.domain.model.CountryModel
import retrofit2.Response
import toData
import toDomain
import javax.inject.Inject


class CountriesRepositoryImpl @Inject  constructor(private val requestService: RequestService,private val dataSource: CountrySelectionDataSource) : CountriesRepository {
    override suspend fun getCountries(): Result<List<CountryModel>> {
     return   try {
            val response = requestService.getCountries()
             if (response.isSuccessful) {
                response.body()?.let {Result.success(it.map {country -> country.toDomain() })  }
                 ?: Result.failure(Exception("Response body is null"))
            } else {
                Result.failure(Exception(response.message()))
            }
        }catch (e: Exception){
         Log.e("yoxla1", e.localizedMessage.toString(), )
             Result.failure(e)

        }
    }
    override  fun getSelectedCountry(): CountryModel? = dataSource.getSelected()?.toDomain()
    override  fun setSelectedCountry(country: CountryModel) = dataSource.setSelected(country.toData())
    override  fun clearSelectedCountry() = dataSource.clearSelected()
}