package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.database.entities.*
import com.appdev16.thespaceworld.data.dto.launches.*
import com.appdev16.thespaceworld.domain.modal.launches.*

fun LaunchDto.toDomain(): Launch = Launch(
    agencyLaunchAttemptCount = agencyLaunchAttemptCount,
    agencyLaunchAttemptCountYear = agencyLaunchAttemptCountYear,
    failReason = failReason,
    id = id,
    image = image?.toDomain(),
    lastUpdated = lastUpdated,
    launchDesignator = launchDesignator ?: "",
    launchServiceProvider = launchServiceProvider?.toDomain(),
    locationLaunchAttemptCount = locationLaunchAttemptCount,
    locationLaunchAttemptCountYear = locationLaunchAttemptCountYear,
    mission = mission?.toDomain(),
    name = name,
    net = net,
    orbitalLaunchAttemptCount = orbitalLaunchAttemptCount?.toIntOrNull() ?: 0,
    orbitalLaunchAttemptCountYear = orbitalLaunchAttemptCountYear,
    padLaunchAttemptCount = padLaunchAttemptCount,
    padLaunchAttemptCountYear = padLaunchAttemptCountYear,
    responseMode = responseMode,
    rocket = rocket?.toDomain(),
    slug = slug,
    status = status?.toDomain(),
    url = url,
    webcastLive = webcastLive,
    windowEnd = windowEnd,
    windowStart = windowStart
)

fun LaunchDto.toEntity(): LaunchEntity = LaunchEntity(
    id = id,
    name = name,
    net = net,
    lastUpdated = lastUpdated,
    failReason = failReason,
    agencyLaunchAttemptCount = agencyLaunchAttemptCount,
    agencyLaunchAttemptCountYear = agencyLaunchAttemptCountYear,
    locationLaunchAttemptCount = locationLaunchAttemptCount,
    locationLaunchAttemptCountYear = locationLaunchAttemptCountYear,
    orbitalLaunchAttemptCount = orbitalLaunchAttemptCount?.toIntOrNull() ?: 0,
    orbitalLaunchAttemptCountYear = orbitalLaunchAttemptCountYear,
    padLaunchAttemptCount = padLaunchAttemptCount,
    padLaunchAttemptCountYear = padLaunchAttemptCountYear,
    webcastLive = webcastLive,
    windowEnd = windowEnd,
    windowStart = windowStart,
    slug = slug,
    url = url,
    responseMode = responseMode,
    image = image?.let { ImageEntity(it.imageUrl, it.name, it.thumbnailUrl) },
    launchServiceProvider = launchServiceProvider?.let { ProviderEntity(it.name) },
    mission = mission?.let { MissionEntity(it.description, it.id, it.name, it.type) },
    rocket = rocket?.let { RocketEntity(it.id, it.configuration.let { config -> 
        ConfigurationEntity(config.fullName, config.id, config.name, config.url, config.variant) 
    }) },
    status = status?.let { StatusEntity(it.abbrev, it.description, it.id, it.name) }
)

fun LaunchEntity.toDomain(): Launch = Launch(
    agencyLaunchAttemptCount = agencyLaunchAttemptCount,
    agencyLaunchAttemptCountYear = agencyLaunchAttemptCountYear,
    failReason = failReason,
    id = id,
    image = image?.let { Image(it.imageUrl, it.name, it.thumbnailUrl) },
    lastUpdated = lastUpdated,
    launchDesignator = "",
    launchServiceProvider = launchServiceProvider?.let { LaunchServiceProvider(it.name) },
    locationLaunchAttemptCount = locationLaunchAttemptCount,
    locationLaunchAttemptCountYear = locationLaunchAttemptCountYear,
    mission = mission?.let { Mission(it.description, it.missionId, it.name, it.type) },
    name = name,
    net = net,
    orbitalLaunchAttemptCount = orbitalLaunchAttemptCount,
    orbitalLaunchAttemptCountYear = orbitalLaunchAttemptCountYear,
    padLaunchAttemptCount = padLaunchAttemptCount,
    padLaunchAttemptCountYear = padLaunchAttemptCountYear,
    responseMode = responseMode,
    rocket = rocket?.let { 
        Rocket(
            configuration = Configuration(
                fullName = it.configuration?.fullName,
                id = it.configuration?.configId ?: 0,
                name = it.configuration?.name,
                url = it.configuration?.url,
                variant = it.configuration?.variant
            ),
            id = it.rocketId
        ) 
    },
    slug = slug,
    status = status?.let { Status(it.abbrev, it.description, it.statusId, it.name) },
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
