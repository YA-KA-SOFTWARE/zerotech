package com.yakasoftware.zerotech.views

import android.content.Context
import android.net.ConnectivityManager
import com.yakasoftware.zerotech.R
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(waitTime: Long = 1500L,navController: NavHostController) {
    val alpha = remember { Animatable(0f) }
    val isLaunchedEffectExecuted = remember { mutableStateOf(false) }

    val context = LocalContext.current

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f, // Hedef değer 1f olarak ayarlanır, yani tamamen görünür hale getirilir.
            animationSpec = tween(
                durationMillis = waitTime.toInt(),
                easing = LinearEasing
            )
        )
    }
    if (!isLaunchedEffectExecuted.value) {
        LaunchedEffect(true) {
            delay(waitTime)
            isLaunchedEffectExecuted.value = true
            if (!isNetworkAvailable(context)) {
                navController.navigate("no_internet_screen"){
                    popUpTo("splash_screen") { inclusive = true }
                }
            }else {
                navController.navigate("main_screen")
            }
        }

    }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.logonoback),
                contentDescription = "Geçici Gözüken Logo",
                alpha = alpha.value // Alfa değeri Animatable'ın değerine göre ayarlanır.
            )
        }
    }
}