package com.appdev16.thespaceworld.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launches")
data class LaunchEntity(
    @PrimaryKey val id: String,
    val name: String,
    val net: String,
    val lastUpdated: String?,
    val failReason: String?,
    val agencyLaunchAttemptCount: Int,
    val agencyLaunchAttemptCountYear: Int,
    val locationLaunchAttemptCount: Int,
    val locationLaunchAttemptCountYear: Int,
    val orbitalLaunchAttemptCount: Int,
    val orbitalLaunchAttemptCountYear: Int,
    val padLaunchAttemptCount: Int,
    val padLaunchAttemptCountYear: Int,
    val webcastLive: Boolean,
    val windowEnd: String,
    val windowStart: String,
    val slug: String,
    val url: String,
    val responseMode: String,
    
    @Embedded(prefix = "image_") val image: ImageEntity?,
    @Embedded(prefix = "provider_") val launchServiceProvider: ProviderEntity?,
    @Embedded(prefix = "mission_") val mission: MissionEntity?,
    @Embedded(prefix = "rocket_") val rocket: RocketEntity?,
    @Embedded(prefix = "status_") val status: StatusEntity?
)

data class ImageEntity(
    val imageUrl: String,
    val name: String,
    val thumbnailUrl: String
)

data class ProviderEntity(
    val name: String
)

data class MissionEntity(
    val description: String,
    val missionId: Int,
    val name: String,
    val type: String
)

data class RocketEntity(
    val rocketId: Int,
    @Embedded(prefix = "config_") val configuration: ConfigurationEntity?
)

data class ConfigurationEntity(
    val fullName: String?,
    val configId: Int,
    val name: String?,
    val url: String?,
    val variant: String?
)

data class StatusEntity(
    val abbrev: String,
    val description: String,
    val statusId: Int,
    val name: String
)
