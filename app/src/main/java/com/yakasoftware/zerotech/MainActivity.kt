package com.yakasoftware.zerotech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yakasoftware.zerotech.ui.theme.ZeroTechTheme
import com.yakasoftware.zerotech.views.MainScreen
import com.yakasoftware.zerotech.views.NoInternetScreen
import com.yakasoftware.zerotech.views.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroTechTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "splash_screen") {

                    composable("splash_screen") {
                        SplashScreen(navController = navController)
                    }
                    composable("main_screen"){
                        MainScreen(navController = navController)
                    }
                    composable("no_internet_screen") {
                        NoInternetScreen(navController = navController)
                    }
                }
            }
        }
    }
}
