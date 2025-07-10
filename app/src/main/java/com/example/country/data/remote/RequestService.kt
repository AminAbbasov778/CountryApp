package com.example.country.data.remote

import com.example.country.data.model.Country
import okhttp3.Response
import retrofit2.http.GET

interface RequestService {


    @GET("all?fields=name,flags")
    suspend fun getCountries(): retrofit2.Response<List<Country>>

}