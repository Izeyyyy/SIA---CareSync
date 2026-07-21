package edu.cit.gaane.caresync.mobile.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val CareSyncColorScheme = lightColorScheme(
        primary = Teal,
        onPrimary = Card,

        secondary = TealDark,
        onSecondary = Card,

        background = Paper,
        onBackground = Ink,

        surface = Card,
        onSurface = Ink,

        error = Danger,
)

@Composable
fun MobileTheme(
        content: @Composable () -> Unit
) {
    MaterialTheme(
            colorScheme = CareSyncColorScheme,
            typography = AppTypography,
            content = content
    )
}