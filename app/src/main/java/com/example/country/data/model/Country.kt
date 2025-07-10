package com.example.country.data.model


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("flags")
    val flags: Flags?,
    @SerializedName("name")
    val name: Name?
)