package com.appdev16.thespaceworld

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TheSpaceWorld",
    ) {
        App()
    }
}