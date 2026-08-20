package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.database.entities.ImageEntity
import com.appdev16.thespaceworld.data.database.entities.SpaceStationEntity
import com.appdev16.thespaceworld.data.database.entities.StationTypeEntity
import com.appdev16.thespaceworld.data.database.entities.StatusEntity
import com.appdev16.thespaceworld.data.dto.spacestations.SpaceStationDto
import com.appdev16.thespaceworld.data.dto.spacestations.StationOwnerDto
import com.appdev16.thespaceworld.data.dto.spacestations.StationTypeDto
import com.appdev16.thespaceworld.domain.modal.launches.Image
import com.appdev16.thespaceworld.domain.modal.launches.Status
import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.modal.spacestations.StationOwner
import com.appdev16.thespaceworld.domain.modal.spacestations.StationType

fun SpaceStationDto.toDomain(): SpaceStation {
    return SpaceStation(
        id = id,
        url = url,
        name = name,
        status = status?.toDomain(),
        founded = founded,
        deorbited = deorbited,
        description = description,
        orbit = orbit,
        type = type?.toDomain(),
        image = image?.toDomain(),
        owners = owners.map { it.toDomain() }
    )
}

fun StationTypeDto.toDomain(): StationType {
    return StationType(
        id = id,
        name = name
    )
}

fun StationOwnerDto.toDomain(): StationOwner {
    return StationOwner(
        id = id,
        name = name,
        abbrev = abbrev
    )
}

fun SpaceStationDto.toEntity(): SpaceStationEntity {
    return SpaceStationEntity(
        id = id,
        url = url,
        name = name,
        founded = founded,
        deorbited = deorbited,
        description = description,
        orbit = orbit,
        status = status?.let { StatusEntity(it.abbrev, it.description, it.id, it.name) },
        type = type?.let { StationTypeEntity(it.id, it.name) },
        image = image?.let { ImageEntity(it.imageUrl, it.name, it.thumbnailUrl) },
        ownersJson = "" // TODO: Implement serialization if needed, or leave empty if not critical
    )
}

fun SpaceStationEntity.toDomain(): SpaceStation {
    return SpaceStation(
        id = id,
        url = url,
        name = name,
        status = status?.let { Status(it.abbrev, it.description, it.statusId, it.name) },
        founded = founded,
        deorbited = deorbited,
        description = description,
        orbit = orbit,
        type = type?.let { StationType(it.typeId, it.name) },
        image = image?.let { Image(it.imageUrl, it.name, it.thumbnailUrl) },
        owners = emptyList() // Or deserialize ownersJson
    )
}
