import com.example.country.data.model.Country
import com.example.country.data.model.Flags
import com.example.country.data.model.Name
import com.example.country.domain.model.CountryModel
import com.example.country.domain.model.FlagsModel
import com.example.country.domain.model.NameModel


fun Country.toDomain(): CountryModel {
    return CountryModel(
        flags = FlagsModel(flags?.png),
        name = NameModel(name?.common)
    )

}

fun CountryModel.toData(): Country{
   return Country(
        flags = Flags(flags?.png),
        name = Name(name?.common)
    )
}
