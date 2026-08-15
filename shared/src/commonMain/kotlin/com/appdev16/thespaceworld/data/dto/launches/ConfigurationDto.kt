package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationDto(
    @SerialName("families") val families: List<FamilyDto> = listOf(),
    @SerialName("full_name") val fullName: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("response_mode") val responseMode: String = "",
    @SerialName("url") val url: String = "",
    @SerialName("variant") val variant: String = ""
)