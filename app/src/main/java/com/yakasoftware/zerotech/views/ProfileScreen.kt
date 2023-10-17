package com.yakasoftware.zerotech.views

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine
import com.yakasoftware.zerotech.R


@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = Firebase.firestore
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    val email = currentUser?.email

    val name = remember {
        mutableStateOf("")
    }
    val surname = remember{
        mutableStateOf("")
    }
    val phoneNumber = remember {
        mutableStateOf("")
    }
    if (currentUser != null) {
        db.collection("users").document(email.toString())
            .get()
            .addOnSuccessListener {
                val data = it.data
                name.value = data?.get("name") as String
                surname.value = data["surname"] as  String
                phoneNumber.value = data["phoneNumber"] as String

            }

    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {

        val firstLetter = name.value.firstOrNull()?.uppercaseChar() ?: ' '
        val gradientBrush = Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.onSecondary
            )
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(0, 0, 10, 10))
                .fillMaxWidth()
                .height(200.dp)
                .background(gradientBrush)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Profilim", color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.padding(top = 24.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground), CircleShape)
                                .background(MaterialTheme.colorScheme.onSecondary)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = firstLetter.toString(),
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = name.value,fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(text = surname.value, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            val sideBarFontSize = 24.dp
            Spacer(modifier = Modifier.padding(top = 28.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Profil İşlemleri", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profil İşlemleri",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.fillMaxWidth(0.750f)) {
                    SimpleLine()
                }
            }
            Spacer(modifier = Modifier.padding(top = 28.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                SimpleLine()
            }
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("adress_screen")
                }) {
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    painter = painterResource(R.drawable.adresslogo),
                    contentDescription = "Adres Defteri",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.padding(start = 12.dp))
                Text(text = "Adres Defteri", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DoubleArrow,
                    contentDescription = "Ok işareti",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
                Spacer(modifier = Modifier.padding(end = 16.dp))
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                SimpleLine()
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("orders_screen")
                }) {
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    painter = painterResource(R.drawable.orderstr),
                    contentDescription = "Siparişlerim",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.padding(start = 12.dp))
                Text(text = "Siparişlerim", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DoubleArrow,
                    contentDescription = "Ok işareti",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
                Spacer(modifier = Modifier.padding(end = 16.dp))
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                SimpleLine()
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("favorite_screen")
                }) {
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    painter = painterResource(R.drawable.likestr),
                    contentDescription = "favorilerim",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.padding(start = 12.dp))
                Text(text = "Favorilerim", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DoubleArrow,
                    contentDescription = "Ok işareti",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
                Spacer(modifier = Modifier.padding(end = 16.dp))
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                SimpleLine()
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("basket_screen")
                }) {
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    imageVector = Icons.Default.ShoppingBasket,
                    contentDescription = "Sepetim",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
                Spacer(modifier = Modifier.padding(start = 12.dp))
                Text(text = "Sepetim", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DoubleArrow,
                    contentDescription = "Ok işareti",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
                Spacer(modifier = Modifier.padding(end = 16.dp))
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                SimpleLine()
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("account_detail_screen")
                }) {
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Hesap Bilgileri",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
                Spacer(modifier = Modifier.padding(start = 12.dp))
                Text(text = "Hesap Bilgileri", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DoubleArrow,
                    contentDescription = "Ok işareti",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
                Spacer(modifier = Modifier.padding(end = 16.dp))
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                SimpleLine()
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("reset_password_screen")
                }) {
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Şifreyi Değiştir",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
                Spacer(modifier = Modifier.padding(start = 12.dp))
                Text(text = "Şifreyi Değiştir", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DoubleArrow,
                    contentDescription = "Ok işareti",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(26.dp)
                )
                Spacer(modifier = Modifier.padding(end = 16.dp))
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                SimpleLine()
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Spacer(modifier = Modifier.padding(top = 32.dp))
            Row(modifier = Modifier
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
                    Row(modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(0.6f)
                        .background(Color(202, 47, 47, 255))
                        .clickable {
                            navController.navigate("main_screen"){
                                popUpTo("main_screen") { inclusive = true }
                                launchSingleTop = true }
                            if (currentUser != null) {
                                auth.signOut()
                            }
                            Toast.makeText(context,"Başarıyla çıkış yapıdı.",Toast.LENGTH_SHORT).show()
                        }
                        , verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Çıkış Yap", color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.padding(start = 12.dp))
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Çıkış Yap",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(28.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                    }

                }

            }
        }

    }
}