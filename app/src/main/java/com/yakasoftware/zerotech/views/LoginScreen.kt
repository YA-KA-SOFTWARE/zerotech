package com.yakasoftware.zerotech.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yakasoftware.zerotech.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val isLoading = remember {
        mutableStateOf(true)
    }
    val userEmail = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    LaunchedEffect(isLoading.value) {
        delay(1000) // 1 saniye bekleme
        isLoading.value = false // Bekleme sona erdiğinde loading durumunu değiştir
    }
    if (isLoading.value) {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary){
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center), color = MaterialTheme.colorScheme.secondary
                )
            }
    }
    else {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {

            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.zerotechlogotransparentr),
                        contentDescription = "Logo",
                        modifier = Modifier.size(90.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))

                }
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier
                        .fillMaxSize(0.8f)
                        .background(MaterialTheme.colorScheme.primary)
                        .border(
                            width = 0.5.dp, // Kenarlık kalınlığı
                            color = MaterialTheme.colorScheme.secondary, // Kenarlık rengi
                            shape = RectangleShape // Kenarlık şekli (isteğe bağlı)
                        ), contentAlignment = Alignment.Center) {

                        Column(modifier = Modifier.fillMaxSize()) {
                            Spacer(modifier = Modifier.padding(top = 35.dp))
                            Row(modifier = Modifier.fillMaxWidth(),
                               horizontalArrangement = Arrangement.Center) {
                                Image(
                                    bitmap = ImageBitmap.imageResource(id = R.drawable.techprofile),
                                    contentDescription = "Profil Fotağrafı",
                                    modifier = Modifier.size(150.dp)
                                )
                            }
                            Row {
                                OutlinedTextField(value = userEmail.value, onValueChange = {
                                    userEmail.value = it
                                } )
                            }
                        }

                    }
                }

            }
        }

    }
}