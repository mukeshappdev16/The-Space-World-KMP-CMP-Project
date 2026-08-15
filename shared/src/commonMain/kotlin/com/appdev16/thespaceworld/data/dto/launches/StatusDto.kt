package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusDto(
    @SerialName("abbrev") val abbrev: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = ""
)