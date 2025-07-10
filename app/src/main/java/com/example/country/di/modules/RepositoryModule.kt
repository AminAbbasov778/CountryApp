package com.example.country.di.modules

import com.example.country.data.local.CountrySelectionDataSource
import com.example.country.data.remote.RequestService
import com.example.country.data.repositories.CountriesRepositoryImpl
import com.example.country.domain.interfaces.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCountriesRepository(requestService: RequestService,dataSource: CountrySelectionDataSource): CountriesRepository = CountriesRepositoryImpl(requestService,dataSource)
}