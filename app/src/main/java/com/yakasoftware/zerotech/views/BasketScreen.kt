package com.yakasoftware.zerotech.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("SimpleDateFormat")
@Composable
fun BasketScreen(navController: NavHostController) {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
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
                    name.value = data?.get("name") as String
                    surname.value = data["surname"] as String
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
                    Text(text = "Sepetim", color = MaterialTheme.colorScheme.secondary,
                        fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Icon(
                        imageVector = Icons.Default.ShoppingBasket,
                        contentDescription = "Sepetim",
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
                data class BasketProduct(
                    val title: String,
                    val photo1: String,
                    val price: String,
                    val oldPrice: String,
                    val discount: String,
                    val type: String,
                    val amount: String,
                    val date: com.google.firebase.Timestamp?,
                    val onay: Boolean
                )
                val oldPrice = remember { mutableStateOf("") }
                val price = remember { mutableStateOf("") }
                val photo1 = remember { mutableStateOf("") }
                val discount = remember { mutableStateOf("") }
                val type = remember { mutableStateOf("") }
                val title = remember { mutableStateOf("") }
                val amount = remember { mutableStateOf("") }
                val date = remember { mutableStateOf<com.google.firebase.Timestamp?>(null) }
                val onay = remember { mutableStateOf(false) }

                val basList = remember{ mutableStateListOf<BasketProduct>() }

                db.collection("basket").whereEqualTo("email",email).get().addOnSuccessListener { documents ->
                   basList.clear()
                    for (document in documents){
                        val basketData: Map<String,Any> = document.data
                        title.value = basketData["title"].toString()
                        oldPrice.value = basketData["oldPrice"].toString()
                        price.value = basketData["price"].toString()
                        photo1.value = basketData["photo1"].toString()
                        discount.value = basketData["discount"].toString()
                        type.value = basketData["type"].toString()
                        val dateValue = basketData["date"]
                        date.value = if (dateValue is com.google.firebase.Timestamp) {
                            dateValue
                        } else {
                            null // Değer "com.google.firebase.Timestamp" değilse, null olarak ayarlayın
                        }
                        amount.value = basketData["amount"].toString()
                        onay.value = basketData["onay"] as? Boolean ?: false
                        basList.add(BasketProduct(title.value,photo1.value,price.value,oldPrice.value,discount.value,type.value,amount.value,
                            date.value,onay.value))
                    }
                }

                LazyColumn{
                    items(basList.size){index ->
                        val baskets = basList[index]

                        Column() {
                            Text(text = baskets.title, color = MaterialTheme.colorScheme.secondary)
                            Text(text = baskets.amount, color = MaterialTheme.colorScheme.secondary)
                            Text(text = baskets.discount, color = MaterialTheme.colorScheme.secondary)
                            Text(text = baskets.oldPrice, color = MaterialTheme.colorScheme.secondary)
                            Text(text = baskets.price, color = MaterialTheme.colorScheme.secondary)
                            Text(text = baskets.type, color = MaterialTheme.colorScheme.secondary)
                            val painter = rememberAsyncImagePainter(model = baskets.photo1)
                            Image(painter = painter, contentDescription = null,Modifier.size(50.dp) )

                            val dateFormat = SimpleDateFormat("dd/MM/yyyy (HH:mm)",Locale.getDefault())
                            val date = baskets.date?.toDate()
                            val formattedDate = date?.let { dateFormat.format(it) }


                            Text(text = formattedDate.toString() ,color = MaterialTheme.colorScheme.secondary )

                        }

                    }
                }
            }

        }
    }
}