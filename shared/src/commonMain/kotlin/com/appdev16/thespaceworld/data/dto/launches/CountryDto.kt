package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    @SerialName("alpha_2_code") val alpha2Code: String = "",
    @SerialName("alpha_3_code") val alpha3Code: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("nationality_name") val nationalityName: String = "",
    @SerialName("nationality_name_composed") val nationalityNameComposed: String = ""
)