package com.appdev16.thespaceworld.domain.modal.launches

data class Launch(
    val agencyLaunchAttemptCount: Int,
    val agencyLaunchAttemptCountYear: Int,
    val failReason: String?,
    val id: String,
    val image: Image?,
    val lastUpdated: String?,
    val launchDesignator: String?,
    val launchServiceProvider: LaunchServiceProvider?,
    val locationLaunchAttemptCount: Int,
    val locationLaunchAttemptCountYear: Int,
    val mission: Mission?,
    val name: String,
    val net: String,
    val orbitalLaunchAttemptCount: Int,
    val orbitalLaunchAttemptCountYear: Int,
    val padLaunchAttemptCount: Int,
    val padLaunchAttemptCountYear: Int,
    val responseMode: String,
    val rocket: Rocket?,
    val slug: String,
    val status: Status?,
    val url: String,
    val webcastLive: Boolean,
    val windowEnd: String,
    val windowStart: String,
    val netPrecision: Type? = null
)

data class Configuration(
    val fullName: String?,
    val id: Int,
    val name: String?,
    val url: String?,
    val variant: String?
)

data class Image(
    val imageUrl: String,
    val name: String,
    val thumbnailUrl: String
)

data class LaunchServiceProvider(
    val name: String
)

data class Mission(
    val description: String,
    val id: Int,
    val name: String,
    val type: String
)

data class Rocket(
    val configuration: Configuration,
    val id: Int
)

data class Status(
    val abbrev: String,
    val description: String,
    val id: Int,
    val name: String
)

data class Type(
    val id: Int,
    val name: String
)
