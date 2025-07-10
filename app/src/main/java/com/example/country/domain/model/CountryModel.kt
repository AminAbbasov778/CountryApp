package com.example.country.domain.model

import com.example.country.data.model.Flags
import com.example.country.data.model.Name
import com.google.gson.annotations.SerializedName

class CountryModel( val flags: FlagsModel?,
                    val name: NameModel?) {
}