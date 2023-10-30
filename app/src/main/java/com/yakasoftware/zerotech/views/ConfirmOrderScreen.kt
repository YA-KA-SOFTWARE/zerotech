package com.yakasoftware.zerotech.views

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

@Composable
fun ConfirmOrderScreen(navController: NavHostController) {
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    val db = Firebase.firestore
    val currentUserEmail = currentUser?.email

    data class PriceProduct(
        val price: String,
        val amount: String,
    )

    val price = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf("") }

    val basList = remember { mutableStateListOf<PriceProduct>() }

    db.collection("basket").whereEqualTo("email", currentUserEmail).get()
        .addOnSuccessListener { documents ->
            basList.clear()
            for (document in documents) {
                val basketData: Map<String, Any> = document.data
                price.value = basketData["price"].toString()
                amount.value = basketData["amount"].toString()
                basList.add(
                    PriceProduct(
                        price.value,
                        amount.value
                    )
                )
            }
        }
    val totalPrice = remember { mutableStateOf(0.0) }

    fun calculateTotalPrice(basList: List<PriceProduct>): Double {
        var totalPrice = 0.0
        for (baskets in basList) {
            totalPrice += baskets.price.toFloat() * baskets.amount.toInt()
        }
        return totalPrice
    }
    totalPrice.value = calculateTotalPrice(basList)
    val context = LocalContext.current
    val ibanText = remember {
        mutableStateOf("TR71 0004 6000 8000 1767 53")
    }
    val fontSize = 18.dp

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
    val firstLetter = name.value.firstOrNull()?.uppercaseChar() ?: ' '
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.onSecondary

        )
    )

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Column {

            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                                    .border(
                                        BorderStroke(
                                            2.dp,
                                            MaterialTheme.colorScheme.onBackground
                                        ), CircleShape
                                    )
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
            }

            val annotatedIbanText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 16.sp
                    )
                ) {
                    append(ibanText.value)
                }
            }
            val clipboardManager = LocalClipboardManager.current

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(300.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(MaterialTheme.colorScheme.onTertiary),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        //iban
                        Column {
                            Text(text = "IBAN",
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
                                fontSize = with(LocalDensity.current) {
                                    fontSize.toSp()
                                }
                            )

                            Spacer(modifier = Modifier.padding(top = 30.dp))

                            Text(text = ibanText.value, color =
                            MaterialTheme.colorScheme.onSecondary,
                                fontSize = with(LocalDensity.current) {
                                    fontSize.toSp()
                                }
                            )
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Kopyala",
                            tint = MaterialTheme.colorScheme.tertiary, // İkon rengini ayarlayın
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    clipboardManager.setText(annotatedIbanText)
                                    Toast
                                        .makeText(context, "İban kopyalandı.", Toast.LENGTH_SHORT)
                                        .show()

                                }
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))



                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(MaterialTheme.colorScheme.primary),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.padding(start = 10.dp))

                        Column {
                            Text(text = "Alıcı",
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
                                fontSize = with(LocalDensity.current) {
                                    fontSize.toSp()
                                }
                            )

                            Spacer(modifier = Modifier.padding(top = 30.dp))

                            Text(text = "Aziz Yağmur", color =
                            MaterialTheme.colorScheme.onSecondary,
                                fontSize = with(LocalDensity.current) {
                                    fontSize.toSp()
                                }
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }


                    Spacer(modifier = Modifier.weight(1f))



                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(MaterialTheme.colorScheme.onTertiary),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = totalPrice.value.toString() + "₺",
                            color = Color(255, 90, 60, 255),
                            fontSize = with(LocalDensity.current) {
                                fontSize.toSp()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(78, 255, 129, 200)),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(75.dp)
                    ) {

                        Column {
                            //icon
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Kopyala",
                                tint = Color.Black, // İkon rengini ayarlayın
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Spacer(modifier = Modifier.padding(top = 5.dp))

                            Text(text = "Bu kısım bilgilendirme yeridir", color =Color.Black,
                                fontSize = with(LocalDensity.current) {
                                    fontSize.toSp()
                                },
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }


            }
        }
    }
}