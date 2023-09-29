package com.yakasoftware.zerotech.views


import android.widget.Toast
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SheetBarLine
import com.yakasoftware.zerotech.Lines.SimpleLine
import com.yakasoftware.zerotech.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdressScreen(navController: NavHostController) {
    val context = LocalContext.current

    val adressTitle = remember {
        mutableStateOf("")
    }
    val city = remember {
        mutableStateOf("")
    }
    val docId = remember {
        mutableStateOf("")
    }
    val district = remember {
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
    val isClicked = remember {
        mutableStateOf(false)
    }
    val barVisible = remember {
        mutableStateOf(false)
    }

    val sidebarHeight by animateFloatAsState(
        targetValue = if (barVisible.value ) 0.6f else 0f,
        animationSpec = if (barVisible.value) {
            tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing // Bu yavaşça kapanmasını sağlar
            )
        } else {
            tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing // Yavaşça açılması için burada da kullanabilirsiniz
            )
        },
        label = "Yan menü animasyonu"
    )
    val coroutineScope = rememberCoroutineScope()

    data class AdressData(
        val city: String,
        val adressTitle: String,
        val direction: String,
        val district: String,
        val neighbourhood: String,
        val street: String,
        val docId: String
    )




    Surface(
        modifier = Modifier
            .fillMaxSize()
            .blur(radius = if (barVisible.value) 10.dp else 0.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
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

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = isClicked.value) {
                    coroutineScope.launch {
                        if (isClicked.value) {
                            barVisible.value = false
                            delay(timeMillis = 250) // Delay for 1000 milliseconds (1 second)
                        }
                        if (!barVisible.value) {
                            isClicked.value = false
                        }
                    }
                },
            color = MaterialTheme.colorScheme.primary
        ) {
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
                    Text(text = "Adres Defteri", color = MaterialTheme.colorScheme.secondary,
                        fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Icon(
                        painter = painterResource(R.drawable.adresslogo),
                        contentDescription = "Adres Defteri",
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
                Spacer(modifier = Modifier.padding(top = 18.dp))

                Column(modifier = Modifier.fillMaxWidth()) {

                    val adressList = remember {
                        mutableStateListOf<AdressData>()
                    }
                    db.collection("adress").document(email!!).collection(email).get().addOnSuccessListener { documents ->
                        adressList.clear()
                        for (document in documents){
                            val adressData: Map<String,Any> = document.data
                            city.value = adressData["city"].toString()
                            adressTitle.value = adressData["adresstitle"].toString()
                            direction.value = adressData["direction"].toString()
                            district.value = adressData["district"].toString()
                            neighbourhood.value = adressData["neighbourhood"].toString()
                            street.value = adressData["street"].toString()
                            docId.value = adressData["documentId"].toString()
                            adressList.add(AdressData(city.value,adressTitle.value,direction.value,district.value,neighbourhood.value,street.value,docId.value))
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 18.dp))
                    LazyColumn() {
                        val adressTitleFontSize = 20.dp
                        val directionFontSize = 16.dp
                        items(adressList.size) {index ->
                            val adress = adressList[index]
                            //gerekli veriler çekildi tarafımca test edildi istediğiniz bilgiyi çağırmak için adress.city şeklinde çağırmanız yeterlidir M.K.
                           Box(modifier = Modifier.fillMaxWidth(),
                               contentAlignment = Alignment.Center) {
                               Box(modifier = Modifier
                                   .clip(RoundedCornerShape(20.dp))
                                   .fillMaxWidth(0.6f)
                                   .height(120.dp)
                                   .border(
                                       width = 2.dp,
                                       color = MaterialTheme.colorScheme.secondary,
                                       shape = RoundedCornerShape(20.dp)
                                   )
                                   .background(MaterialTheme.colorScheme.onTertiary)) {
                                   Column(modifier = Modifier.fillMaxSize(),
                                       horizontalAlignment = Alignment.CenterHorizontally) {
                                       Spacer(modifier = Modifier.padding(top = 12.dp))
                                       Row(modifier = Modifier.fillMaxWidth(),
                                           verticalAlignment = Alignment.CenterVertically,
                                           horizontalArrangement = Arrangement.Center) {
                                           Icon(painter = painterResource(id = R.drawable.konumtr), contentDescription ="Konum",
                                               tint = MaterialTheme.colorScheme.secondary,
                                               modifier = Modifier.size(28.dp))
                                           Spacer(modifier = Modifier.padding(2.dp))
                                           Text(text = adress.adressTitle, color = MaterialTheme.colorScheme.secondary,
                                               fontWeight = FontWeight.Bold,
                                               fontSize = with(LocalDensity.current){adressTitleFontSize.toSp()}
                                           )
                                           Spacer(modifier = Modifier.weight(0.9f))
                                           Icon(imageVector = Icons.Default.DriveFileRenameOutline, contentDescription ="Adres Güncelleme",
                                               tint = MaterialTheme.colorScheme.secondary,
                                               modifier = Modifier
                                                   .size(26.dp)
                                                   .clickable {
                                                       navController.navigate("adress_detail_screen/${adress.docId}")
                                                   })
                                       }
                                       Spacer(modifier = Modifier.padding(top = 4.dp))
                                       Row(
                                           modifier = Modifier.fillMaxWidth(),
                                           horizontalArrangement = Arrangement.Start
                                       ) {
                                           Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                                               SimpleLine()
                                           }
                                       }
                                       Spacer(modifier = Modifier.padding(12.dp))
                                       Row(
                                           modifier = Modifier.fillMaxWidth(0.8f), // Metnin maksimum genişliği
                                           horizontalArrangement = Arrangement.spacedBy(2.dp)
                                       ) {
                                           Text(
                                               text = adress.direction,
                                               color = MaterialTheme.colorScheme.tertiary,
                                               fontSize = with(LocalDensity.current) { directionFontSize.toSp() },
                                               maxLines = 1,
                                               overflow = TextOverflow.Ellipsis
                                           )
                                       }
                                   }
                               }
                           }
                            //Buraya güncelleme için gereken bar eklenecek veya yeni sayfaya atılacak
                            Spacer(modifier = Modifier.height(18.dp))
                        }

                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    LargeFloatingActionButton(
                        onClick = {
                            isClicked.value = true
                            barVisible.value = true
                        },
                        modifier = Modifier.clickable(enabled = !isClicked.value) {},
                    ) {
                        Icon(Icons.Filled.AddCircleOutline, "Localized description")
                    }
                }


            }

        }

    }

    if (isClicked.value) {

        Column(
            Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.750f)
                ) {
                    SheetBarLine()
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(sidebarHeight)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)
                    )
                    .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                    .border(
                        BorderStroke(3.dp, MaterialTheme.colorScheme.onSecondary),
                        RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)
                    ),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.End ) {

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                MaterialTheme.colorScheme.onSecondary,
                                CircleShape
                            )
                            .padding(4.dp),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Bar kapama",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .clickable {
                                    coroutineScope.launch {
                                        if (isClicked.value) {
                                            barVisible.value = false
                                            delay(timeMillis = 250) // Delay for 1000 milliseconds (1 second)
                                        }
                                        if (!barVisible.value) {
                                            isClicked.value = false
                                        }
                                    }
                                }

                        )
                    } }

                LazyColumn(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {

                        Spacer(modifier = Modifier.padding(top = 25.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.DriveFileRenameOutline, // Simgenizin adını buraya ekleyin
                                contentDescription = "İsim Simgesi",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(
                                        MaterialTheme.colorScheme.onSecondary,
                                        RoundedCornerShape(5.dp)
                                    )
                            )
                            Spacer(modifier = Modifier.padding(start = 5.dp))
                            Text(
                                text = "Yeni Adres Ekle",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 45.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        shape = RoundedCornerShape(16.dp)
                                    )

                            ) {
                                LazyRow {
                                    item {
                                        OutlinedTextField(
                                            value = adressTitle.value,
                                            onValueChange = {
                                                adressTitle.value = it
                                            },
                                            label = {
                                                Text(
                                                    text = "Adres Başlığı: (örn: ev, yurt..)",
                                                    color = Color.LightGray
                                                )
                                            },
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                focusedLabelColor = Color.Transparent,
                                                focusedBorderColor = Color.Transparent,
                                                unfocusedLabelColor = Color.LightGray,
                                                unfocusedBorderColor = Color.Transparent,
                                                cursorColor = Color.White
                                            ),
                                            shape = RoundedCornerShape(16.dp),

                                            )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 15.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                LazyRow {
                                    item {
                                        OutlinedTextField(
                                            value = city.value,
                                            onValueChange = {
                                                city.value = it
                                            },
                                            label = {
                                                Text(
                                                    text = "İl",
                                                    color = Color.LightGray
                                                )
                                            },
                                            modifier = Modifier.fillMaxWidth(0.5f),
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                focusedLabelColor = Color.Transparent,
                                                focusedBorderColor = Color.Transparent,
                                                unfocusedLabelColor = Color.LightGray,
                                                unfocusedBorderColor = Color.Transparent,
                                                cursorColor = Color.White
                                            ),
                                            shape = RoundedCornerShape(16.dp),

                                            )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                LazyRow {
                                    item {
                                        OutlinedTextField(
                                            value = district.value,
                                            onValueChange = {
                                                district.value = it
                                            },
                                            label = {
                                                Text(
                                                    text = "İlçe",
                                                    color = Color.LightGray
                                                )
                                            },
                                            modifier = Modifier.fillMaxWidth(0.5f),
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                focusedLabelColor = Color.Transparent,
                                                focusedBorderColor = Color.Transparent,
                                                unfocusedLabelColor = Color.LightGray,
                                                unfocusedBorderColor = Color.Transparent,
                                                cursorColor = Color.White
                                            ),
                                            shape = RoundedCornerShape(16.dp),

                                            )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 15.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                LazyRow {
                                    item {
                                        OutlinedTextField(
                                            value = neighbourhood.value,
                                            onValueChange = {
                                                neighbourhood.value = it
                                            },
                                            label = {
                                                Text(
                                                    text = "Mahalle",
                                                    color = Color.LightGray
                                                )
                                            },
                                            modifier = Modifier.fillMaxWidth(0.5f),
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                focusedLabelColor = Color.Transparent,
                                                focusedBorderColor = Color.Transparent,
                                                unfocusedLabelColor = Color.LightGray,
                                                unfocusedBorderColor = Color.Transparent,
                                                cursorColor = Color.White
                                            ),
                                            shape = RoundedCornerShape(16.dp),

                                            )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                LazyRow {
                                    item {
                                        OutlinedTextField(
                                            value = street.value,
                                            onValueChange = {
                                                street.value = it
                                            },
                                            label = {
                                                Text(
                                                    text = "Sokak",
                                                    color = Color.LightGray
                                                )
                                            },
                                            modifier = Modifier.fillMaxWidth(0.5f),
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                focusedLabelColor = Color.Transparent,
                                                focusedBorderColor = Color.Transparent,
                                                unfocusedLabelColor = Color.LightGray,
                                                unfocusedBorderColor = Color.Transparent,
                                                cursorColor = Color.White
                                            ),
                                            shape = RoundedCornerShape(16.dp),

                                            )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 15.dp))
                        Spacer(modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.padding(top = 15.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.3f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                LazyRow {
                                    item {
                                        OutlinedTextField(
                                            direction.value,
                                            onValueChange = {
                                                direction.value = it
                                            },
                                            label = {
                                                Text(
                                                    text = "Adres Tarifi",
                                                    color = Color.LightGray
                                                )
                                            },
                                            modifier = Modifier.fillMaxWidth(0.5f),
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                focusedLabelColor = Color.Transparent,
                                                focusedBorderColor = Color.Transparent,
                                                unfocusedLabelColor = Color.LightGray,
                                                unfocusedBorderColor = Color.Transparent,
                                                cursorColor = Color.White
                                            ),
                                            shape = RoundedCornerShape(16.dp),

                                            )
                                    }
                                }
                            }
                        }

                    }
                    item {
                        Spacer(modifier = Modifier.padding(top = 30.dp))
                        Row(
                            Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val fontSizeButton = 24.dp
                            OutlinedButton(
                                onClick = {
                                    val adressMap = hashMapOf(
                                        "adresstitle" to adressTitle.value,
                                        "city" to city.value,
                                        "district" to district.value,
                                        "neighbourhood" to neighbourhood.value,
                                        "street" to street.value,
                                        "direction" to direction.value
                                    )
                                    val adressDb = Firebase.firestore
                                    val currentUserEmailAdress = Firebase.auth.currentUser?.email
                                    if (currentUserEmailAdress != null) {
                                        if (adressTitle.value.isNotEmpty() && city.value.isNotEmpty()
                                            && district.value.isNotEmpty() && neighbourhood.value.isNotEmpty() &&
                                            street.value.isNotEmpty() && direction.value.isNotEmpty()) {
                                            val adressCollection = adressDb.collection("adress").document(currentUserEmailAdress)
                                                .collection(currentUserEmailAdress)
                                            adressCollection.add(adressMap)
                                                .addOnSuccessListener { documentReference ->
                                                    val newAdressDocumentId = documentReference.id
                                                    val updatedData = mapOf("documentId" to newAdressDocumentId)
                                                    documentReference.update(updatedData)
                                                        .addOnSuccessListener {
                                                            Toast.makeText(context,"Adres başarıyla eklendi.",Toast.LENGTH_SHORT).show()
                                                            adressTitle.value = ""
                                                            city.value = ""
                                                            district.value = ""
                                                            neighbourhood.value = ""
                                                            street.value = ""
                                                            direction.value = ""
                                                            coroutineScope.launch {
                                                                if (isClicked.value) {
                                                                    barVisible.value = false
                                                                    delay(timeMillis = 250) // Delay for 1000 milliseconds (1 second)
                                                                }
                                                                if (!barVisible.value) {
                                                                    isClicked.value = false
                                                                }
                                                            }
                                                        }
                                                        .addOnFailureListener { e ->
                                                            Toast.makeText(context,"Adres eklenirken hata oluştu..",Toast.LENGTH_SHORT).show()
                                                        }
                                                }
                                                .addOnFailureListener { e ->
                                                    Toast.makeText(context,"Adres eklenirken hata oluştu..",Toast.LENGTH_SHORT).show()
                                                }

                                        }else {
                                            Toast.makeText(context,"Gerekli alanları doldurunuz.",Toast.LENGTH_SHORT).show()
                                        }

                                    }

                                },
                                shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
                            ) {
                                Text(text = "Kaydet", color = MaterialTheme.colorScheme.secondary, letterSpacing = 1.sp,
                                    fontWeight = FontWeight.Bold,  fontSize = with(LocalDensity.current) { fontSizeButton.toSp()})
                            }
                        }
                        Spacer(modifier = Modifier.padding(bottom = 18.dp))

                    }
                }
            }
        }
    }
}