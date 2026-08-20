package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.database.entities.ImageEntity
import com.appdev16.thespaceworld.data.database.entities.LocationEntity
import com.appdev16.thespaceworld.data.dto.locations.LocationDto
import com.appdev16.thespaceworld.domain.modal.launches.Image
import com.appdev16.thespaceworld.domain.modal.locations.Location

fun LocationDto.toEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        url = url,
        name = name,
        active = active,
        description = description,
        longitude = longitude,
        latitude = latitude,
        timezoneName = timezoneName,
        totalLaunchCount = totalLaunchCount,
        totalLandingCount = totalLandingCount,
        mapImage = mapImage,
        countryName = country?.name,
        celestialBodyName = celestialBody?.name,
        image = image?.let { ImageEntity(it.imageUrl ?: "", "", it.thumbnailUrl ?: "") }
    )
}

fun LocationEntity.toDomain(): Location {
    return Location(
        id = id,
        url = url,
        name = name,
        active = active,
        description = description,
        longitude = longitude,
        latitude = latitude,
        timezoneName = timezoneName,
        totalLaunchCount = totalLaunchCount,
        totalLandingCount = totalLandingCount,
        mapImage = mapImage,
        image = image?.let { Image(it.imageUrl, it.name, it.thumbnailUrl) },
        countryName = countryName,
        celestialBodyName = celestialBodyName
    )
}

fun LocationDto.toDomain(): Location {
    return Location(
        id = id,
        url = url,
        name = name,
        active = active,
        description = description,
        longitude = longitude,
        latitude = latitude,
        timezoneName = timezoneName,
        totalLaunchCount = totalLaunchCount,
        totalLandingCount = totalLandingCount,
        mapImage = mapImage,
        image = image?.let { Image(it.imageUrl ?: "", "", it.thumbnailUrl ?: "") },
        countryName = country?.name,
        celestialBodyName = celestialBody?.name
    )
}
