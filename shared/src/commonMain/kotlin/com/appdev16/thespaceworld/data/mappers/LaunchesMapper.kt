package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.dto.launches.*
import com.appdev16.thespaceworld.domain.modal.launches.*

fun LaunchDto.toDomain(): Launch = Launch(
    agencyLaunchAttemptCount = agencyLaunchAttemptCount,
    agencyLaunchAttemptCountYear = agencyLaunchAttemptCountYear,
    failReason = failReason,
    id = id,
    image = image?.toDomain() ?: Image("", "", ""),
    lastUpdated = lastUpdated,
    launchDesignator = launchDesignator ?: "",
    launchServiceProvider = launchServiceProvider?.toDomain() ?: LaunchServiceProvider(""),
    locationLaunchAttemptCount = locationLaunchAttemptCount,
    locationLaunchAttemptCountYear = locationLaunchAttemptCountYear,
    mission = mission?.toDomain() ?: Mission("", 0, "", ""),
    name = name,
    net = net,
    orbitalLaunchAttemptCount = orbitalLaunchAttemptCount?.toInt() ?: 0,
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
