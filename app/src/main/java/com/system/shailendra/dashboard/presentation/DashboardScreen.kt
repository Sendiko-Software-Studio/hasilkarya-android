package com.system.shailendra.dashboard.presentation

import android.Manifest
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.system.shailendra.R
import com.system.shailendra.core.navigation.Destination
import com.system.shailendra.core.network.Status
import com.system.shailendra.core.ui.theme.poppinsFont
import com.system.shailendra.dashboard.presentation.component.MenuCard
import com.system.shailendra.dashboard.presentation.component.MenuCardExpendable
import com.system.shailendra.dashboard.presentation.component.UnsentItemCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun DashboardScreen(
    state: DashboardScreenState,
    connectionStatus: Status,
    onEvent: (DashboardScreenEvent) -> Unit,
    onNavigate: (Destination) -> Unit,
) {

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    LaunchedEffect(
        key1 = cameraPermissionState.hasPermission,
        block = {
            if (!cameraPermissionState.hasPermission)
                cameraPermissionState.launchPermissionRequest()
        }
    )
    LaunchedEffect(
        key1 = state.totalData,
        key2 = connectionStatus,
        block = {
            if (state.totalData != 0 && connectionStatus == Status.Available)
                onEvent(DashboardScreenEvent.CheckDataAndPost)
        }
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Selamat datang, ${state.name}",
                        fontFamily = poppinsFont
                    )
                },
                actions = {
                    AnimatedVisibility(
                        visible = state.isLoading,
                        enter = expandHorizontally() + expandVertically(),
                        exit = shrinkHorizontally() + shrinkVertically()
                    ) {
                        Icon(imageVector = Icons.Default.Sync, contentDescription = "sinkronisasi")
                    }
                    IconButton(onClick = { onNavigate(Destination.ProfileScreen) }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding() + 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                item {
                    AnimatedVisibility(
                        visible = state.role == "checker",
                        enter = expandHorizontally(),
                        exit = shrinkHorizontally()
                    ) {
                        Row {
                            MenuCard(
                                text = "Scan Material Movement",
                                icon = painterResource(id = R.drawable.scan_material_movement),
                                onClickAction = {
                                    onNavigate(Destination.MaterialQrScreen)
                                },
                            )
                        }
                    }
                    AnimatedVisibility(
                        visible = state.role == "gas-operator",
                        enter = expandHorizontally(),
                        exit = shrinkHorizontally()
                    ) {
                        MenuCardExpendable(
                            text = "Scan Transaksi BBM",
                            icon = painterResource(id = R.drawable.scan_gas),
                            onClickAction1 = {
                                onNavigate(Destination.GasQrScreen)
                            },
                            onClickAction2 = {
                                onNavigate(Destination.GasHVQrScreen)
                            }
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    AnimatedVisibility(
                        visible = state.totalData != 0,
                        enter = expandHorizontally(),
                        exit = shrinkHorizontally()
                    ) {
                        UnsentItemCard(
                            modifier = Modifier
                                .fillMaxWidth(),
                            itemCount = state.totalData
                        )
                    }
                }
            }
        )
    }
}