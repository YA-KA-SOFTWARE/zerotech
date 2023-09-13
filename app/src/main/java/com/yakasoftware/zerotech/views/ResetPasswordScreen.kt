package com.yakasoftware.zerotech.views

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(navController: NavHostController) {

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

    val currentPassword = remember {
        mutableStateOf("")
    }
    val newPassword = remember {
        mutableStateOf("")
    }
    val newPasswordAgain = remember {
        mutableStateOf("")
    }

    val fontSizeInDp = 26.dp
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
            Spacer(modifier = Modifier.padding(top = 36.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Şifreyi Değiştir", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Şifreyi Değiştir",
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
            Spacer(modifier = Modifier.padding(top = 45.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val passwordVisibility = remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = currentPassword.value,
                    onValueChange = {
                        currentPassword.value = it
                    },
                    label = {
                        Text(
                            text = "Mevcut Şifre",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    },
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
            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val passwordVisibility = remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = newPassword.value,
                    onValueChange = {
                        newPassword.value = it
                    },
                    label = {
                        Text(
                            text = "Yeni Şifre",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    },
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
            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val passwordVisibility = remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = newPasswordAgain.value,
                    onValueChange = {
                        newPasswordAgain.value = it
                    },
                    label = {
                        Text(
                            text = "Yeni Şifre Tekrar",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    },
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
            Spacer(modifier = Modifier.padding(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                val resetAuth = Firebase.auth
                val resetCurrentUser = resetAuth.currentUser
                val resetUserEmail = resetCurrentUser?.email
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                              if (resetCurrentUser != null) {
                                  resetAuth.sendPasswordResetEmail(resetUserEmail!!)
                                      .addOnCompleteListener { reset ->
                                          if (reset.isSuccessful) {
                                              Toast.makeText(context,"Sıfırlama maili ${resetUserEmail} adresine gönderildi.",Toast.LENGTH_SHORT).show()
                                          }else {
                                              Toast.makeText(context,"Şifre sıfırlama başarısız.",Toast.LENGTH_SHORT).show()
                                          }

                                      }

                              }

                    },
                    shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
                ) {
                    Text(text = "Şifremi Unuttum", color = MaterialTheme.colorScheme.secondary)
                }
                Spacer(modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                OutlinedButton(
                    onClick = {
                              if (currentPassword.value.isNotEmpty() && newPassword.value.isNotEmpty() && newPasswordAgain.value.isNotEmpty()) {
                                  if (newPassword.value == newPasswordAgain.value) {
                                      if(newPassword.value.length >= 7 && newPasswordAgain.value.length >= 7) {
                                          val firebaseUser = FirebaseAuth.getInstance().currentUser
                                          firebaseUser?.email?.let { email ->
                                              val credential = EmailAuthProvider.getCredential(email, currentPassword.value)
                                              firebaseUser.reauthenticate(credential).addOnCompleteListener { task ->
                                                  if (task.isSuccessful) {
                                                      firebaseUser.updatePassword(newPassword.value).addOnCompleteListener { task ->
                                                          if (task.isSuccessful) {
                                                              // Şifre güncellendi
                                                              navController.navigate("profile_screen"){
                                                                  popUpTo("main_screen") {
                                                                      inclusive = true
                                                                  }
                                                              }
                                                              Toast.makeText(context,"Şifre başarıyla güncellendi.",Toast.LENGTH_SHORT).show()
                                                          } else {
                                                              // Şifre güncellenemedi
                                                              Toast.makeText(context,"Şifre güncelleme başarısız.",Toast.LENGTH_SHORT).show()
                                                          }
                                                      }
                                                  } else {
                                                      // Mevcut şifre yanlış
                                                      Toast.makeText(context,"Mevcut şifre yanlış.",Toast.LENGTH_SHORT).show()
                                                  }
                                              }
                                          }
                                      }else {
                                          Toast.makeText(context,"Şifreler en az 7 karakterden oluşmalıdır.",Toast.LENGTH_SHORT).show()
                                      }
                                  }else {
                                      Toast.makeText(context,"Şifreler aynı olmak zorunda.",Toast.LENGTH_SHORT).show()
                                  }
                              }else {
                                  Toast.makeText(context,"Gerekli alanları doldurunuz.",Toast.LENGTH_SHORT).show()
                              }
                    },
                    shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
                ) {
                    Text(text = "Kaydet", color = MaterialTheme.colorScheme.secondary, letterSpacing = 1.sp,
                        fontWeight = FontWeight.Bold,  fontSize = with(LocalDensity.current) { fontSizeInDp.toSp()})
                }
                Spacer(modifier = Modifier.weight(1f))

            }
        }
    }
}
