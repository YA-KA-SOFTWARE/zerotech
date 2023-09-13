package com.yakasoftware.zerotech.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val firebaseAuth = Firebase.auth
    val isLoading = remember {
        mutableStateOf(true)
    }
    val userEmail = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    LaunchedEffect(isLoading.value) {
        delay(1000) // 1 saniye bekleme
        isLoading.value = false // Bekleme sona erdiğinde loading durumunu değiştir
    }
    if (isLoading.value) {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary){
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center), color = MaterialTheme.colorScheme.secondary
                )
            }
    }
    else {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {

            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.logonoback),
                        contentDescription = "Logo",
                        modifier = Modifier.size(90.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))

                }
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Üye Girişi",
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier
                        .fillMaxSize(0.8f)
                        .background(MaterialTheme.colorScheme.primary)
                        .border(
                            width = 0.5.dp, // Kenarlık kalınlığı
                            color = MaterialTheme.colorScheme.secondary, // Kenarlık rengi
                            shape = RoundedCornerShape(20.dp)
                        ), contentAlignment = Alignment.Center) {

                        Column(modifier = Modifier.fillMaxSize()) {
                            Spacer(modifier = Modifier.padding(top = 35.dp))
                            Row(modifier = Modifier.fillMaxWidth(),
                               horizontalArrangement = Arrangement.Center) {
                                Image(
                                    bitmap = ImageBitmap.imageResource(id = R.drawable.techprofile),
                                    contentDescription = "Profil Fotağrafı",
                                    modifier = Modifier.size(150.dp)
                                )
                            }
                            Spacer(modifier = Modifier.padding(top = 35.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                    OutlinedTextField(value = userEmail.value, onValueChange = {
                                        userEmail.value = it
                                    }, label = { Text(text = "Email", color = MaterialTheme.colorScheme.secondary)},
                                      colors = TextFieldDefaults.outlinedTextFieldColors(
                                          focusedLabelColor = MaterialTheme.colorScheme.secondary,
                                          focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                          unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                                          unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                          cursorColor = MaterialTheme.colorScheme.secondary
                                      ), shape = RoundedCornerShape(10.dp)
                                    )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            val passwordVisibility = remember { mutableStateOf(false) }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OutlinedTextField(
                                    value = password.value,
                                    onValueChange = {
                                        password.value = it
                                    },
                                    label = { Text(text = "Şifre", color = MaterialTheme.colorScheme.secondary) },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedLabelColor = MaterialTheme.colorScheme.secondary,
                                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                        unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                        cursorColor = MaterialTheme.colorScheme.secondary
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Password, // Şifre klavyesi kullan
                                        imeAction = ImeAction.Done // Done düğmesini göster
                                    ),
                                    trailingIcon = {
                                        IconButton(
                                            onClick = {
                                                passwordVisibility.value = !passwordVisibility.value
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                                contentDescription = if (passwordVisibility.value) "Şifreyi Gizle" else "Şifreyi Göster",
                                                tint = MaterialTheme.colorScheme.secondary
                                            )
                                        }
                                    },
                                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.weight(1f))
                                Button(onClick = {
                                    if(userEmail.value.isNotEmpty()) {
                                        if (userEmail.value.endsWith(".com")) {
                                            firebaseAuth.sendPasswordResetEmail(userEmail.value)
                                                .addOnCompleteListener { task ->
                                                    if (task.isSuccessful) {
                                                        // Şifre sıfırlama e-postası gönderildi
                                                        Toast.makeText(context,"Şifre sıfırlama maili ${userEmail.value} adresine gönderildi.",
                                                            Toast.LENGTH_SHORT).show()
                                                    } else {
                                                        // Hata oluştu
                                                        Toast.makeText(context,"Şifre sıfırlama başarısız.",
                                                            Toast.LENGTH_SHORT).show()
                                                    }
                                                }
                                        }else {
                                            Toast.makeText(context,"Geçerli bir mail adresi girin.", Toast.LENGTH_SHORT).show()
                                        }
                                    }else {
                                        Toast.makeText(context,"Bu e-posta adresine ait kayıt bulunamadı.",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                },
                                    shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)) {
                                    Text(text = "Şifremi Unuttum", color = MaterialTheme.colorScheme.secondary)
                                }
                                Spacer(modifier = Modifier.weight(1f))
                            }
                            // Giriş Yap Butonu
                            Row(Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.weight(1f))

                                OutlinedButton(onClick = {
                                    if (userEmail.value.isNotEmpty() && password.value.isNotEmpty()) {
                                        if (userEmail.value.endsWith(".com")) {
                                            FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail.value,password.value)
                                                .addOnCompleteListener { task ->
                                                    if (task.isSuccessful) {
                                                        val user = FirebaseAuth.getInstance().currentUser
                                                        if (user != null && user.isEmailVerified) {
                                                            navController.navigate("main_screen"){
                                                                popUpTo("main_screen") { inclusive = true }
                                                                launchSingleTop = true
                                                            }
                                                            Toast.makeText(context,"Başarıyla giriş yapıldı.",Toast.LENGTH_SHORT).show()
                                                        }else {
                                                            Toast.makeText(context,"Eposta adresinizi doğrulayın.",Toast.LENGTH_SHORT).show()
                                                        }

                                                    }else {
                                                        Toast.makeText(context,"Hesap bulunamadı",Toast.LENGTH_SHORT).show()
                                                    }

                                                }

                                        }else {
                                            Toast.makeText(context,"Geçerli bir email adresi girin.",Toast.LENGTH_SHORT).show()
                                        }
                                    }else {
                                        Toast.makeText(context,"Giriş yapmak için gerekli alanları doldurun.",Toast.LENGTH_SHORT).show()
                                    }
                                }) {
                                    Text(text = "Giriş Yap", color = MaterialTheme.colorScheme.secondary, letterSpacing = 1.sp, fontSize = 20.sp)
                                }
                                Spacer(modifier = Modifier.weight(1f))

                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = "Üye Ol",
                                    color = MaterialTheme.colorScheme.secondary,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier.clickable {
                                        navController.navigate("register_screen")
                                    }
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }

                        }

                    }
                }

            }
        }

    }
}