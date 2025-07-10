package com.example.country.presentation.mappers

import com.example.country.domain.model.CountryModel
import com.example.country.domain.model.FlagsModel
import com.example.country.domain.model.NameModel
import com.example.country.presentation.models.CountryUi
import com.example.country.presentation.models.FlagsUi
import com.example.country.presentation.models.NameUi

fun CountryModel.toUi(): CountryUi{
    return CountryUi(
        name = NameUi(name?.common),
        flags = FlagsUi(flags?.png)

    )

}

fun CountryUi.toDomain(): CountryModel{
    return CountryModel(
        name = NameModel(name?.common),
        flags = FlagsModel(flags?.png))
}