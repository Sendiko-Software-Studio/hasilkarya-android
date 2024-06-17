package com.system.shailendra.dashboard.presentation

sealed class DashboardScreenEvent {
    data object CheckDataAndPost: DashboardScreenEvent()
    data object ClearNotificationState: DashboardScreenEvent()
}