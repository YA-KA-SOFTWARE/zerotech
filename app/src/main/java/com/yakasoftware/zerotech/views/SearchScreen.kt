package com.yakasoftware.zerotech.views

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Speaker
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine
import com.yakasoftware.zerotech.Lines.SimpleLineWhite
import com.yakasoftware.zerotech.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URLEncoder



@SuppressLint("SuspiciousIndentation", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    val isMenuVisible = remember {
        mutableStateOf(false)
    }
    val searchBar = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val barVisible = remember {
        mutableStateOf(false)
    }
    val focusRequester = FocusRequester()
    val sidebarWidth by animateDpAsState(
        targetValue = if (barVisible.value) (LocalConfiguration.current.screenWidthDp * 0.50f).dp else 0.dp,
        animationSpec = if (barVisible.value) {
            tween(
                durationMillis = 500,
                easing = LinearOutSlowInEasing // Bu yavaşça kapanmasını sağlar
            )
        } else {
            tween(
                durationMillis = 500,
                easing = LinearOutSlowInEasing // Yavaşça açılması için burada da kullanabilirsiniz
            )
        },
        label = "Yan menü animasyonu"
    )

    val auth = Firebase.auth
    val currentUser = auth.currentUser
    val db = Firebase.firestore
    val name = remember {
        mutableStateOf("")
    }
    val email = currentUser?.email

    if (auth.currentUser != null) {
        db.collection("users").document(email!!)
            .get()
            .addOnSuccessListener {
                val data = it.data
                name.value = data?.get("name") as String

            }
    }
    val basketCount = remember {
        mutableStateOf(0)
    }
    if (auth.currentUser != null) {
        val collectionRef = db.collection("basket")
            .whereEqualTo("email", currentUser!!.email)
        collectionRef.get()
            .addOnSuccessListener { documents ->
                basketCount.value = documents.size()

            }
            .addOnFailureListener {
                println(it)
            }
    }

    val firstLetter = name.value.firstOrNull()?.uppercaseChar() ?: ' '
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.padding(top = 30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {

            Icon(
                imageVector = Icons.Default.ArrowCircleLeft,
                contentDescription = "Menü Açma",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .size(35.dp)
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(60.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(16.dp)
                        )
                        .border(
                            BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
                            RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(0.1f))
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Arama",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.weight(0.1f))
                        TextField(
                            value = searchBar.value,
                            onValueChange = { searchBar.value = it },
                            placeholder = {
                                Text(
                                    text = "Ne Aramıştınız",
                                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.7f)
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = MaterialTheme.colorScheme.onSecondary,
                                containerColor = Color.Transparent,
                                focusedLabelColor = MaterialTheme.colorScheme.onSecondary,
                                cursorColor = MaterialTheme.colorScheme.secondary,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                                ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = androidx.compose.ui.text.input.ImeAction.Search
                            ),
                            modifier = Modifier
                                // add focusRequester modifier
                                .focusRequester(focusRequester)
                        )
                        LaunchedEffect(Unit) {
                            focusRequester.requestFocus()
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }

                }
            }}
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                    SimpleLine()
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            LazyColumn(
                Modifier
                    .fillMaxSize()
            ) {
                items(15) { index ->
                    val isVisiblee = remember { mutableStateOf(false) }

                    LaunchedEffect(isVisiblee.value) {
                        delay(index * 5L)
                        isVisiblee.value = true
                    }

                    // Offset animasyonu ile öğeleri animasyonlu bir şekilde görünür yapın
                    val offset by animateDpAsState(
                        targetValue = if (isVisiblee.value) 0.dp else 100.dp, // Öğelerin kaydırılacağı konum
                        animationSpec = tween(durationMillis = 500), label = "Animasyonlu ürün gösterimi"
                    )

                    Spacer(modifier = Modifier.padding(top = 10.dp))

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = offset),
                        horizontalArrangement = Arrangement.Center) {
                        Row(modifier = Modifier.fillMaxWidth(0.4f)) {
                            SimpleLine()
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .offset(y = offset) // Offset animasyonunu burada kullanın
                            .animateContentSize()
                    ) {
                        Row(modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onSecondary), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Box (modifier = Modifier
                                .height(75.dp)
                                .width(80.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.primary,
                                            Color.Transparent,
                                        ),
                                        startX = 0f,
                                        endX = 200f // Yüksekliği ayarlayın
                                    )
                                )){

                            }
                            Text(text = "Aggiy Hoparlör 7-Tro...", color = Color.Black)
                            Column {
                                Text(text = "307,99$", color = Color.DarkGray, textDecoration = TextDecoration.LineThrough, fontSize = 12.sp, modifier = Modifier.padding(start = 10.dp))
                                Text(text = "153,99$", color = Color(182, 27, 27, 255))
                            }
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Favorilerim",
                                tint = Color.DarkGray,
                                modifier = Modifier
                                    .size(25.dp)
                                    .background(
                                        Color.White.copy(alpha = 0.3f),
                                        CircleShape
                                    ))

                        }

                    }
                }
            }
        }
    }
}
