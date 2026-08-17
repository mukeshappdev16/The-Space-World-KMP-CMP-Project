package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.database.entities.LaunchEntity
import com.appdev16.thespaceworld.data.dto.launches.*
import com.appdev16.thespaceworld.domain.modal.launches.*

fun LaunchDto.toDomain(): Launch = Launch(
    agencyLaunchAttemptCount = agencyLaunchAttemptCount,
    agencyLaunchAttemptCountYear = agencyLaunchAttemptCountYear,
    failReason = failReason,
    id = id,
    image = image?.toDomain() ?: Image("", "", ""),
    lastUpdated = lastUpdated ?: "",
    launchDesignator = launchDesignator ?: "",
    launchServiceProvider = launchServiceProvider?.toDomain() ?: LaunchServiceProvider(""),
    locationLaunchAttemptCount = locationLaunchAttemptCount,
    locationLaunchAttemptCountYear = locationLaunchAttemptCountYear,
    mission = mission?.toDomain() ?: Mission("", 0, "", ""),
    name = name,
    net = net,
    orbitalLaunchAttemptCount = orbitalLaunchAttemptCount?.toIntOrNull() ?: 0,
    orbitalLaunchAttemptCountYear = orbitalLaunchAttemptCountYear,
    padLaunchAttemptCount = padLaunchAttemptCount,
    padLaunchAttemptCountYear = padLaunchAttemptCountYear,
    responseMode = responseMode,
    rocket = rocket?.toDomain() ?: Rocket(Configuration(null, 0, null, null, null), 0),
    slug = slug,
    status = status?.toDomain() ?: Status("", "", 0, ""),
    url = url,
    webcastLive = webcastLive,
    windowEnd = windowEnd,
    windowStart = windowStart
)

fun LaunchDto.toEntity(): LaunchEntity = LaunchEntity(
    id = id,
    name = name,
    net = net,
    lastUpdated = lastUpdated ?: "",
    imageUrl = image?.imageUrl,
    providerName = launchServiceProvider?.name,
    statusName = status?.name,
    missionName = mission?.name,
    missionDescription = mission?.description,
    webcastLive = webcastLive,
    windowEnd = windowEnd,
    windowStart = windowStart
)

fun LaunchEntity.toDomain(): Launch = Launch(
    agencyLaunchAttemptCount = 0,
    agencyLaunchAttemptCountYear = 0,
    failReason = null,
    id = id,
    image = Image(imageUrl ?: "", "", ""),
    lastUpdated = lastUpdated,
    launchDesignator = "",
    launchServiceProvider = LaunchServiceProvider(providerName ?: ""),
    locationLaunchAttemptCount = 0,
    locationLaunchAttemptCountYear = 0,
    mission = Mission(missionDescription ?: "", 0, missionName ?: "", ""),
    name = name,
    net = net,
    orbitalLaunchAttemptCount = 0,
    orbitalLaunchAttemptCountYear = 0,
    padLaunchAttemptCount = 0,
    padLaunchAttemptCountYear = 0,
    responseMode = "",
    rocket = Rocket(Configuration(null, 0, null, null, null), 0),
    slug = "",
    status = Status(statusName ?: "", "", 0, statusName ?: ""),
    url = "",
    webcastLive = webcastLive,
    windowEnd = windowEnd,
    windowStart = windowStart
)

fun ConfigurationDto.toDomain(): Configuration = Configuration(
    fullName = fullName,
    id = id,
    name = name,
    url = url,
    variant = variant
)

fun ImageDto.toDomain(): Image = Image(
    imageUrl = imageUrl,
    name = name,
    thumbnailUrl = thumbnailUrl
)

fun LaunchServiceProviderDto.toDomain(): LaunchServiceProvider = LaunchServiceProvider(
    name = name
)

fun MissionDto.toDomain(): Mission = Mission(
    description = description,
    id = id,
    name = name,
    type = type
)

fun RocketDto.toDomain(): Rocket = Rocket(
    configuration = configuration.toDomain(),
    id = id
)

fun StatusDto.toDomain(): Status = Status(
    abbrev = abbrev,
    description = description,
    id = id,
    name = name
)
