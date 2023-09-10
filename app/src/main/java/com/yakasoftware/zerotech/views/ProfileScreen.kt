package com.yakasoftware.zerotech.views

import android.util.Log
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class UserData(val name: String , val userName: String, val phoneNumber: String)
@Composable
fun ProfileScreen(navController: NavHostController) {
    val db = Firebase.firestore
    val auth = Firebase.auth
    val currentUser = auth.currentUser!!
    val email = currentUser.email!!

    val name = remember {
        mutableStateOf("")
    }
    val surname = remember{
        mutableStateOf("")
    }
    val phoneNumber = remember {
        mutableStateOf("")
    }
    db.collection("users").document(email)
        .get()
        .addOnSuccessListener {
            val data = it.data
            name.value = data?.get("name") as String ?: " "
            surname.value = data?.get("surname") as  String ?: ""
            phoneNumber.value = data?.get("phoneNumber") as String ?: ""

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
            Box(modifier = Modifier
                .clip(RoundedCornerShape(0,0,10,10))
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
                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = name.value,fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(text = surname.value, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

        }

        /*
        Column() {
            Text(text = name.value, color = Color.White)
            Text(text = surname.value, color = Color.White)
            Text(text = phoneNumber.value , color = Color.White)
           }

         */
        }
    }