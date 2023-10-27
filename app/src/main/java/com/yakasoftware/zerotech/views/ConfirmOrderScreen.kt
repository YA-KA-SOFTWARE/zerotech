package com.yakasoftware.zerotech.views

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun ConfirmOrderScreen(navController: NavHostController) {
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    val db = Firebase.firestore
    val currentUserEmail = currentUser?.email
    val totalPrice = remember {
        mutableStateOf("")
    }
    var total = 0.0 // Toplam fiyatı saklayacak değişken
    if (currentUserEmail != null) {
        db.collection("basket")
            .whereEqualTo("email", currentUserEmail)
            .get()
            .addOnSuccessListener { documents ->


                for (document in documents) {
                    val price = document.getString("price")
                    if (price != null) {
                        total += price.toDouble() // Her belgedeki fiyatı toplama ekleyin
                    }
                }

                // Toplam fiyatı kullanabilirsiniz
                println("Toplam Fiyat: $total")
            }
            .addOnFailureListener { exception ->
                // Hata durumunda ne yapmanız gerektiğini burada ele alabilirsiniz
            }
    }
    val context = LocalContext.current
    val ibanText =  remember {
        mutableStateOf("TR71 0004 6000 8000 1767 53")
    }
    val fontSize = 18.dp
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            val annotatedIbanText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary, fontSize = 16.sp)) {
                    append(ibanText.value)
                }
            }
            val clipboardManager = LocalClipboardManager.current
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
            //iban
            Text(text = "İBAN: ${ibanText.value}", color =
            MaterialTheme.colorScheme.tertiary,
                fontSize = with(LocalDensity.current){
                    fontSize.toSp()
                }
            )
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
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Alıcı: AZİZ YAĞMUR", color = MaterialTheme.colorScheme.tertiary,
                    fontSize = with(LocalDensity.current) {
                        fontSize.toSp()
                    }
                )
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = total.toString(), color = MaterialTheme.colorScheme.tertiary,
                    fontSize = with(LocalDensity.current) {
                        fontSize.toSp()
                    }
                )
            }
        }
    }
}