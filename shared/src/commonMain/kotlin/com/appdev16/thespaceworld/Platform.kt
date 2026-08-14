package com.appdev16.thespaceworld

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform