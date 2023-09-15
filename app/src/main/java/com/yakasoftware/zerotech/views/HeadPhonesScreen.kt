package com.yakasoftware.zerotech.views

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Speaker
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine
import com.yakasoftware.zerotech.Lines.SimpleLineWhite
import com.yakasoftware.zerotech.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadPhonesScreen(navController: NavHostController) {
    val isMenuVisible = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val barVisible = remember {
        mutableStateOf(false) //İKİ KERE TIKLAMA SORUNU SONRADAN ÇÖZÜLECEK A.Ç.
    }

    val searchBar = remember {
        mutableStateOf("")
    }

    val sidebarWidth by animateDpAsState(
        targetValue = if (barVisible.value) (LocalConfiguration.current.screenWidthDp * 0.50f).dp else 0.dp,
        animationSpec = if (barVisible.value) {
            tween(
                durationMillis = 500,
                easing = LinearOutSlowInEasing // Bu yavaşça kapanmasını sağlar
            )
        } else {
            tween(
                durationMillis = 500,
                easing = LinearOutSlowInEasing // Yavaşça açılması için burada da kullanabilirsiniz
            )
        },
        label = "Yan menü animasyonu"
    )

    val auth = Firebase.auth
    val currentUser = auth.currentUser
    val db = Firebase.firestore
    val name = remember {
        mutableStateOf("")
    }
    val email = currentUser?.email

    if (auth.currentUser != null) {
        db.collection("users").document(email!!)
            .get()
            .addOnSuccessListener {
                val data = it.data
                name.value = data?.get("name") as String

            }
    }


    val firstLetter = name.value.firstOrNull()?.uppercaseChar() ?: ' '
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = if (barVisible.value) 5.dp else 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    if (!isMenuVisible.value) {
                        isMenuVisible.value = true
                        barVisible.value = true
                    }
                }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menü Açma",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
                Row(modifier = Modifier.clickable {
                    navController.navigate("main_screen") {
                        popUpTo("profile_screen") {
                            inclusive = true
                        }
                    }
                }) {
                    Image(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.logonoback),
                        contentDescription = "Logo",
                        modifier = Modifier.size(90.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        if (!isMenuVisible.value) {
                            if (currentUser == null) {
                                val popBackStackDestinationId =
                                    navController.previousBackStackEntry?.destination?.route
                                navController.navigate("login_screen") {
                                    // "login_screen" sayfasına geçerken geriye gitme işlemini yapılandırın
                                    if (popBackStackDestinationId == "main_screen") {
                                        // Eğer önceki sayfa "main_screen" ise geriye gitme işlemini devre dışı bırak
                                        popUpTo("login_screen") {
                                            saveState = false
                                            inclusive = false
                                        }
                                    }
                                }
                            } else {
                                navController.navigate("profile_screen")
                            }
                        }


                    }, enabled = !isMenuVisible.value, colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    //Profil ve Hesap Açma
                    val fontSizeInDp = 26.dp
                    if (currentUser == null) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profil ve Hesap",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = firstLetter.toString(),
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = with(LocalDensity.current) { fontSizeInDp.toSp() }
                            )

                        }
                    }
                }
                //Sepetim
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Sepetim",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            if (!isMenuVisible.value) {
                                navController.navigate("basket_screen")
                            }
                            //Sepet işlemleri
                        }
                )
                Spacer(modifier = Modifier.padding(end = 4.dp))
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(value = searchBar.value,
                    onValueChange = {
                        searchBar.value = it
                    },
                    modifier = Modifier.fillMaxWidth(0.9f),
                    label = {
                        Text(
                            text = "Ne Aramıştınız?",
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
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search // "Search" işlemini yakala
                    ),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Arama",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    })
            }
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Kulaklıklar",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(12.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.fillMaxWidth(0.650f)) {
                    Spacer(modifier = Modifier.weight(1f))
                    SimpleLine()
                    Spacer(modifier = Modifier.weight(1f))
                }
            }



            Spacer(modifier = Modifier.padding(20.dp))
            RectanglesWithLinesHeadPhones()

        }
    }
    val screenHalf: Dp = (LocalConfiguration.current.screenWidthDp * 1.5f).dp

    if (isMenuVisible.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        if (offset.x >= 0 && offset.x <= screenHalf.value) {
                            // Box içine tıklandığında herhangi bir işlem yapma
                        } else {
                            // Box dışına tıklanırsa menüyü kapat
                            coroutineScope.launch {
                                if (isMenuVisible.value) {
                                    barVisible.value = false
                                    delay(250) // Delay for 1000 milliseconds (1 second)
                                }
                                if (!barVisible.value) {
                                    isMenuVisible.value = false
                                }
                            }
                        }
                    }
                }
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(0, 10, 10, 0))
                    .fillMaxHeight()
                    .width(sidebarWidth)
                    .background(MaterialTheme.colorScheme.primary)
                    .zIndex(if (isMenuVisible.value) 1f else 0f)
                    .animateContentSize()
            ) {
                //SideBar İçeriği
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)){
                    val sideBarFontSize = 20.dp
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = false
                            ) {

                            },

                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.padding(start = 40.dp))
                        Image(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.logonoback),
                            contentDescription = "Logo",
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(onClick = {
                            coroutineScope.launch {
                                if (isMenuVisible.value) {
                                    barVisible.value = false
                                    delay(250) // Delay for 1000 milliseconds (1 second)
                                }
                                if (!barVisible.value) {
                                    isMenuVisible.value = false
                                }
                            }
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary
                        )) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Menü Kapama",
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }

                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Text(text = "Ürünler", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.padding(4.dp))
                        Icon(
                            imageVector = Icons.Default.Shop,
                            contentDescription = "Ürünler",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(26.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                            SimpleLineWhite()
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 18.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }
                    Spacer(modifier = Modifier.padding(bottom = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Akıllı Saat", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(R.drawable.saatlogotr), // Simgenizin adını buraya ekleyin
                            contentDescription = "Akıllı Saat",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 10.dp))
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Kulaklık", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Headphones,
                            contentDescription = "Kulaklık",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(26.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 10.dp))
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Kordon", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Watch,
                            contentDescription = "Kordon",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(26.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 10.dp))
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth()
                        .clickable {
                            navController.navigate("speaker_screen"){
                                popUpTo("profile_screen"){
                                    inclusive = true
                                }
                            }
                        },
                        horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Hoparlör", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Speaker,
                            contentDescription = "Hoparlör",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(26.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 10.dp))
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Aksesuar", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(R.drawable.accosoriestr), // Simgenizin adını buraya ekleyin
                            contentDescription = "Aksesuar",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 10.dp))
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Kampanyalar", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Campaign,
                            contentDescription = "Kampanylar",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(26.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 10.dp))
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }
                    Spacer(modifier = Modifier.padding(top = 40.dp))

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Text(text = "İletişim", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.padding(4.dp))
                        Icon(
                            imageVector = Icons.Default.ContactPhone,
                            contentDescription = "iletişim",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(26.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                            SimpleLineWhite()
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 18.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isMenuVisible.value && barVisible.value) {
                                val phoneNumber = "+905010996541"
                                val message = "Merhaba uygulama üzerinden ulaşıyorum."
                                val url = "https://wa.me/$phoneNumber?text=${
                                    URLEncoder.encode(
                                        message,
                                        "UTF-8"
                                    )
                                }"
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(url)
                                intent.setPackage("com.whatsapp")
                                context.startActivity(
                                    // on below line we are opening the intent.
                                    Intent(
                                        // on below line we are calling
                                        // uri to parse the data
                                        Intent.ACTION_VIEW,
                                        Uri.parse(
                                            // on below line we are passing uri,
                                            // message and whats app phone number.
                                            java.lang.String.format(
                                                "https://api.whatsapp.com/send?phone=%s&text=%s",
                                                phoneNumber,
                                                message
                                            )
                                        )
                                    )
                                )
                            }

                        },
                        horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Canlı Destek", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Whatsapp,
                            contentDescription = "Whatsapp",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(26.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 10.dp))
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isMenuVisible.value && barVisible.value) {
                                val instagramUri =
                                    Uri.parse("https://instagram.com/__zerotech__?igshid=MzRlODBiNWFlZA==")
                                val intent = Intent(Intent.ACTION_VIEW, instagramUri)

                                intent.setPackage("com.instagram.android")

                                try {
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    context.startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("https://instagram.com/__zerotech__?igshid=MzRlODBiNWFlZA==")
                                        )
                                    )
                                }
                            }
                        },
                        horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Tüm Ürünler", color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(R.drawable.instagramlogo), // Simgenizin adını buraya ekleyin
                            contentDescription = "instagram",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 10.dp))
                    }
                    Spacer(modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SimpleLineWhite()
                    }

                }
            }
        }
    }
}

@Composable
fun RectanglesWithLinesHeadPhones() {
    val photoSpeaker1 = remember {
        mutableStateOf("")
    }
    val oldPrice = remember {
        mutableStateOf("")
    }
    val price = remember {
        mutableStateOf("")
    }
    val discount = remember {
        mutableStateOf("")
    }
    val title = remember {
        mutableStateOf("")
    }
    val headPhoneList = remember { mutableStateListOf<ProductsData>() }
    val isSpeakerLoading = remember { mutableStateOf(true) }
    val headphonesDb = Firebase.firestore
    val fontSize = 12.dp
    val fontSizePrice = 16.dp

    LaunchedEffect(Unit) {
        isSpeakerLoading.value = true
        headphonesDb.collection("products").document("headphones")
            .collection("AirPod")
            .get()
            .addOnSuccessListener { documents ->
                headPhoneList.clear()
                for (document in documents) {
                    val speakerDataBigVal: Map<String, Any> = document.data
                    // Firestore'dan gelen 'usercaption' field değerini 'userCaption' değişkenine atıyoruz
                    photoSpeaker1.value = speakerDataBigVal["photo1"].toString()
                    oldPrice.value = speakerDataBigVal["oldPrice"].toString()
                    price.value = speakerDataBigVal["price"].toString()
                    title.value = speakerDataBigVal["title"].toString()
                    discount.value = speakerDataBigVal["discount"].toString()
                    headPhoneList.add(ProductsData(photoSpeaker1.value,oldPrice.value,price.value,title.value,discount.value))

                }
                isSpeakerLoading.value = false
            }.addOnFailureListener {
                println(it)
            }
    }

    if (isSpeakerLoading.value){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
        }
    }
    else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(headPhoneList.size/ 2) { rowIndex ->
                val firstSpeakerIndex = rowIndex * 2
                val secondSpeakerIndex = rowIndex * 2 + 1
                val firstSpeakerData = headPhoneList[firstSpeakerIndex]
                val secondSpeakerData = headPhoneList.getOrNull(secondSpeakerIndex)
                val painter = rememberAsyncImagePainter(model = firstSpeakerData.photo1)
                Box(modifier = Modifier
                    .size(390.dp)
                    .background(MaterialTheme.colorScheme.primary)) {

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // İlk dikdörtgeni üç parçaya böl
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(
                                    MaterialTheme.colorScheme.secondary,
                                    RoundedCornerShape(14.dp)
                                )
                                .padding(4.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            //Resimler + Ürün ismi buraya müminim
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(5f)
                                    .background(
                                        MaterialTheme.colorScheme.onPrimary,
                                        RoundedCornerShape(10.dp)
                                    )
                            )
                            {
                                Image(painter = painter, contentDescription = "Kulaklık", contentScale = ContentScale.Crop, modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(10.dp)),)
                                Icon(
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = "Sepetim",
                                    tint = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(alignment = Alignment.TopEnd)
                                )

                                Column (modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.Transparent,// Başlangıç rengi
                                                MaterialTheme.colorScheme.onPrimary    // Bitiş rengi
                                            ),
                                            startY = 0f,
                                            endY = 800f // Yüksekliği ayarlayın
                                        )
                                    ), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally){
                                    Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                                        Text(text = firstSpeakerData.title, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold,
                                            fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                            textAlign = TextAlign.Center,)
                                    }

                                }
                            }
                            //Sepete ekleme - ürün fiyat
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.7f)
                                    .background(
                                        MaterialTheme.colorScheme.onPrimary,
                                        RoundedCornerShape(10.dp)
                                    )

                            ) {
                                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
                                    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.ShoppingCart,
                                                contentDescription = "Sepetim",
                                                tint = MaterialTheme.colorScheme.onSecondary,
                                                modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Spacer(modifier = Modifier.weight(1f))
                                            Column {
                                                Text(text = firstSpeakerData.oldPrice, color = MaterialTheme.colorScheme.secondary,
                                                    fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                    textAlign = TextAlign.Center,
                                                    textDecoration = TextDecoration.LineThrough)
                                                Spacer(modifier = Modifier.padding(top = 2.dp))
                                                Text(text = firstSpeakerData.price, color = MaterialTheme.colorScheme.secondary,
                                                    fontSize = with(LocalDensity.current) { fontSizePrice.toSp() }, fontWeight = FontWeight.Bold,
                                                    textAlign = TextAlign.Center)
                                            }
                                            Spacer(modifier = Modifier.weight(1f))

                                        }

                                    }}

                            }

                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        if (secondSpeakerData != null) {
                            val painter2 = rememberAsyncImagePainter(model = secondSpeakerData.photo1)
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .background(
                                        MaterialTheme.colorScheme.secondary,
                                        RoundedCornerShape(14.dp)
                                    )
                                    .padding(4.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {

                                //Resimler + Ürün ismi buraya müminim
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(5f)
                                        .background(
                                            MaterialTheme.colorScheme.onPrimary,
                                            RoundedCornerShape(10.dp)
                                        )
                                )
                                {
                                    Image(painter = painter2, contentDescription = "Hoparlör", contentScale = ContentScale.Crop, modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(10.dp)))
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Sepetim",
                                        tint = MaterialTheme.colorScheme.onSecondary,
                                        modifier = Modifier
                                            .size(30.dp)
                                            .align(alignment = Alignment.TopEnd)
                                    )

                                    Column (modifier = Modifier
                                        .fillMaxHeight()
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    Color.Transparent,// Başlangıç rengi
                                                    MaterialTheme.colorScheme.onPrimary    // Bitiş rengi
                                                ),
                                                startY = 0f,
                                                endY = 800f // Yüksekliği ayarlayın
                                            )
                                        ), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally){
                                        Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                                            Text(text = secondSpeakerData.title, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold,
                                                fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                textAlign = TextAlign.Center,)
                                        }

                                    }
                                }
                                //Sepete ekleme - ürün fiyat
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.7f)
                                        .background(
                                            MaterialTheme.colorScheme.onPrimary,
                                            RoundedCornerShape(10.dp)
                                        )

                                ) {
                                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
                                        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
                                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Default.ShoppingCart,
                                                    contentDescription = "Sepetim",
                                                    tint = MaterialTheme.colorScheme.onSecondary,
                                                    modifier = Modifier
                                                        .size(35.dp)
                                                )
                                                Spacer(modifier = Modifier.weight(1f))
                                                Column {
                                                    Text(text = secondSpeakerData.oldPrice, color = MaterialTheme.colorScheme.secondary,
                                                        fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                        textAlign = TextAlign.Center,
                                                        textDecoration = TextDecoration.LineThrough)
                                                    Spacer(modifier = Modifier.padding(top = 2.dp))
                                                    Text(text = secondSpeakerData.price, color = MaterialTheme.colorScheme.secondary,
                                                        fontSize = with(LocalDensity.current) { fontSizePrice.toSp() }, fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center)
                                                }
                                                Spacer(modifier = Modifier.weight(1f))

                                            }

                                        }}
                                }
                            }
                        }

                    }

                }

            }
        }
    }

}