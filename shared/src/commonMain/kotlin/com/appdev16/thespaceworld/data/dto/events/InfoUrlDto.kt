package com.appdev16.thespaceworld.data.dto.events


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoUrlDto(
    @SerialName("description") val description: String = "",
    @SerialName("feature_image") val featureImage: String? = null,
    @SerialName("language") val language: LanguageDto = LanguageDto(),
    @SerialName("priority") val priority: Int = 0,
    @SerialName("source") val source: String = "",
    @SerialName("title") val title: String = "",
    @SerialName("url") val url: String = ""
)