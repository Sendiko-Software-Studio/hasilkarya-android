package com.system.shailendra.core

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.system.shailendra.core.navigation.Destination
import com.system.shailendra.core.preferences.AppThemeEnum
import com.system.shailendra.core.preferences.ThemeViewModel
import com.system.shailendra.core.ui.theme.HasilKaryaTheme
import com.system.shailendra.dashboard.presentation.DashboardScreen
import com.system.shailendra.dashboard.presentation.DashboardScreenViewModel
import com.system.shailendra.heavy_vehicle_fuel.presentation.HeavyVehicleFuelQrScreen
import com.system.shailendra.heavy_vehicle_fuel.presentation.HeavyVehicleFuelQrScreenViewModel
import com.system.shailendra.login.presentation.LoginScreen
import com.system.shailendra.login.presentation.LoginScreenViewModel
import com.system.shailendra.material.presentation.MaterialQrScreen
import com.system.shailendra.material.presentation.MaterialQrScreenViewModel
import com.system.shailendra.profile.presentation.ProfileScreen
import com.system.shailendra.profile.presentation.ProfileScreenViewModel
import com.system.shailendra.splash.presentation.SplashScreen
import com.system.shailendra.splash.presentation.SplashScreenViewModel
import com.system.shailendra.truck_fuel.presentation.TruckFuelQrScreen
import com.system.shailendra.truck_fuel.presentation.TruckFuelQrScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel = hiltViewModel<ThemeViewModel>()
            HasilKaryaTheme(
                darkTheme = when (themeViewModel.state.collectAsState().value.theme){
                    AppThemeEnum.Default -> isSystemInDarkTheme()
                    AppThemeEnum.Dark -> true
                    AppThemeEnum.Light -> false
                }
            ) {
                val navController = rememberNavController()
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
                                            route = route.name
                                        ) {
                                            popUpTo(
                                                route.name,
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
                                            route = route.name
                                        ) {
                                            popUpTo(
                                                route.name,
                                            ) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destination.DashboardScreen.name,
                            content = {
                                val viewModel = hiltViewModel<DashboardScreenViewModel>()
                                val connectionStatus = viewModel.connectionStatus.collectAsState().value.connectionStatus
                                DashboardScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onEvent = viewModel::onEvent,
                                    connectionStatus = connectionStatus,
                                    onNavigate = { route ->
                                        navController.navigate(route = route.name)
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destination.MaterialQrScreen.name,
                            content = {
                                val viewModel = hiltViewModel<MaterialQrScreenViewModel>()
                                MaterialQrScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onEvent = viewModel::onEvent,
                                    connectionStatus = viewModel.connectionStatus.collectAsState().value.connectionStatus,
                                    onNavigateBack = { route ->
                                        navController.navigate(
                                            route = route.name
                                        ) {
                                            popUpTo(
                                                route.name,
                                            ) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destination.ProfileScreen.name,
                            content = {
                                val viewModel = hiltViewModel<ProfileScreenViewModel>()
                                ProfileScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onEvent = viewModel::onEvent,
                                    onNavigateBack = {
                                        navController.navigate(it.name){
                                            popUpTo(it.name) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destination.GasQrScreen.name,
                            content = {
                                val viewModel = hiltViewModel<TruckFuelQrScreenViewModel>()
                                TruckFuelQrScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onEvent = viewModel::onEvent,
                                    connectionStatus = viewModel._connectionStatus.collectAsState().value.connectionStatus,
                                    onNavigateBack = {
                                        navController.navigate(it.name){
                                            popUpTo(it.name) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destination.GasHVQrScreen.name,
                            content = {
                                val viewModel = hiltViewModel<HeavyVehicleFuelQrScreenViewModel>()
                                HeavyVehicleFuelQrScreen(
                                    state = viewModel.state.collectAsState().value,
                                    onEvent = viewModel::onEvent,
                                    onNavigateBack = {
                                        navController.navigate(it.name){
                                            popUpTo(it.name) { inclusive = true }
                                        }
                                    },
                                    connectionStatus = viewModel.connectionStatus.collectAsState().value.connectionStatus
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}