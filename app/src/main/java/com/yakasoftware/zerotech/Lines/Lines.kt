package com.yakasoftware.zerotech.Lines

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SimpleLine() {
    Divider(color = MaterialTheme.colorScheme.tertiary, thickness = 0.5.dp)
}