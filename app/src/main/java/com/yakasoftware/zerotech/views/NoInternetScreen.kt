package com.yakasoftware.zerotech.views

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.yakasoftware.zerotech.MainActivity

@Composable
fun NoInternetScreen(navController: NavHostController) {

    val context = LocalContext.current

    fun checkInternetConnection(context: Context): Boolean {

        // İnternet bağlantısını kontrol etmek için gerekli kodu burada uygulayabilirsiniz
        val connectivityManager =
            ContextCompat.getSystemService(context, ConnectivityManager::class.java)
        val network = connectivityManager?.activeNetwork
        val networkCapabilities = connectivityManager?.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    fun restartApp(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .width(350.dp)
                    .height(250.dp)
                    .background(MaterialTheme.colorScheme.secondary)) {
                    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Row {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Default.SignalWifiOff,
                                contentDescription = "İnternet Yok ",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Row() {
                            Text(
                                text = "İnternet Bağlantısı Gerekli!",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 18.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Row() {
                            Text(
                                text = "Uygulama için ağ bağlantısı gerkelidir.",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 16.sp
                            )
                        }
                    }

                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 160.dp)
            ) {
                Button(
                    onClick = {
                        val isInternetAvailable = checkInternetConnection(context)
                        if (isInternetAvailable) {
                            restartApp(context)
                        } else {
                           Toast.makeText(context,"Ağ bağlantısı bulunamadı.",Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Tekrar Dene", color = MaterialTheme.colorScheme.secondary, fontSize = 20.sp)
                }
            }
        }
    }

}