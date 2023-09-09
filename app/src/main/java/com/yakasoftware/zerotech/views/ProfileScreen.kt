package com.yakasoftware.zerotech.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    db.collection("users").document(email).get().addOnSuccessListener {

    }
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

        Column() {
            Text(text = name.value, color = Color.White)
            Text(text = surname.value, color = Color.White)
            Text(text = phoneNumber.value , color = Color.White)
           }
        }
    }