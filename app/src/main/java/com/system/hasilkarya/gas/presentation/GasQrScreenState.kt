package com.system.hasilkarya.gas.presentation

import com.system.hasilkarya.core.entities.GasEntity
import com.system.hasilkarya.core.network.Status
import com.system.hasilkarya.core.ui.utils.FailedRequest
import com.system.hasilkarya.dashboard.presentation.ScanOptions

data class GasQrScreenState(
    val token: String = "",
    val notificationMessage: String = "",
    val isLoading: Boolean = false,
    val isPostSuccessful: Boolean = false,
    val isRequestFailed: FailedRequest = FailedRequest(),
    val connectionStatus: Status = Status.UnAvailable,
    val currentlyScanning: ScanOptions = ScanOptions.Truck,
    val truckId: String = "",
    val driverId: String = "",
    val userId: String = "",
    val stationId: String = "",
    val volume: Double = 0.0,
    val odometer: String = "",
    val remarks: String = "",
    val gasList: List<GasEntity> = emptyList(),
)
