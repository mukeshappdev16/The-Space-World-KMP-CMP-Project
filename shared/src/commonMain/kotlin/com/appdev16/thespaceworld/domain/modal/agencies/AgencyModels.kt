package com.appdev16.thespaceworld.domain.modal.agencies

data class Agency(
    val id: Int,
    val name: String,
    val featured: Boolean,
    val countryCode: String?,
    val abbrev: String?,
    val description: String?,
    val administrator: String?,
    val foundingYear: String?,
    val type: AgencyType?,
    val image: AgencyImage?,
    val logo: AgencyImage?,
    val totalLaunchCount: Int,
    val successfulLaunches: Int,
    val failedLaunches: Int,
    val pendingLaunches: Int,
    val infoUrl: String?,
    val wikiUrl: String?
)

data class AgencyType(
    val id: Int,
    val name: String
)

data class AgencyImage(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val thumbnailUrl: String
)
