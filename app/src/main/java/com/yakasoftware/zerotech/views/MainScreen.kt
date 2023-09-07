package com.yakasoftware.zerotech.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yakasoftware.zerotech.Lines.SimpleLine
import com.yakasoftware.zerotech.R

@Composable
fun MainScreen(navController: NavHostController) {
    var isMenuVisible = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    isMenuVisible.value = !isMenuVisible.value
                }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
                    if (isMenuVisible.value) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Menü Kapama",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }else {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menü Açma",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                }
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.zerotechlogotransparentr),
                    contentDescription = "Logo",
                    modifier = Modifier.size(90.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = { /*TODO*/ }) {
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
                        contentDescription = "Menü Açma",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                //Sepet işlemleri
                            }
                    )

                Spacer(modifier = Modifier.padding(end = 4.dp))

            }
            if (isMenuVisible.value) {
                // Menü içeriğini burada göster
                Text("Menü içeriği", color = Color.Red)
            }
            Row(Modifier.fillMaxSize()) {
                SimpleLine()
            }
        }
    }
}