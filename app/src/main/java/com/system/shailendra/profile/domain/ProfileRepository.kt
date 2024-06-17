package com.system.shailendra.profile.domain

import com.system.shailendra.core.preferences.AppThemeEnum as AppTheme
import com.system.shailendra.core.network.ApiServices
import com.system.shailendra.core.preferences.AppPreferences
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val preferences: AppPreferences,
    private val apiServices: ApiServices
) {

    fun getName() = preferences.getName()
    fun getToken() = preferences.getToken()
    fun getEmail() = preferences.getEmail()
    fun getTheme() = preferences.getTheme()
    suspend fun setTheme(theme: AppTheme) = preferences.setTheme(theme)
    suspend fun clearData() = preferences.clearData()
    fun logout(token: String) = apiServices.logout(token)
}