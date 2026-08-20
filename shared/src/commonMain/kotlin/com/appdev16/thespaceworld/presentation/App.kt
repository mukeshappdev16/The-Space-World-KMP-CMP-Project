package com.appdev16.thespaceworld.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.appdev16.thespaceworld.presentation.navigation.Screen
import com.appdev16.thespaceworld.presentation.screens.agencies.AgenciesListScreen
import com.appdev16.thespaceworld.presentation.screens.agencies.AgenciesViewModel
import com.appdev16.thespaceworld.presentation.screens.agencies.detail.AgencyDetailScreen
import com.appdev16.thespaceworld.presentation.screens.agencies.detail.AgencyDetailViewModel
import com.appdev16.thespaceworld.presentation.screens.astronauts.AstronautsListScreen
import com.appdev16.thespaceworld.presentation.screens.astronauts.AstronautsViewModel
import com.appdev16.thespaceworld.presentation.screens.astronauts.detail.AstronautDetailScreen
import com.appdev16.thespaceworld.presentation.screens.astronauts.detail.AstronautDetailViewModel
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
import com.appdev16.thespaceworld.presentation.theme.TheSpaceWorldTheme
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

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
                    onNavigateToAgencies = {
                        navController.navigate(Screen.Agencies)
                    },
                    onNavigateToAstronauts = {
                        navController.navigate(Screen.Astronauts)
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

            composable<Screen.Agencies> {
                val viewModel = koinViewModel<AgenciesViewModel>()
                AgenciesListScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onNavigateToDetail = { id ->
                        navController.navigate(Screen.AgencyDetail(id))
                    }
                )
            }

            composable<Screen.AgencyDetail> { backStackEntry ->
                val route = backStackEntry.toRoute<Screen.AgencyDetail>()
                val viewModel = koinViewModel<AgencyDetailViewModel>(
                    parameters = { parametersOf(route.id) }
                )
                AgencyDetailScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable<Screen.Astronauts> {
                val viewModel = koinViewModel<AstronautsViewModel>()
                AstronautsListScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onNavigateToDetail = { id ->
                        navController.navigate(Screen.AstronautDetail(id))
                    }
                )
            }

            composable<Screen.AstronautDetail> { backStackEntry ->
                val route = backStackEntry.toRoute<Screen.AstronautDetail>()
                val viewModel = koinViewModel<AstronautDetailViewModel>(
                    parameters = { parametersOf(route.id) }
                )
                AstronautDetailScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
