package com.appdev16.thespaceworld.presentation.util

import com.appdev16.thespaceworld.domain.modal.launches.*

object MockData {
    val launch = Launch(
        agencyLaunchAttemptCount = 10,
        agencyLaunchAttemptCountYear = 2,
        failReason = null,
        hashtag = "#SpaceX",
        id = "1",
        image = Image(
            credit = "SpaceX",
            id = 1,
            imageUrl = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/falcon_9_block_5_image_20190222031131.jpeg",
            license = License(1, "https://creativecommons.org/licenses/by/2.0/", "CC BY 2.0", 1),
            name = "Falcon 9",
            singleUse = false,
            thumbnailUrl = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/falcon_9_block_5_image_20190222031131.jpeg",
            variants = emptyList()
        ),
        infographic = null,
        lastUpdated = "2023-10-27T00:00:00Z",
        launchDesignator = "2023-001",
        launchServiceProvider = LaunchServiceProvider(
            abbrev = "SPX",
            id = 121,
            name = "SpaceX",
            responseMode = "normal",
            type = Type(1, "Private"),
            url = "https://www.spacex.com"
        ),
        locationLaunchAttemptCount = 50,
        locationLaunchAttemptCountYear = 5,
        mission = Mission(
            agencies = emptyList(),
            description = "This is a mock mission description for a SpaceX Falcon 9 launch carrying satellites to Low Earth Orbit.",
            id = 1,
            image = null,
            infoUrls = emptyList(),
            name = "Starlink G6-25",
            orbit = Orbit("LEO", CelestialBody(id = 1, name = "Earth", responseMode = "normal"), 1, "Low Earth Orbit"),
            type = "Communication",
            vidUrls = emptyList()
        ),
        name = "Falcon 9 Block 5 | Starlink G6-25",
        net = "2023-10-31T20:00:00Z",
        netPrecision = "hour",
        orbitalLaunchAttemptCount = 100,
        orbitalLaunchAttemptCountYear = 10,
        pad = Pad(
            active = true,
            agencies = emptyList(),
            country = Country("US", "USA", 1, "United States", "American", "American"),
            description = null,
            fastestTurnaround = "PT10H",
            id = 1,
            image = null,
            infoUrl = null,
            latitude = 28.5623,
            location = Location(
                active = true,
                celestialBody = CelestialBody(id = 1, name = "Earth", responseMode = "normal"),
                country = Country("US", "USA", 1, "United States", "American", "American"),
                description = "Cape Canaveral Space Force Station",
                id = 1,
                image = Image(
                    credit = "USAF",
                    id = 1,
                    imageUrl = "",
                    license = License(1, "", "", 1),
                    name = "",
                    singleUse = false,
                    thumbnailUrl = "",
                    variants = emptyList()
                ),
                latitude = 28.5623,
                longitude = -80.5773,
                mapImage = "",
                name = "Cape Canaveral, FL, USA",
                responseMode = "normal",
                timezoneName = "America/New_York",
                totalLandingCount = 10,
                totalLaunchCount = 100,
                url = ""
            ),
            longitude = -80.5773,
            mapImage = "",
            mapUrl = "",
            name = "Space Launch Complex 40",
            orbitalLaunchAttemptCount = 100,
            totalLaunchCount = 100,
            url = "",
            wikiUrl = ""
        ),
        padLaunchAttemptCount = 100,
        padLaunchAttemptCountYear = 10,
        probability = "90",
        program = emptyList(),
        responseMode = "normal",
        rocket = Rocket(
            configuration = Configuration(
                families = emptyList(),
                fullName = "Falcon 9 Block 5",
                id = 1,
                name = "Falcon 9",
                responseMode = "normal",
                url = "",
                variant = "Block 5"
            ),
            id = 1
        ),
        slug = "falcon-9-block-5-starlink-g6-25",
        status = Status("Success", "Launch successful", 1, "Success"),
        url = "",
        weatherConcerns = null,
        webcastLive = false,
        windowEnd = "2023-10-31T21:00:00Z",
        windowStart = "2023-10-31T20:00:00Z"
    )

    val launches = listOf(
        launch,
        launch.copy(id = "2", name = "Falcon Heavy | Psyche", status = Status("Success", "", 1, "Success")),
        launch.copy(id = "3", name = "Starship | IFT-2", status = Status("Failure", "", 2, "Failure"))
    )
}
