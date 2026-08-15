package com.appdev16.thespaceworld.domain.modal.launches

data class Image(
    val credit: String?,
    val id: Int,
    val imageUrl: String,
    val license: License,
    val name: String,
    val singleUse: Boolean,
    val thumbnailUrl: String,
    val variants: List<String?>
)

data class License(
    val id: Int,
    val link: String?,
    val name: String,
    val priority: Int
)

data class Type(
    val id: Int,
    val name: String
)

data class LaunchServiceProvider(
    val abbrev: String,
    val id: Int,
    val name: String,
    val responseMode: String,
    val type: Type,
    val url: String
)
