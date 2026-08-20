package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.database.entities.ImageEntity
import com.appdev16.thespaceworld.data.database.entities.SpacecraftAgencyEntity
import com.appdev16.thespaceworld.data.database.entities.SpacecraftEntity
import com.appdev16.thespaceworld.data.database.entities.SpacecraftTypeEntity
import com.appdev16.thespaceworld.data.dto.spacecrafts.SpacecraftDto
import com.appdev16.thespaceworld.data.dto.spacecrafts.SpacecraftAgencyDto
import com.appdev16.thespaceworld.data.dto.spacecrafts.SpacecraftTypeDto
import com.appdev16.thespaceworld.domain.modal.launches.Image
import com.appdev16.thespaceworld.domain.modal.spacecrafts.SpacecraftConfig
import com.appdev16.thespaceworld.domain.modal.spacecrafts.SpacecraftAgency
import com.appdev16.thespaceworld.domain.modal.spacecrafts.SpacecraftType

fun SpacecraftDto.toDomain(): SpacecraftConfig {
    return SpacecraftConfig(
        id = id,
        url = url,
        name = name,
        type = type?.toDomain(),
        agency = agency?.toDomain(),
        inUse = inUse,
        image = image?.toDomain()
    )
}

fun SpacecraftTypeDto.toDomain(): SpacecraftType {
    return SpacecraftType(
        id = id,
        name = name
    )
}

fun SpacecraftAgencyDto.toDomain(): SpacecraftAgency {
    return SpacecraftAgency(
        id = id,
        name = name,
        abbrev = abbrev
    )
}

fun SpacecraftDto.toEntity(): SpacecraftEntity {
    return SpacecraftEntity(
        id = id,
        url = url,
        name = name,
        inUse = inUse,
        type = type?.let { SpacecraftTypeEntity(it.id, it.name) },
        agency = agency?.let { SpacecraftAgencyEntity(it.id, it.name, it.abbrev) },
        image = image?.let { ImageEntity(it.imageUrl, it.name, it.thumbnailUrl) }
    )
}

fun SpacecraftEntity.toDomain(): SpacecraftConfig {
    return SpacecraftConfig(
        id = id,
        url = url,
        name = name,
        type = type?.let { SpacecraftType(it.typeId, it.name) },
        agency = agency?.let { SpacecraftAgency(it.agencyId, it.name, it.abbrev) },
        inUse = inUse,
        image = image?.let { Image(it.imageUrl, it.name, it.thumbnailUrl) }
    )
}
