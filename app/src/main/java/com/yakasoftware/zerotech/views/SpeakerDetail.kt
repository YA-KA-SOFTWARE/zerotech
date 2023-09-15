package com.yakasoftware.zerotech.views


import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        Column(modifier = Modifier.fillMaxSize()){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .border(BorderStroke(5.dp, MaterialTheme.colorScheme.onSecondary)),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ){
                Box(modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(5.dp, MaterialTheme.colorScheme.onSecondary))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,//
                                MaterialTheme.colorScheme.onPrimary
                            ),
                            startY = 0f,
                            endY = 800f
                        )
                    )){

                    //Resimler bu kısıma eklenecek - ayarlama yapılacak sonrasında
                   // Image(painter = , contentDescription = )

                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Ürünün İsmi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ){
            Text(text = productTitle, color = MaterialTheme.colorScheme.secondary, textAlign = TextAlign.Center, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Yorum ve Yıldız ve FİYAT bilgisi
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {

                //Yıldızlar
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.StarBorder,
                            contentDescription = "Sepetim",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }

                //Yorum sayısı - Puanı - FİYAT BİLGİSİ
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)

                ){
                    Text(text = "(0) Yorum - 0 puan", fontSize = 10.sp, color = MaterialTheme.colorScheme.secondary)

                    //FİYAT BİLGİSİ
                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                        Text(text = "3131$", fontSize = 30.sp)
                    }

                }

            }
        }
    }
}