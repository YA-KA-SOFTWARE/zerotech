package com.yakasoftware.zerotech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.yakasoftware.zerotech.ui.theme.ZeroTechTheme
import com.yakasoftware.zerotech.views.AccesoiresScreen
import com.yakasoftware.zerotech.views.AccesoriesDetail
import com.yakasoftware.zerotech.views.AccountDetailScreen
import com.yakasoftware.zerotech.views.AdressDetail
import com.yakasoftware.zerotech.views.AdressScreen
import com.yakasoftware.zerotech.views.AgreementScreen
import com.yakasoftware.zerotech.views.BandDetail
import com.yakasoftware.zerotech.views.BandsScreen
import com.yakasoftware.zerotech.views.BasketScreen
import com.yakasoftware.zerotech.views.FavoriteScreen
import com.yakasoftware.zerotech.views.HeadPhonesDetail
import com.yakasoftware.zerotech.views.HeadPhonesScreen
import com.yakasoftware.zerotech.views.LoginScreen
import com.yakasoftware.zerotech.views.MainScreen
import com.yakasoftware.zerotech.views.NoInternetScreen
import com.yakasoftware.zerotech.views.OrdersScreen
import com.yakasoftware.zerotech.views.ProfileScreen
import com.yakasoftware.zerotech.views.RectanglesWithLinesHeadPhones
import com.yakasoftware.zerotech.views.RectanglesWithLinesSpeaker
import com.yakasoftware.zerotech.views.RegisterScreen
import com.yakasoftware.zerotech.views.ResetPasswordScreen
import com.yakasoftware.zerotech.views.SpeakerDetailScreen
import com.yakasoftware.zerotech.views.SpeakerScreen
import com.yakasoftware.zerotech.views.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            ZeroTechTheme {
                val navController = rememberNavController()


                NavHost(navController = navController,
                    startDestination = "splash_screen",
                    enterTransition = {
                        // you can change whatever you want animation
                        EnterTransition.None
                    },
                    exitTransition = {
                        // you can change whatever you want animation
                        ExitTransition.None
                    }) {

                    composable("splash_screen") {
                        SplashScreen(navController = navController)
                    }
                    composable("main_screen"){
                        MainScreen(navController = navController)
                    }
                    composable("no_internet_screen") {
                        NoInternetScreen(navController = navController)
                    }
                    composable("register_screen") {
                        RegisterScreen(navController = navController)
                    }
                    composable("login_screen"){
                        LoginScreen(navController = navController)
                    }
                    composable("profile_screen") {
                        ProfileScreen(navController = navController)
                    }
                    composable("basket_screen") {
                        BasketScreen(navController = navController)
                    }
                    composable("adress_screen") {
                        AdressScreen(navController = navController)
                    }
                    composable("favorite_screen"){
                        FavoriteScreen(navController = navController)
                    }
                    composable("orders_screen"){
                        OrdersScreen(navController = navController)
                    }
                    composable("account_detail_screen"){
                        AccountDetailScreen(navController = navController)
                    }
                    composable("reset_password_screen"){
                        ResetPasswordScreen(navController = navController)
                    }
                    composable("speaker_screen"){
                        SpeakerScreen(navController = navController)
                    }
                    composable("headphone_screen"){
                        HeadPhonesScreen(navController = navController)
                    }
                    composable("speaker_detail_screen/{title}"){backStackEntry ->
                        backStackEntry.arguments?.getString("title")
                            ?.let { SpeakerDetailScreen(navController = navController, productTitle = it) }

                    }
                    composable("accesories_detail_screen/{title}"){backStackEntry ->
                        backStackEntry.arguments?.getString("title")
                            ?.let { AccesoriesDetail(navController = navController, productTitle = it) }

                    }
                    composable("headphones_detail_screen/{title}"){backStackEntry ->
                        backStackEntry.arguments?.getString("title")
                            ?.let { HeadPhonesDetail(navController = navController, productTitle = it) }

                    }
                    composable("band_detail_screen/{title}"){backStackEntry ->
                        backStackEntry.arguments?.getString("title")
                            ?.let { BandDetail(navController = navController, productTitle = it) }

                    }
                    composable("RectanglesWithLinesSpeaker"){
                        RectanglesWithLinesSpeaker(navController = navController)
                    }
                    composable("RectanglesWithLinesHeadPhone"){
                        RectanglesWithLinesHeadPhones(navController = navController)
                    }
                    composable("accesories_screen"){
                        AccesoiresScreen(navController = navController)
                    }
                    composable("band_screen"){
                        BandsScreen(navController = navController)
                    }
                    composable("agreement_screen") {
                        AgreementScreen(navController = navController)
                    }
                    composable("adress_detail_screen/{documentId}") { backStackEntry ->
                        backStackEntry.arguments?.getString("documentId")
                            ?.let { AdressDetail(navController = navController, documentId = it) }
                    }
                }
            }
        }
    }
}
