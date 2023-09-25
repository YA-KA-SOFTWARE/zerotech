package com.yakasoftware.zerotech.views


import android.widget.Space
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

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

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
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
import com.yakasoftware.zerotech.Lines.SheetBarLine
import com.yakasoftware.zerotech.Lines.SimpleLine
import com.yakasoftware.zerotech.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdressScreen(navController: NavHostController) {
    val isClicked = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxSize()
        .blur(radius = if (isClicked.value) 10.dp else 0.dp), color = MaterialTheme.colorScheme.primary) {
        val context = LocalContext.current
        val db = Firebase.firestore
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        val email = currentUser?.email
        val name = remember {
            mutableStateOf("")
        }
        val surname = remember {
            mutableStateOf("")
        }
        val phoneNumber = remember {
            mutableStateOf("")
        }

        val adresText = remember {
            mutableStateOf("")
        }
        val numara = remember {
            mutableStateOf("")
        }



        if (currentUser != null) {
            db.collection("users").document(email.toString())
                .get()
                .addOnSuccessListener {
                    val data = it.data
                    name.value = data?.get("name") as String
                    surname.value = data["surname"] as String
                    phoneNumber.value = data["phoneNumber"] as String

                }

        }

        Surface(modifier = Modifier.fillMaxSize().clickable (enabled = isClicked.value){ isClicked.value = false  }, color = MaterialTheme.colorScheme.primary) {
            val firstLetter = name.value.firstOrNull()?.uppercaseChar() ?: ' '
            val gradientBrush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.onSecondary

                )
            )

            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(0, 0, 10, 10))
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(gradientBrush)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.padding(top = 30.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "Profilim", color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.secondary)
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
                            Text(
                                text = name.value,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = surname.value,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
                val sideBarFontSize = 24.dp
                Spacer(modifier = Modifier.padding(top = 28.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Adres Defteri", color = MaterialTheme.colorScheme.secondary,
                        fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Icon(
                        painter = painterResource(R.drawable.adresslogo),
                        contentDescription = "Adres Defteri",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(26.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth(0.750f)) {
                        SimpleLine()
                    }
                }
                Spacer(modifier = Modifier.padding(top = 12.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLine()
                    }
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    LazyColumn() {
                        items(5) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Spacer(modifier = Modifier.padding(start = 12.dp))
                                Icon(
                                    imageVector = Icons.Default.AddLocation, // Simgenizin adını buraya ekleyin
                                    contentDescription = "İsim Simgesi",
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(
                                            MaterialTheme.colorScheme.onPrimary,
                                            RoundedCornerShape(5.dp)
                                        )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = "İsim: ${name.value} ",
                                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() },
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.weight(1f))

                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                SimpleLine()
                            }
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    LargeFloatingActionButton(
                        onClick = { isClicked.value = !isClicked.value },
                        modifier = Modifier.clickable(enabled = !isClicked.value){},
                    ) {
                        Icon(Icons.Filled.AddCircleOutline, "Localized description")
                    }
                }


            }

        }

    }
    if (isClicked.value)
        BottomSheet(isClicked.value)
}@ExperimentalMaterial3Api
@Composable
fun BottomSheet(isClicked: Boolean) {

    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.750f)
            ) {
                SheetBarLine()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)
                )
                .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                .border(
                    BorderStroke(3.dp, MaterialTheme.colorScheme.onSecondary),
                    RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)
                ),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    val comments = remember {
                        mutableStateOf("")
                    }
                    val comments1 = remember {
                        mutableStateOf("")
                    }
                    val comments2 = remember {
                        mutableStateOf("")
                    }
                    val comments3 = remember {
                        mutableStateOf("")
                    }
                    Spacer(modifier = Modifier.padding(top = 25.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                    ) {
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                        Icon(
                            imageVector = Icons.Default.DriveFileRenameOutline, // Simgenizin adını buraya ekleyin
                            contentDescription = "İsim Simgesi",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    MaterialTheme.colorScheme.onSecondary,
                                    RoundedCornerShape(5.dp)
                                )
                        )
                        Spacer(modifier = Modifier.padding(start =5.dp))
                        Text(
                            text = "Yeni Adres Ekle",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 45.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )

                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Adres Başlığı: (örn: ev, yurt..)",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(top = 15.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Şehir",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Semt",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Mahalle",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Sokak",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Apartman Adı",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Apartman Numarası",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Daire Numarası",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Posta Kodu",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Kat",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Şirket",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Telefon Numarası",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            LazyRow {
                                item {
                                    OutlinedTextField(
                                        value = comments2.value,
                                        onValueChange = {
                                            comments2.value = it
                                        },
                                        label = {
                                            Text(
                                                text = "Adres Tarifi",
                                                color = Color.LightGray
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedLabelColor = Color.Transparent,
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedLabelColor = Color.LightGray,
                                            unfocusedBorderColor = Color.Transparent,
                                            cursorColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(16.dp),

                                        )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                }
            }
        }
    }
}



