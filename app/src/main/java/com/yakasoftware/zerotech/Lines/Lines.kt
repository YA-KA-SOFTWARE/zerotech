package com.yakasoftware.zerotech.Lines

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SimpleLine() {
    Divider(color = MaterialTheme.colorScheme.tertiary, thickness = 0.3.dp)
}

@Composable
fun SheetBarLine() {
    Divider(color = MaterialTheme.colorScheme.onSecondary, thickness = 3.dp)
}

@Composable
fun SimpleLineWhite() {
    Divider(color = Color.White, thickness = 0.3.dp)
}