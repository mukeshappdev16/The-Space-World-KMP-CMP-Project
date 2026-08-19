package com.appdev16.thespaceworld.domain.modal.events

data class Event(
    val date: String,
    val datePrecision: EventDatePrecision?,
    val description: String,
    val id: Int,
    val image: EventImage?,
    val lastUpdated: String,
    val location: String,
    val name: String,
    val responseMode: String,
    val slug: String,
    val type: EventType?,
    val url: String,
    val webcastLive: Boolean
)

data class EventImage(
    val imageUrl: String,
    val name: String,
    val thumbnailUrl: String
)

data class EventType(
    val id: Int,
    val name: String
)

data class EventDatePrecision(
    val abbrev: String,
    val description: String,
    val id: Int,
    val name: String
)
