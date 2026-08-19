package com.appdev16.thespaceworld.data.dto.agencies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgencyResponseDto(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<AgencyDto>
)

@Serializable
data class AgencyDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("featured") val featured: Boolean = false,
    @SerialName("type") val type: AgencyTypeDto? = null,
    @SerialName("country_code") val countryCode: String? = null,
    @SerialName("abbrev") val abbrev: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("administrator") val administrator: String? = null,
    @SerialName("founding_year") val foundingYear: String? = null,
    @SerialName("image") val image: AgencyImageDto? = null,
    @SerialName("logo") val logo: AgencyImageDto? = null,
    @SerialName("total_launch_count") val totalLaunchCount: Int = 0,
    @SerialName("successful_launches") val successfulLaunches: Int = 0,
    @SerialName("failed_launches") val failedLaunches: Int = 0,
    @SerialName("pending_launches") val pendingLaunches: Int = 0,
    @SerialName("info_url") val infoUrl: String? = null,
    @SerialName("wiki_url") val wikiUrl: String? = null
)

@Serializable
data class AgencyTypeDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)

@Serializable
data class AgencyImageDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("image_url") val imageUrl: String = "",
    @SerialName("thumbnail_url") val thumbnailUrl: String = ""
)
