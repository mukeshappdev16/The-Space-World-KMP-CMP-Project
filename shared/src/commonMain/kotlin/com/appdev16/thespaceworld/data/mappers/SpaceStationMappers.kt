package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.dto.spacestations.SpaceStationDto
import com.appdev16.thespaceworld.data.dto.spacestations.StationOwnerDto
import com.appdev16.thespaceworld.data.dto.spacestations.StationTypeDto
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
