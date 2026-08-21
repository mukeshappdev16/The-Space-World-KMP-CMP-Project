package com.appdev16.thespaceworld

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.appdev16.thespaceworld.di.initKoin
import com.appdev16.thespaceworld.presentation.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TheSpaceWorld",
    ) {
        initKoin()
        App()
    }
}