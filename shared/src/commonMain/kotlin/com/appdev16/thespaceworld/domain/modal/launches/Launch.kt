package com.appdev16.thespaceworld.domain.modal.launches

data class Launch(
    val agencyLaunchAttemptCount: Int,
    val agencyLaunchAttemptCountYear: Int,
    val failReason: String?,
    val hashtag: String?,
    val id: String,
    val image: Image,
    val infographic: String?,
    val lastUpdated: String,
    val launchDesignator: String?,
    val launchServiceProvider: LaunchServiceProvider,
    val locationLaunchAttemptCount: Int,
    val locationLaunchAttemptCountYear: Int,
    val mission: Mission,
    val name: String,
    val net: String,
    val netPrecision: String?,
    val orbitalLaunchAttemptCount: Int,
    val orbitalLaunchAttemptCountYear: Int,
    val pad: Pad,
    val padLaunchAttemptCount: Int,
    val padLaunchAttemptCountYear: Int,
    val probability: String?,
    val program: List<String?>,
    val responseMode: String,
    val rocket: Rocket,
    val slug: String,
    val status: Status,
    val url: String,
    val weatherConcerns: String?,
    val webcastLive: Boolean,
    val windowEnd: String,
    val windowStart: String
)

data class Rocket(
    val configuration: Configuration,
    val id: Int
)

data class Configuration(
    val families: List<Family>,
    val fullName: String,
    val id: Int,
    val name: String,
    val responseMode: String,
    val url: String,
    val variant: String
)

data class Family(
    val id: Int,
    val name: String,
    val responseMode: String
)

data class Status(
    val abbrev: String,
    val description: String,
    val id: Int,
    val name: String
)
