package com.appdev16.thespaceworld.domain.modal.astronauts

data class Astronaut(
    val id: Int,
    val name: String,
    val status: AstronautStatus?,
    val agency: AstronautAgency?,
    val image: AstronautImage?,
    val inSpace: Boolean,
    val timeInSpace: String,
    val evaTime: String,
    val age: Int,
    val dateOfBirth: String,
    val bio: String,
    val nationality: List<Nationality>,
    val flightsCount: Int,
    val landingsCount: Int,
    val spacewalksCount: Int
)

data class AstronautStatus(
    val id: Int,
    val name: String
)

data class AstronautAgency(
    val id: Int,
    val name: String,
    val abbrev: String
)

data class AstronautImage(
    val imageUrl: String,
    val name: String,
    val thumbnailUrl: String
)

data class Nationality(
    val name: String
)
