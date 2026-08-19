package com.appdev16.thespaceworld.data.dto.events


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DatePrecisionDto(
    @SerialName("abbrev") val abbrev: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = ""
)