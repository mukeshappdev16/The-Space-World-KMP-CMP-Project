package com.appdev16.thespaceworld.data.mappers

import com.appdev16.thespaceworld.data.database.entities.EventDatePrecisionEntity
import com.appdev16.thespaceworld.data.database.entities.EventEntity
import com.appdev16.thespaceworld.data.database.entities.EventImageEntity
import com.appdev16.thespaceworld.data.database.entities.EventTypeEntity
import com.appdev16.thespaceworld.data.dto.events.EventDto
import com.appdev16.thespaceworld.domain.modal.events.Event
import com.appdev16.thespaceworld.domain.modal.events.EventDatePrecision
import com.appdev16.thespaceworld.domain.modal.events.EventImage
import com.appdev16.thespaceworld.domain.modal.events.EventType

fun EventDto.toDomain(): Event = Event(
    date = date,
    datePrecision = datePrecision?.let { EventDatePrecision(it.abbrev, it.description, it.id, it.name) },
    description = description,
    id = id,
    image = image.let { EventImage(it.imageUrl, it.name, it.thumbnailUrl) },
    lastUpdated = lastUpdated,
    location = location,
    name = name,
    responseMode = responseMode,
    slug = slug,
    type = type.let { EventType(it.id, it.name) },
    url = url,
    webcastLive = webcastLive
)

fun EventDto.toEntity(): EventEntity = EventEntity(
    id = id,
    name = name,
    description = description,
    location = location,
    date = date,
    lastUpdated = lastUpdated,
    webcastLive = webcastLive,
    url = url,
    slug = slug,
    responseMode = responseMode,
    datePrecision = datePrecision?.let { EventDatePrecisionEntity(it.abbrev, it.description, it.id, it.name) },
    image = image.let { EventImageEntity(it.imageUrl, it.name, it.thumbnailUrl) },
    type = type.let { EventTypeEntity(it.id, it.name) }
)

fun EventEntity.toDomain(): Event = Event(
    date = date,
    datePrecision = datePrecision?.let { EventDatePrecision(it.abbrev, it.description, it.precisionId, it.name) },
    description = description,
    id = id,
    image = image?.let { EventImage(it.imageUrl, it.name, it.thumbnailUrl) },
    lastUpdated = lastUpdated,
    location = location,
    name = name,
    responseMode = responseMode,
    slug = slug,
    type = type?.let { EventType(it.typeId, it.name) },
    url = url,
    webcastLive = webcastLive
)
