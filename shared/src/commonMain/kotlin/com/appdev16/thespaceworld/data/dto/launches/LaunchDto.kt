package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchDto(
    @SerialName("agency_launch_attempt_count") val agencyLaunchAttemptCount: Int = 0,
    @SerialName("agency_launch_attempt_count_year") val agencyLaunchAttemptCountYear: Int = 0,
    @SerialName("failreason") val failReason: String = "",
    @SerialName("hashtag") val hashtag: String? = null,
    @SerialName("id") val id: String = "",
    @SerialName("image") val image: ImageDto = ImageDto(),
    @SerialName("infographic") val infographic: String? = null,
    @SerialName("last_updated") val lastUpdated: String = "",
    @SerialName("launch_designator") val launchDesignator: String = "",
    @SerialName("launch_service_provider") val launchServiceProvider: LaunchServiceProviderDto = LaunchServiceProviderDto(),
    @SerialName("location_launch_attempt_count") val locationLaunchAttemptCount: Int = 0,
    @SerialName("location_launch_attempt_count_year") val locationLaunchAttemptCountYear: Int = 0,
    @SerialName("mission") val mission: MissionDto = MissionDto(),
    @SerialName("name") val name: String = "",
    @SerialName("net") val net: String = "",
    @SerialName("net_precision") val netPrecision: String? = null,
    @SerialName("orbital_launch_attempt_count") val orbitalLaunchAttemptCount: Int = 0,
    @SerialName("orbital_launch_attempt_count_year") val orbitalLaunchAttemptCountYear: Int = 0,
    @SerialName("pad") val pad: PadDto = PadDto(),
    @SerialName("pad_launch_attempt_count") val padLaunchAttemptCount: Int = 0,
    @SerialName("pad_launch_attempt_count_year") val padLaunchAttemptCountYear: Int = 0,
    @SerialName("probability") val probability: String? = null,
    @SerialName("program") val program: List<String?> = listOf(),
    @SerialName("response_mode") val responseMode: String = "",
    @SerialName("rocket") val rocket: RocketDto = RocketDto(),
    @SerialName("slug") val slug: String = "",
    @SerialName("status") val status: StatusDto = StatusDto(),
    @SerialName("url") val url: String = "",
    @SerialName("weather_concerns") val weatherConcerns: String? = null,
    @SerialName("webcast_live") val webcastLive: Boolean = false,
    @SerialName("window_end") val windowEnd: String = "",
    @SerialName("window_start") val windowStart: String = ""
)