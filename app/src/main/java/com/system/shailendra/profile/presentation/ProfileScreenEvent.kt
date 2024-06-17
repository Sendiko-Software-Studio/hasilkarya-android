package com.system.shailendra.profile.presentation

import com.system.shailendra.core.preferences.AppThemeEnum as AppTheme


sealed class ProfileScreenEvent {
    data object OnLogout: ProfileScreenEvent()
    data class OnThemeChanged(val theme: AppTheme): ProfileScreenEvent()
}
