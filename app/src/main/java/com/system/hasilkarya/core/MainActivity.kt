package com.system.hasilkarya.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.system.hasilkarya.core.navigation.Destination
import com.system.hasilkarya.core.ui.theme.HasilKaryaTheme
import com.system.hasilkarya.dashboard.presentation.DashboardScreen
import com.system.hasilkarya.dashboard.presentation.DashboardScreenViewModel
import com.system.hasilkarya.login.presentation.LoginScreen
import com.system.hasilkarya.login.presentation.LoginScreenViewModel
import com.system.hasilkarya.splash.presentation.SplashScreen
import com.system.hasilkarya.splash.presentation.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HasilKaryaTheme {
                val navController = rememberNavController()
                enableEdgeToEdge()
                NavHost(
                    navController = navController,
                    startDestination = Destination.SplashScreen.name,
                    builder = {
                        composable(
                            route = Destination.SplashScreen.name,
                            content = {
                                val viewModel = hiltViewModel<SplashScreenViewModel>()
                                SplashScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onNavigate = { route ->
                                        navController.navigate(
                                            route = route
                                        ) {
                                            popUpTo(
                                                route,
                                            ) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destination.LoginScreen.name,
                            content = {
                                val viewModel = hiltViewModel<LoginScreenViewModel>()
                                LoginScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onEvent = viewModel::onEvent,
                                    onNavigate = { route ->
                                        navController.navigate(
                                            route = route
                                        ) {
                                            popUpTo(
                                                route,
                                            ) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destination.DashboardScreen.name,
                            content = {
                                val viewModel: DashboardScreenViewModel = viewModel()
                                DashboardScreen(
                                    state = viewModel.state.collectAsState().value,
                                    viewModel::onEvent
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}