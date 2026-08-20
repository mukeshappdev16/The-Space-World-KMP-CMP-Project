package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.database.entities.*
import com.appdev16.thespaceworld.data.dto.astronauts.*
import com.appdev16.thespaceworld.domain.modal.astronauts.*

fun AstronautDto.toDomain(): Astronaut = Astronaut(
    id = id,
    name = name,
    status = status?.toDomain(),
    agency = agency?.toDomain(),
    image = image?.toDomain(),
    inSpace = inSpace,
    timeInSpace = timeInSpace,
    evaTime = evaTime,
    age = age,
    dateOfBirth = dateOfBirth,
    bio = bio,
    nationality = nationality.map { it.toDomain() },
    flightsCount = flightsCount,
    landingsCount = landingsCount,
    spacewalksCount = spacewalksCount
)

fun AstronautDto.toEntity(): AstronautEntity = AstronautEntity(
    id = id,
    name = name,
    inSpace = inSpace,
    timeInSpace = timeInSpace,
    evaTime = evaTime,
    age = age,
    dateOfBirth = dateOfBirth,
    bio = bio,
    flightsCount = flightsCount,
    landingsCount = landingsCount,
    spacewalksCount = spacewalksCount,
    status = status?.let { AstronautStatusEntity(it.id, it.name) },
    agency = agency?.let { AstronautAgencyEntity(it.id, it.name, it.abbrev) },
    image = image?.let { AstronautImageEntity(it.imageUrl, it.name, it.thumbnailUrl) }
)

fun AstronautEntity.toDomain(): Astronaut = Astronaut(
    id = id,
    name = name,
    status = status?.let { AstronautStatus(it.statusId, it.name) },
    agency = agency?.let { AstronautAgency(it.agencyId, it.name, it.abbrev) },
    image = image?.let { AstronautImage(it.imageUrl, it.name, it.thumbnailUrl) },
    inSpace = inSpace,
    timeInSpace = timeInSpace,
    evaTime = evaTime,
    age = age,
    dateOfBirth = dateOfBirth,
    bio = bio,
    nationality = emptyList(), // Not stored in entity for simplicity
    flightsCount = flightsCount,
    landingsCount = landingsCount,
    spacewalksCount = spacewalksCount
)

fun AstronautStatusDto.toDomain(): AstronautStatus = AstronautStatus(
    id = id,
    name = name
)

fun AstronautAgencyDto.toDomain(): AstronautAgency = AstronautAgency(
    id = id,
    name = name,
    abbrev = abbrev
)

fun AstronautImageDto.toDomain(): AstronautImage = AstronautImage(
    imageUrl = imageUrl,
    name = name,
    thumbnailUrl = thumbnailUrl
)

fun NationalityDto.toDomain(): Nationality = Nationality(
    name = name
)
