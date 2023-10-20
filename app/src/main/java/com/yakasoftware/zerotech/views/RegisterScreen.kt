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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.R
import com.yakasoftware.zerotech.classes.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    val context = LocalContext.current
    val auth = Firebase.auth
    val db = Firebase.firestore
    val isLoading = remember {
        mutableStateOf(true)
    }
    val phoneNumber = remember {
        mutableStateOf("")
    }
    val userEmail = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }
    val surName = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordAgain = remember {
        mutableStateOf("")
    }

    val passwordVisibility = remember { mutableStateOf(false) }
    val passwordVisibilityAgain = remember { mutableStateOf(false) }
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
    }else {
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
                        text = "Kayıt Formu",
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
                        .fillMaxSize(0.925f)
                        .background(MaterialTheme.colorScheme.primary)
                        .border(
                            width = 0.5.dp, // Kenarlık kalınlığı
                            color = MaterialTheme.colorScheme.secondary, // Kenarlık rengi
                            shape = RoundedCornerShape(20.dp)
                        ), contentAlignment = Alignment.Center) {

                        Column(modifier = Modifier.fillMaxSize()) {
                            Spacer(modifier = Modifier.padding(top = 25.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                OutlinedTextField(value = name.value, onValueChange = {
                                    name.value = it.trim()
                                }, label = { Text(text = "İsim", color = MaterialTheme.colorScheme.secondary)},
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done
                                    ),
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
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                OutlinedTextField(value = surName.value, onValueChange = {
                                    surName.value = it.trim()
                                }, label = { Text(text = "Soyisim", color = MaterialTheme.colorScheme.secondary)},
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done
                                    ),
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
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                OutlinedTextField(value = userEmail.value, onValueChange = {
                                    userEmail.value = it.trim()
                                }, label = { Text(text = "Email", color = MaterialTheme.colorScheme.secondary)},
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done
                                    ),
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

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OutlinedTextField(
                                    value = password.value,
                                    onValueChange = {
                                        password.value = it.trim()
                                    },
                                    label = { Text(text = "Şifre", color = MaterialTheme.colorScheme.secondary) },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Password, // Şifre klavyesi kullan
                                        imeAction = ImeAction.Done
                                    ),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedLabelColor = MaterialTheme.colorScheme.secondary,
                                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                        unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                        cursorColor = MaterialTheme.colorScheme.secondary
                                    ),
                                    shape = RoundedCornerShape(10.dp),
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
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OutlinedTextField(
                                    value = passwordAgain.value,
                                    onValueChange = {
                                        passwordAgain.value = it.trim()
                                    },
                                    label = { Text(text = "Şifre Tekrar", color = MaterialTheme.colorScheme.secondary) },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Password, // Şifre klavyesi kullan
                                        imeAction = ImeAction.Done
                                    ),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedLabelColor = MaterialTheme.colorScheme.secondary,
                                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                        unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                        cursorColor = MaterialTheme.colorScheme.secondary
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    trailingIcon = {
                                        IconButton(
                                            onClick = {
                                                passwordVisibilityAgain.value = !passwordVisibilityAgain.value
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (passwordVisibilityAgain.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                                contentDescription = if (passwordVisibilityAgain.value) "Şifreyi Gizle" else "Şifreyi Göster",
                                                tint = MaterialTheme.colorScheme.secondary
                                            )
                                        }
                                    },
                                    visualTransformation = if (passwordVisibilityAgain.value) VisualTransformation.None else PasswordVisualTransformation()
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                OutlinedTextField(
                                    value = phoneNumber.value,
                                    onValueChange = {
                                        phoneNumber.value = it.trim()
                                    },
                                    label = {
                                        Text(
                                            text = "Telefon Numarası",
                                            color = MaterialTheme.colorScheme.secondary
                                        )
                                    },
                                    placeholder = { Text(text = "(5xx)-xxx-xxxx")},
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedLabelColor = MaterialTheme.colorScheme.secondary,
                                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                        unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                                        cursorColor = MaterialTheme.colorScheme.secondary
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            //Sözleşmeler
                            var isChecked by remember { mutableStateOf(false) }
                            var isCheckedSecond by remember { mutableStateOf(false) }
                            Row(modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Spacer(modifier = Modifier.weight(1f))
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(
                                            MaterialTheme.colorScheme.secondary,
                                            shape = RoundedCornerShape(5.dp)
                                        )
                                        .clickable { isChecked = !isChecked },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = { isChecked = it },
                                        modifier = Modifier
                                            .size(24.dp)
                                            .background(
                                                MaterialTheme.colorScheme.secondary,
                                                RoundedCornerShape(5.dp)
                                            )
                                            .padding(4.dp),
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = MaterialTheme.colorScheme.primary,
                                            uncheckedColor = Color.Transparent,
                                            checkmarkColor = MaterialTheme.colorScheme.secondary
                                        )
                                    )
                                }

                                Text(modifier = Modifier.clickable {
                                    navController.navigate("agreement_screen")
                                },
                                    text = "Kullanıcı ve Gizlilik Sözleşmesi",
                                    color = MaterialTheme.colorScheme.secondary,
                                    textDecoration = TextDecoration.Underline
                                )
                                Spacer(modifier = Modifier.weight(1f))

                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.weight(1f))
                                OutlinedButton(onClick = {
                                    if (userEmail.value.isNotEmpty() && password.value.isNotEmpty() && passwordAgain.value.isNotEmpty() && name.value.isNotEmpty()
                                        && surName.value.isNotEmpty() && phoneNumber.value.isNotEmpty() && isChecked) {
                                        if (userEmail.value.endsWith(".com")) {
                                            if (password.value == passwordAgain.value) {
                                                if (password.value.length >= 7 && passwordAgain.value.length >= 7) {
                                                    if (phoneNumber.value.length == 10) {
                                                        //Telefon Numarası Kontrolü
                                                        db.collection("users").whereEqualTo("phoneNumber",phoneNumber.value)
                                                            .get()
                                                            .addOnSuccessListener {documents ->
                                                                if (documents.isEmpty) {
                                                                    //Telelfon Numarası Boş Hesap Oluştumaya Devam
                                                                    auth.fetchSignInMethodsForEmail(userEmail.value)
                                                                        .addOnCompleteListener { it ->
                                                                            if (it.isSuccessful) {
                                                                                val result = it.result
                                                                                val signInMethods = result?.signInMethods ?: emptyList<String>()
                                                                                if (signInMethods.isEmpty()) {
                                                                                    //Hesap Oluşturulabilir
                                                                                    auth.createUserWithEmailAndPassword(userEmail.value,password.value)
                                                                                        .addOnCompleteListener{ create ->
                                                                                            if (create.isSuccessful) {
                                                                                                val user = auth.currentUser
                                                                                                user?.sendEmailVerification()?.addOnCompleteListener { send ->
                                                                                                    if (send.isSuccessful) {
                                                                                                        //Mail Gönderildi
                                                                                                        Toast.makeText(context,"${userEmail.value} adresine doğrulama maili gönderildi.",Toast.LENGTH_SHORT).show()
                                                                                                        //Mailin Doğrulandığını Kontol Et
                                                                                                        CoroutineScope(Dispatchers.IO).launch{
                                                                                                            var emailVerified = false
                                                                                                            while (!emailVerified) {
                                                                                                                user.reload().await()
                                                                                                                delay(500) // Belirli bir süre bekleyerek kontrol etmek için 0.5 saniyede bir döngüyü tekrarlıyoruz
                                                                                                                if (user.isEmailVerified) {
                                                                                                                    emailVerified = true
                                                                                                                    // E-posta doğrulandı, kullanıcıyı yönlendir
                                                                                                                    withContext(Dispatchers.Main) {
                                                                                                                        Toast.makeText(
                                                                                                                            context,
                                                                                                                          "Email doğrulandı oturum açabilirsiniz!",
                                                                                                                            Toast.LENGTH_SHORT
                                                                                                                        ).show()
                                                                                                                        //veritabanına kayıt kısmı
                                                                                                                        val userdata = UserDatabase.FirestoreDatabase()

                                                                                                                        userdata.addUserData("name",name.value)

                                                                                                                        userdata.addUserData("surname",surName.value)

                                                                                                                        userdata.addUserData("phoneNumber",phoneNumber.value)

                                                                                                                        userdata.addUserData("email", userEmail.value)

                                                                                                                    }
                                                                                                                }
                                                                                                                else {
                                                                                                                    // E-posta doğrulanmadı, hala bekleyin
                                                                                                                    continue
                                                                                                                }
                                                                                                            }
                                                                                                        }

                                                                                                    }else {
                                                                                                        //Doğrulama Gönderilemedi
                                                                                                        Toast.makeText(context,"Mail gönderilemedi.",Toast.LENGTH_SHORT).show()

                                                                                                    }
                                                                                                }
                                                                                            }else {
                                                                                                Toast.makeText(context,"Hesap oluşturma başarısız.",Toast.LENGTH_SHORT).show()
                                                                                            }
                                                                                        }
                                                                                }else {
                                                                                    Toast.makeText(context,"Bu email adresine bağlı bir hesap mevcut.",Toast.LENGTH_SHORT).show()
                                                                                }

                                                                            }else {
                                                                                Toast.makeText(context,"Hesap oluştuma başarısız.",Toast.LENGTH_SHORT).show()

                                                                            }

                                                                        }

                                                                }else {
                                                                    Toast.makeText(context,"Bu telefon numarası önceden alınmış.",Toast.LENGTH_SHORT).show()

                                                                }

                                                            }
                                                            .addOnFailureListener {
                                                                Toast.makeText(context,"Hesap oluşturulamadı.",Toast.LENGTH_SHORT).show()
                                                            }

                                                    }else {
                                                        Toast.makeText(context,"Telefon numarası 10 karakterden oluşmalıdır başına 0 girmeden tekrar deneyin.",Toast.LENGTH_LONG).show()
                                                    }
                                                }else {
                                                    Toast.makeText(context,"Şifre en az 7 karakterden oluşmalıdır.",Toast.LENGTH_SHORT).show()
                                                }
                                            }else {
                                                Toast.makeText(context,"Şifreler aynı değil.",Toast.LENGTH_SHORT).show()
                                            }
                                        }else {
                                         Toast.makeText(context,"Geçerli bir email adresi girin.",Toast.LENGTH_SHORT).show()
                                        }
                                    }else {
                                        Toast.makeText(context,"Gerekli alanların doldurulması zorunludur.",Toast.LENGTH_SHORT).show()
                                    }
                                }) {
                                    Text(text = "Kayıt Ol", color = MaterialTheme.colorScheme.secondary,
                                        fontSize = 20.sp,
                                        letterSpacing = 1.sp)
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
                                        text = "Mevcut Hesaba Giriş",
                                        color = MaterialTheme.colorScheme.secondary,
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier.clickable {
                                            navController.navigate("login_screen")
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