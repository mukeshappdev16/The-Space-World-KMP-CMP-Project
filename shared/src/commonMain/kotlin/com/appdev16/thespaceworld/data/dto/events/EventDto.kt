package com.appdev16.thespaceworld.data.dto.events


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDto(
    @SerialName("date") val date: String = "",
    @SerialName("date_precision") val datePrecision: DatePrecisionDto? = DatePrecisionDto(),
    @SerialName("description") val description: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("image") val image: ImageDto = ImageDto(),
    @SerialName("info_urls") val infoUrls: List<InfoUrlDto> = listOf(),
    @SerialName("last_updated") val lastUpdated: String = "",
    @SerialName("location") val location: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("response_mode") val responseMode: String = "",
    @SerialName("slug") val slug: String = "",
    @SerialName("type") val type: TypeDto = TypeDto(),
    @SerialName("url") val url: String = "",
    @SerialName("webcast_live") val webcastLive: Boolean = false
)