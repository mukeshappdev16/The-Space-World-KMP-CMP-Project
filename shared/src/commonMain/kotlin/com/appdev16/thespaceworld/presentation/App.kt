package com.appdev16.thespaceworld.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appdev16.thespaceworld.presentation.navigation.Screen
import com.appdev16.thespaceworld.presentation.screens.events.EventsListScreen
import com.appdev16.thespaceworld.presentation.screens.events.EventsViewModel
import com.appdev16.thespaceworld.presentation.screens.events.detail.EventDetailScreen
import com.appdev16.thespaceworld.presentation.screens.events.detail.EventDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.home.HomeScreen
import com.appdev16.thespaceworld.presentation.screens.launches.LaunchesListScreen
import com.appdev16.thespaceworld.presentation.screens.launches.LaunchesViewModel
import com.appdev16.thespaceworld.presentation.screens.launches.detail.LaunchDetailScreen
import com.appdev16.thespaceworld.presentation.screens.launches.detail.LaunchDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.splash.SplashScreen
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

import com.appdev16.thespaceworld.presentation.theme.TheSpaceWorldTheme

import androidx.navigation.toRoute

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
                    onNavigateToEvents = {
                        navController.navigate(Screen.Events)
                    },
                    onNavigateToNews = { /* Coming Soon */ }
                )
            }

            composable<Screen.Launches> {
                val viewModel = koinViewModel<LaunchesViewModel>()
                LaunchesListScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onNavigateToDetail = { id ->
                        navController.navigate(Screen.LaunchDetail(id))
                    }
                )
            }

            composable<Screen.LaunchDetail> { backStackEntry ->
                val route = backStackEntry.toRoute<Screen.LaunchDetail>()
                val viewModel = koinViewModel<LaunchDetailViewModel>(
                    parameters = { parametersOf(route.id) }
                )
                LaunchDetailScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable<Screen.Events> {
                val viewModel = koinViewModel<EventsViewModel>()
                EventsListScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onNavigateToDetail = { id ->
                        navController.navigate(Screen.EventDetail(id))
                    }
                )
            }

            composable<Screen.EventDetail> { backStackEntry ->
                val route = backStackEntry.toRoute<Screen.EventDetail>()
                val viewModel = koinViewModel<EventDetailViewModel>(
                    parameters = { parametersOf(route.id) }
                )
                EventDetailScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
