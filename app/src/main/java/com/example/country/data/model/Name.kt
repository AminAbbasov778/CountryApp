package com.example.country.data.model


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common")
    val common: String?
)