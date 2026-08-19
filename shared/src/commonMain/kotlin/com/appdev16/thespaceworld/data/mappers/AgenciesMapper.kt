package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.database.entities.*
import com.appdev16.thespaceworld.data.dto.agencies.*
import com.appdev16.thespaceworld.domain.modal.agencies.*

fun AgencyDto.toDomain(): Agency = Agency(
    id = id,
    name = name,
    featured = featured,
    countryCode = countryCode,
    abbrev = abbrev,
    description = description,
    administrator = administrator,
    foundingYear = foundingYear,
    type = type?.let { AgencyType(it.id, it.name) },
    image = image?.let { AgencyImage(it.id, it.imageUrl, it.name, it.thumbnailUrl) },
    logo = logo?.let { AgencyImage(it.id, it.imageUrl, it.name, it.thumbnailUrl) },
    totalLaunchCount = totalLaunchCount,
    successfulLaunches = successfulLaunches,
    failedLaunches = failedLaunches,
    pendingLaunches = pendingLaunches,
    infoUrl = infoUrl,
    wikiUrl = wikiUrl
)

fun AgencyDto.toEntity(): AgencyEntity = AgencyEntity(
    id = id,
    name = name,
    featured = featured,
    countryCode = countryCode,
    abbrev = abbrev,
    description = description,
    administrator = administrator,
    foundingYear = foundingYear,
    totalLaunchCount = totalLaunchCount,
    successfulLaunches = successfulLaunches,
    failedLaunches = failedLaunches,
    pendingLaunches = pendingLaunches,
    infoUrl = infoUrl,
    wikiUrl = wikiUrl,
    type = type?.let { AgencyTypeEntity(it.id, it.name) },
    image = image?.let { AgencyImageEntity(it.id, it.imageUrl, it.name, it.thumbnailUrl) },
    logo = logo?.let { AgencyImageEntity(it.id, it.imageUrl, it.name, it.thumbnailUrl) }
)

fun AgencyEntity.toDomain(): Agency = Agency(
    id = id,
    name = name,
    featured = featured,
    countryCode = countryCode,
    abbrev = abbrev,
    description = description,
    administrator = administrator,
    foundingYear = foundingYear,
    type = type?.let { AgencyType(it.typeId, it.name) },
    image = image?.let { AgencyImage(it.imageId, it.imageUrl, it.name, it.thumbnailUrl) },
    logo = logo?.let { AgencyImage(it.imageId, it.imageUrl, it.name, it.thumbnailUrl) },
    totalLaunchCount = totalLaunchCount,
    successfulLaunches = successfulLaunches,
    failedLaunches = failedLaunches,
    pendingLaunches = pendingLaunches,
    infoUrl = infoUrl,
    wikiUrl = wikiUrl
)
