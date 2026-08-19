package com.appdev16.thespaceworld.data.dto.events


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguageDto(
    @SerialName("code") val code: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = ""
)