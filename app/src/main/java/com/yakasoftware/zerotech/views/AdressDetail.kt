package com.yakasoftware.zerotech.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdressDetail(navController: NavHostController,documentId: String) {
    val context = LocalContext.current
    val city = remember {
        mutableStateOf("")
    }
    val neighbourhood = remember {
        mutableStateOf("")
    }
    val street = remember {
        mutableStateOf("")
    }
    val direction = remember {
        mutableStateOf("")
    }
    val district = remember {
        mutableStateOf("")
    }
    val adressTitle = remember {
        mutableStateOf("")
    }
    
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    val currentUserEmail = currentUser?.email
    val db = Firebase.firestore

    if (currentUserEmail != null)
    db.collection("adress")
        .document(currentUserEmail)
        .collection(currentUserEmail)
        .document(documentId)
        .get()
        .addOnSuccessListener { 
            val data = it.data
            city.value = data?.get("city") as String
            direction.value = data["direction"] as String
            district.value = data["district"] as String
            neighbourhood.value = data["neighbourhood"] as String
            adressTitle.value = data["adresstitle"] as String
            street.value = data["street"] as String
        }
        .addOnFailureListener { 
            println(it)
        }
    val uptadeTitle = remember {
        mutableStateOf("")
    }
    val uptadeCity = remember {
        mutableStateOf("")
    }
    val uptadeDistrict = remember {
        mutableStateOf("")
    }
    val uptadeNeighbourhood = remember {
        mutableStateOf("")
    }
    val uptadeStreet = remember {
        mutableStateOf("")
    }
    val uptadeDirection = remember {
        mutableStateOf("")
    }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {

        val fontSizeMain = 24.dp
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(top = 48.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Adres Güncelleme", color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = with(LocalDensity.current){fontSizeMain.toSp()}
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.fillMaxWidth(0.750f)) {
                    SimpleLine()
                }
            }
            Spacer(modifier = Modifier.padding(top = 70.dp))
            Column(Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedTextField(value = uptadeTitle.value, onValueChange = {
                        uptadeTitle.value = it.trim()
                    }, label = { Text(text = "Adres Başlığı", color = MaterialTheme.colorScheme.secondary)},
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        placeholder = { Text(text = adressTitle.value, color = Color.Gray)},
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = MaterialTheme.colorScheme.secondary,
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.secondary
                        ), shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = uptadeCity.value,
                        onValueChange = { uptadeCity.value = it.trim() },
                        label = { Text(text = "İl", color = MaterialTheme.colorScheme.secondary) },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        placeholder = { Text(text = city.value, color = Color.Gray) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = MaterialTheme.colorScheme.secondary,
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.secondary
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    OutlinedTextField(
                        value = uptadeDistrict.value,
                        onValueChange = { uptadeDistrict.value = it.trim() },
                        label = { Text(text = "İlçe", color = MaterialTheme.colorScheme.secondary) },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        placeholder = { Text(text = district.value, color = Color.Gray) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = MaterialTheme.colorScheme.secondary,
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.secondary
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(top = 8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = uptadeNeighbourhood.value,
                        onValueChange = { uptadeNeighbourhood.value = it.trim() },
                        label = { Text(text = "Mahalle", color = MaterialTheme.colorScheme.secondary) },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        placeholder = { Text(text = neighbourhood.value, color = Color.Gray) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = MaterialTheme.colorScheme.secondary,
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.secondary
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    OutlinedTextField(
                        value = uptadeStreet.value,
                        onValueChange = { uptadeStreet.value = it.trim() },
                        label = { Text(text = "Sokak", color = MaterialTheme.colorScheme.secondary) },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        placeholder = { Text(text = street.value, color = Color.Gray) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = MaterialTheme.colorScheme.secondary,
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.secondary
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(top = 8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedTextField(value = uptadeDirection.value, onValueChange = {
                        uptadeDirection.value = it.trim()
                    }, label = { Text(text = "Adres Tarifi", color = MaterialTheme.colorScheme.secondary)},
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        placeholder = { Text(text = direction.value, color = Color.Gray)},
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = MaterialTheme.colorScheme.secondary,
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.secondary
                        ), shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.padding(top = 24.dp))
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedButton(onClick = {
                        if (currentUserEmail != null) {
                            if (uptadeTitle.value != "") {
                                db.collection("adress")
                                    .document(currentUserEmail)
                                    .collection(currentUserEmail)
                                    .document(documentId)
                                    .update("adresstitle",uptadeTitle.value)
                                    .addOnSuccessListener {
                                        println("Günceledi")
                                    }
                                    .addOnFailureListener {
                                        println(it)
                                    }

                            }
                            if (uptadeCity.value != "") {
                                db.collection("adress")
                                    .document(currentUserEmail)
                                    .collection(currentUserEmail)
                                    .document(documentId)
                                    .update("city",uptadeCity.value)
                                    .addOnSuccessListener {
                                        println("Güncelledi")
                                    }
                                    .addOnFailureListener {
                                        println(it)
                                    }

                            }
                            if (uptadeNeighbourhood.value != "") {
                                db.collection("adress")
                                    .document(currentUserEmail)
                                    .collection(currentUserEmail)
                                    .document(documentId)
                                    .update("neighbourhood",uptadeNeighbourhood.value)
                                    .addOnSuccessListener {
                                        println("Güncel")
                                    }
                                    .addOnFailureListener {
                                        println(it)
                                    }

                            }
                            if (uptadeStreet.value != "") {
                                db.collection("adress")
                                    .document(currentUserEmail)
                                    .collection(currentUserEmail)
                                    .document(documentId)
                                    .update("street",uptadeStreet.value)
                                    .addOnSuccessListener {
                                        println("Güncel")
                                    }
                                    .addOnFailureListener {
                                        println(it)
                                    }

                            }
                            if (uptadeDirection.value != "") {
                                db.collection("adress")
                                    .document(currentUserEmail)
                                    .collection(currentUserEmail)
                                    .document(documentId)
                                    .update("direction",uptadeDirection.value)
                                    .addOnSuccessListener {
                                        println("Güncel")
                                    }
                                    .addOnFailureListener {
                                        println(it)
                                    }

                            }
                            Toast.makeText(context,"Adres başarıyla güncellendi",Toast.LENGTH_SHORT).show()
                            navController.navigate("adress_screen"){
                                popUpTo("adress_screen"){
                                    inclusive = true
                                }
                            }
                        }


                    }) {
                        Text(text = "Güncelle", color = MaterialTheme.colorScheme.secondary,
                            fontSize = 20.sp,
                            letterSpacing = 1.sp)
                    }
                    Spacer(modifier = Modifier.weight(1f))

                }
                Spacer(modifier = Modifier.padding(top = 32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Kargonuzun daha hızlı ve güvenli ulaşabilmesi için lütfen adres bilgilerinizi doğru ve eksiksiz giriniz.",
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}