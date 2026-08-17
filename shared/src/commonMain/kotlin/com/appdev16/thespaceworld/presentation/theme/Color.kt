package com.appdev16.thespaceworld.presentation.theme

import androidx.compose.ui.graphics.Color

// Space Palette - Deep & Vibrant
val SpaceBlack = Color(0xFF06070E)
val SpaceDark = Color(0xFF0B0D1C)
val SpaceDeepBlue = Color(0xFF1B1D3D)
val SpacePurple = Color(0xFF6C63FF)
val SpaceCyan = Color(0xFF00F5FF)
val SpacePink = Color(0xFFFF00E5)
val SpaceWhite = Color(0xFFF8F9FE)

// Functional Colors
val PrimaryDark = SpaceCyan
val OnPrimaryDark = SpaceBlack
val SecondaryDark = SpacePurple
val BackgroundDark = SpaceBlack
val SurfaceDark = SpaceDark
val OnSurfaceDark = SpaceWhite

// Gradients
val SpaceGradient = listOf(SpaceBlack, SpaceDeepBlue)
val AccentGradient = listOf(SpaceCyan, SpacePurple)
val CardGradient = listOf(SpaceDark, SpaceDeepBlue.copy(alpha = 0.5f))
