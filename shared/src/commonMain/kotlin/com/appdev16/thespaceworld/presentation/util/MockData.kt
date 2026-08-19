package com.appdev16.thespaceworld.presentation.util

import com.appdev16.thespaceworld.domain.modal.events.*
import com.appdev16.thespaceworld.domain.modal.launches.*

object MockData {
    private val sampleConfiguration = Configuration(
        fullName = "Falcon 9 Block 5",
        id = 1,
        name = "Falcon 9",
        url = "https://en.wikipedia.org/wiki/Falcon_9",
        variant = "Block 5"
    )

    private val sampleImage = Image(
        imageUrl = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/falcon_9_block_5_image_20190222031131.jpeg",
        name = "Falcon 9",
        thumbnailUrl = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/launch_images/falcon_9_block_5_image_20190222031131.jpeg"
    )

    private val sampleProvider = LaunchServiceProvider(
        name = "SpaceX"
    )

    private val sampleMission = Mission(
        description = "Starlink is a satellite constellation development project by SpaceX in order to provide low-cost broadband internet system.",
        id = 1,
        name = "Starlink Group 6-25",
        type = "Communication"
    )

    private val sampleRocket = Rocket(
        configuration = sampleConfiguration,
        id = 1
    )

    private val sampleStatus = Status(
        abbrev = "Success",
        description = "Launch successful",
        id = 1,
        name = "Success"
    )

    val launch = Launch(
        agencyLaunchAttemptCount = 10,
        agencyLaunchAttemptCountYear = 5,
        failReason = null,
        id = "1",
        image = sampleImage,
        lastUpdated = "2023-10-27T00:00:00Z",
        launchDesignator = "2023-001",
        launchServiceProvider = sampleProvider,
        locationLaunchAttemptCount = 50,
        locationLaunchAttemptCountYear = 10,
        mission = sampleMission,
        name = "Falcon 9 | Starlink Group 6-25",
        net = "2023-10-31T20:00:00Z",
        orbitalLaunchAttemptCount = 100,
        orbitalLaunchAttemptCountYear = 20,
        padLaunchAttemptCount = 100,
        padLaunchAttemptCountYear = 20,
        responseMode = "normal",
        rocket = sampleRocket,
        slug = "falcon-9-starlink-group-6-25",
        status = sampleStatus,
        url = "https://spacex.com",
        webcastLive = false,
        windowEnd = "2023-10-31T21:00:00Z",
        windowStart = "2023-10-31T20:00:00Z"
    )

    val launches = listOf(
        launch,
        launch.copy(
            id = "2",
            name = "Atlas V 551 | Project Kuiper",
            launchServiceProvider = LaunchServiceProvider("United Launch Alliance"),
            status = Status("Success", "", 1, "Success")
        ),
        launch.copy(
            id = "3",
            name = "Electron | 'Leid It Go'",
            launchServiceProvider = LaunchServiceProvider("Rocket Lab"),
            status = Status("Failure", "Engine failure", 2, "Failure")
        ),
        launch.copy(
            id = "4",
            name = "Starship | IFT-2",
            launchServiceProvider = sampleProvider,
            status = Status("Success", "", 1, "Success")
        ),
        launch.copy(
            id = "5",
            name = "Soyuz 2.1b | Progress MS-25",
            launchServiceProvider = LaunchServiceProvider("Roscosmos"),
            status = Status("Success", "", 1, "Success")
        )
    )

    val event = Event(
        date = "2023-11-15T18:00:00Z",
        datePrecision = EventDatePrecision("second", "Second-level precision", 1, "second"),
        description = "A space conference discussing the future of Mars exploration.",
        id = 1,
        image = EventImage(
            imageUrl = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/event_images/spacex_starship_ift-2_image_20231115180000.jpeg",
            name = "Space Conference",
            thumbnailUrl = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/event_images/spacex_starship_ift-2_image_20231115180000.jpeg"
        ),
        lastUpdated = "2023-11-15T18:00:00Z",
        location = "Houston, Texas",
        name = "Mars Exploration Conference 2023",
        responseMode = "normal",
        slug = "mars-exploration-conference-2023",
        type = EventType(1, "Conference"),
        url = "https://example.com/events/1",
        webcastLive = true
    )

    val events = listOf(
        event,
        event.copy(
            id = 2,
            name = "Geminids Meteor Shower Peak",
            location = "Worldwide",
            type = EventType(2, "Celestial Event"),
            webcastLive = false
        ),
        event.copy(
            id = 3,
            name = "ESA Press Briefing",
            location = "Paris, France",
            type = EventType(3, "Press Event"),
            webcastLive = true
        )
    )
}
