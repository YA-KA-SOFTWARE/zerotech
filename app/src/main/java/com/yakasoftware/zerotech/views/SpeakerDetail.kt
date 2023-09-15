package com.yakasoftware.zerotech.views


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.navigation.NavHostController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun SpeakerDetailScreen(navController: NavHostController, productTitle: String) {
    val db = Firebase.firestore
    val proCollection = db.collection("products").document("speakers")
    val docRef = proCollection.collection("Aggiy AG-S21 Bluetooth Hoparlör")
    val photo1 = remember{ mutableStateOf("") }
    val photo2 = remember{ mutableStateOf("") }
    val photo3 = remember{ mutableStateOf("") }
    val photo4 = remember{ mutableStateOf("") }
    val price = remember{ mutableStateOf("") }
    val oldPrice = remember{ mutableStateOf("") }
    val discount = remember{ mutableStateOf("") }
    Surface(Modifier.fillMaxSize()) {
        println(productTitle)
        docRef.whereEqualTo("title",productTitle)
            .get()
            .addOnSuccessListener{documents ->
                for (document in documents){
                    photo1.value = document.getString("photo1")!!
                    photo2.value = document.getString("photo2")!!
                    photo3.value = document.getString("photo3")!!
                    photo4.value = document.getString("photo4")!!
                    price.value = document.getString("price")!!
                    oldPrice.value = document.getString("oldPrice")!!
                    discount.value = document.getString("discount")!!
                }

        }
        Column {
            Text(text = productTitle, color = MaterialTheme.colorScheme.secondary)

        }
    }
}