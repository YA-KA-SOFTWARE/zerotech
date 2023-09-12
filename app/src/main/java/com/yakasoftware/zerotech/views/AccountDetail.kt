package com.yakasoftware.zerotech.views

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerInputModifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailScreen(navController: NavHostController) {
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
    if (currentUser != null) {
        db.collection("users").document(email.toString())
            .get()
            .addOnSuccessListener {
                val data = it.data
                name.value = data?.get("name") as String ?: " "
                surname.value = data?.get("surname") as String ?: ""
                phoneNumber.value = data?.get("phoneNumber") as String ?: ""

            }

    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        val firstLetter = name.value.firstOrNull()?.uppercaseChar() ?: ' '
        val gradientBrush = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFFF8500),  // Tema Reni
                Color(0xFFFF6100)   // Tema Rengi Koyu Hali
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Hesap Bilgileri", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Hesap Bilgileri",
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

            Spacer(modifier = Modifier.padding(16.dp))

            val isim = remember {
                mutableStateOf("")
            }
            val soyisim = remember {
                mutableStateOf("")
            }
            val numara = remember {
                mutableStateOf("")
            }
            val mail = remember {
                mutableStateOf("")
            }
            val duzenlemeButonu = remember {
                mutableStateOf(false)
            }



            Column(modifier = Modifier.fillMaxWidth()) {
                if (!duzenlemeButonu.value) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "İsim: " + isim.value,
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Soyisim: " + soyisim.value,
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Numara: " + numara.value,
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Mail: " + mail.value,
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                //Hesap Bilgilerini Düzenleme
                else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "İsim: ",
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        OutlinedTextField(
                            value = isim.value,
                            onValueChange = { isim.value = it },
                            label = { Text("İsim") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                cursorColor = MaterialTheme.colorScheme.secondary
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Soyisim: ",
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(top = 20.dp)

                        )
                        OutlinedTextField(
                            value = soyisim.value,
                            onValueChange = { soyisim.value = it },
                            label = { Text("Soyisim") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                cursorColor = MaterialTheme.colorScheme.secondary
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Numara: ",
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        OutlinedTextField(
                            value = numara.value,
                            onValueChange = { numara.value = it },
                            label = { Text("Numara") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                cursorColor = MaterialTheme.colorScheme.secondary
                             ),
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Mail: ",
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        OutlinedTextField(
                            value = mail.value,
                            onValueChange = { mail.value = it },
                            label = { Text("Mail") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                cursorColor = MaterialTheme.colorScheme.secondary
                            ),

                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = {
                        duzenlemeButonu.value = true
                    }

                ) {
                    Text(
                        text = "Düzenle",
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 25.sp
                    )
                }

                OutlinedButton(
                    onClick = {
                        duzenlemeButonu.value = false
                    },
                ) {
                    Text(
                        text = "Kaydet",
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 25.sp
                    )
                }
            }
        }
    }
}


