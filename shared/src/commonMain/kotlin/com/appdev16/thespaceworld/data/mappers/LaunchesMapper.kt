package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.dto.launches.*
import com.appdev16.thespaceworld.domain.modal.launches.*

fun LaunchDto.toDomain() = Launch(
    agencyLaunchAttemptCount = agencyLaunchAttemptCount,
    agencyLaunchAttemptCountYear = agencyLaunchAttemptCountYear,
    failReason = failReason,
    hashtag = hashtag,
    id = id,
    image = image.toDomain(),
    infographic = infographic,
    lastUpdated = lastUpdated,
    launchDesignator = launchDesignator,
    launchServiceProvider = launchServiceProvider.toDomain(),
    locationLaunchAttemptCount = locationLaunchAttemptCount,
    locationLaunchAttemptCountYear = locationLaunchAttemptCountYear,
    mission = mission.toDomain(),
    name = name,
    net = net,
    netPrecision = netPrecision,
    orbitalLaunchAttemptCount = orbitalLaunchAttemptCount,
    orbitalLaunchAttemptCountYear = orbitalLaunchAttemptCountYear,
    pad = pad.toDomain(),
    padLaunchAttemptCount = padLaunchAttemptCount,
    padLaunchAttemptCountYear = padLaunchAttemptCountYear,
    probability = probability,
    program = program,
    responseMode = responseMode,
    rocket = rocket.toDomain(),
    slug = slug,
    status = status.toDomain(),
    url = url,
    weatherConcerns = weatherConcerns,
    webcastLive = webcastLive,
    windowEnd = windowEnd,
    windowStart = windowStart
)

fun ImageDto.toDomain() = Image(
    credit = credit,
    id = id,
    imageUrl = imageUrl,
    license = license.toDomain(),
    name = name,
    singleUse = singleUse,
    thumbnailUrl = thumbnailUrl,
    variants = variants
)

fun ImageDtoXX.toDomain() = Image(
    credit = credit,
    id = id,
    imageUrl = imageUrl,
    license = license.toDomain(),
    name = name,
    singleUse = singleUse,
    thumbnailUrl = thumbnailUrl,
    variants = variants
)

fun LicenseDto.toDomain() = License(
    id = id,
    link = link,
    name = name,
    priority = priority
)

fun LicenseDtoX.toDomain() = License(
    id = id,
    link = link,
    name = name,
    priority = priority
)

fun TypeDto.toDomain() = Type(
    id = id,
    name = name
)

fun LaunchServiceProviderDto.toDomain() = LaunchServiceProvider(
    abbrev = abbrev,
    id = id,
    name = name,
    responseMode = responseMode,
    type = type.toDomain(),
    url = url
)

fun MissionDto.toDomain() = Mission(
    agencies = agencies.map { it.toDomain() },
    description = description,
    id = id,
    image = image,
    infoUrls = infoUrls,
    name = name,
    orbit = orbit.toDomain(),
    type = type,
    vidUrls = vidUrls
)

fun OrbitDto.toDomain() = Orbit(
    abbrev = abbrev,
    celestialBody = celestialBody.toDomain(),
    id = id,
    name = name
)

fun CelestialBodyDto.toDomain() = CelestialBody(
    id = id,
    name = name,
    responseMode = responseMode
)

fun CelestialBodyDtoX.toDomain() = CelestialBody(
    atmosphere = atmosphere,
    description = description,
    diameter = diameter,
    failedLandings = failedLandings,
    failedLaunches = failedLaunches,
    gravity = gravity,
    id = id,
    image = image.toDomain(),
    lengthOfDay = lengthOfDay,
    mass = mass,
    name = name,
    responseMode = responseMode,
    successfulLandings = successfulLandings,
    successfulLaunches = successfulLaunches,
    totalAttemptedLandings = totalAttemptedLandings,
    totalAttemptedLaunches = totalAttemptedLaunches,
    type = type.toDomain(),
    wikiUrl = wikiUrl
)

fun PadDto.toDomain() = Pad(
    active = active,
    agencies = agencies.map { it.toDomain() },
    country = country.toDomain(),
    description = description,
    fastestTurnaround = fastestTurnaround,
    id = id,
    image = image,
    infoUrl = infoUrl,
    latitude = latitude,
    location = location.toDomain(),
    longitude = longitude,
    mapImage = mapImage,
    mapUrl = mapUrl,
    name = name,
    orbitalLaunchAttemptCount = orbitalLaunchAttemptCount,
    totalLaunchCount = totalLaunchCount,
    url = url,
    wikiUrl = wikiUrl
)

fun LocationDto.toDomain() = Location(
    active = active,
    celestialBody = celestialBody.toDomain(),
    country = country.toDomain(),
    description = description,
    id = id,
    image = image.toDomain(),
    latitude = latitude,
    longitude = longitude,
    mapImage = mapImage,
    name = name,
    responseMode = responseMode,
    timezoneName = timezoneName,
    totalLandingCount = totalLandingCount,
    totalLaunchCount = totalLaunchCount,
    url = url
)

fun CountryDto.toDomain() = Country(
    alpha2Code = alpha2Code,
    alpha3Code = alpha3Code,
    id = id,
    name = name,
    nationalityName = nationalityName,
    nationalityNameComposed = nationalityNameComposed
)

fun RocketDto.toDomain() = Rocket(
    configuration = configuration.toDomain(),
    id = id
)

fun ConfigurationDto.toDomain() = Configuration(
    families = families.map { it.toDomain() },
    fullName = fullName,
    id = id,
    name = name,
    responseMode = responseMode,
    url = url,
    variant = variant
)

fun FamilyDto.toDomain() = Family(
    id = id,
    name = name,
    responseMode = responseMode
)

fun StatusDto.toDomain() = Status(
    abbrev = abbrev,
    description = description,
    id = id,
    name = name
)
