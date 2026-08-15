package com.appdev16.thespaceworld.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appdev16.thespaceworld.presentation.navigation.Screen
import com.appdev16.thespaceworld.presentation.screens.home.HomeScreen
import com.appdev16.thespaceworld.presentation.screens.launches.LaunchesListScreen
import com.appdev16.thespaceworld.presentation.screens.launches.LaunchesViewModel
import com.appdev16.thespaceworld.presentation.screens.splash.SplashScreen
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

import com.appdev16.thespaceworld.presentation.theme.TheSpaceWorldTheme

@Composable
@Preview
fun App() {
    TheSpaceWorldTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Splash
        ) {
            composable<Screen.Splash> {
                SplashScreen(
                    onNavigateToHome = {
                        navController.navigate(Screen.Home) {
                            popUpTo(Screen.Splash) { inclusive = true }
                        }
                    }
                )
            }

            composable<Screen.Home> {
                HomeScreen(
                    onNavigateToLaunches = {
                        navController.navigate(Screen.Launches)
                    },
                    onNavigateToEvents = { /* Coming Soon */ },
                    onNavigateToNews = { /* Coming Soon */ }
                )
            }

            composable<Screen.Launches> {
                val viewModel = koinViewModel<LaunchesViewModel>()
                LaunchesListScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
