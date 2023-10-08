package com.yakasoftware.zerotech.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = siyah,
    secondary = turuncu,
    tertiary = beyaz,
    onPrimary = koyusiyah,
    onSecondary = koyuturuncu,
    onTertiary = koyusiyah,
    onSurface = acikkirmizi,
    onBackground = acikkrem
)

private val LightColorScheme = lightColorScheme(
    primary = beyaz,
    secondary = turuncu,
    tertiary = siyah,
    onPrimary = aciksiyah ,
    onSecondary = acikturuncu,
    onTertiary = krem,
    onSurface = koyukirmizi,
    onBackground = koyukrem



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ZeroTechTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}