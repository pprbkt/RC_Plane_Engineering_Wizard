package com.example.rc_plane_wizard.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = NeoYellow,
    secondary = NeoCyan,
    tertiary = NeoPink,
    background = NeoBlack,
    surface = NeoBlack,
    onPrimary = NeoBlack,
    onSecondary = NeoBlack,
    onTertiary = NeoBlack,
    onBackground = NeoWhite,
    onSurface = NeoWhite,
    outline = NeoGrayOutline
)

private val LightColorScheme = lightColorScheme(
    primary = NeoYellow,
    secondary = NeoCyan,
    tertiary = NeoPink,
    background = NeoWhite,
    surface = NeoWhite,
    onPrimary = NeoBlack,
    onSecondary = NeoBlack,
    onTertiary = NeoBlack,
    onBackground = NeoBlack,
    onSurface = NeoBlack,
    outline = NeoBlack
)

@Composable
fun RCPlaneEngineeringWizardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disable dynamic color to enforce neo-brutalism
    content: @Composable () -> Unit
) {
    // FORCE LIGHT THEME (NEO-BRUTALISM) ALWAYS
    // We ignore the system dark theme setting to maintain the strict design style
    val colorScheme = LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            // Always set light status bars since we are forcing light theme
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
