package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LicenseDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("link") val link: String? = null,
    @SerialName("name") val name: String = "",
    @SerialName("priority") val priority: Int = 0
)