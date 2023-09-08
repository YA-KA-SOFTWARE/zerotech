package com.yakasoftware.zerotech.views

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.animation.core.animateTo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.yakasoftware.zerotech.R

@Composable
fun MainScreen(navController: NavHostController) {
    val isMenuVisible = remember {
        mutableStateOf(false)
    }
    val sidebarWidth by animateDpAsState(
        targetValue = if (isMenuVisible.value) (LocalConfiguration.current.screenWidthDp * 0.50f).dp else 0.dp,
        animationSpec = if (isMenuVisible.value) tween(durationMillis = 500) else spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "Yan menü animasyonu"
    )

    val auth = Firebase.auth
    val currentUser = auth.currentUser

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    if (!isMenuVisible.value) {
                        isMenuVisible.value = true
                    }
                }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menü Açma",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(32.dp)
                        )
                }
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.zerotechlogotransparentr),
                    contentDescription = "Logo",
                    modifier = Modifier.size(90.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {
                    if (currentUser == null) {
                        val popBackStackDestinationId = navController.previousBackStackEntry?.destination?.route
                        navController.navigate("login_screen") {
                            // "login_screen" sayfasına geçerken geriye gitme işlemini yapılandırın
                            if (popBackStackDestinationId == "main_screen") {
                                // Eğer önceki sayfa "main_screen" ise geriye gitme işlemini devre dışı bırak
                                popUpTo("login_screen") {
                                    saveState = false
                                    inclusive = false
                                }
                            }
                        }
                    }

                }) {
                    //Profil ve Hesap Açma
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profil ve Hesap",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
                //Sepetim
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Sepetim",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            //Sepet işlemleri
                        }
                )

                Spacer(modifier = Modifier.padding(end = 4.dp))

            }
        }
        //SideBar
        if (isMenuVisible.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(sidebarWidth)
                        .background(MaterialTheme.colorScheme.primary)
                        .zIndex(if (isMenuVisible.value) 1f else 0f)
                        .animateContentSize()
                ) {
                    //SideBar İçeriği
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.padding(start = 40.dp))
                            Image(
                                bitmap = ImageBitmap.imageResource(id = R.drawable.zerotechlogotransparentr),
                                contentDescription = "Logo",
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Button(onClick = {
                                if (isMenuVisible.value) {
                                    isMenuVisible.value = false
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Menü Kapama",
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}