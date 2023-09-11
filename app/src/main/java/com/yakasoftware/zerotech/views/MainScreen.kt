package com.yakasoftware.zerotech.views

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Speaker
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material.icons.filled.WaterfallChart
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLineWhite
import com.yakasoftware.zerotech.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val isMenuVisible = remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val barVisible = remember {
        mutableStateOf(false) //İKİ KERE TIKLAMA SORUNU SONRADAN ÇÖZÜLECEK A.Ç.
    }
    val sidebarWidth by animateDpAsState(
        targetValue = if (barVisible.value) (LocalConfiguration.current.screenWidthDp * 0.50f).dp else 0.dp,
        animationSpec =  if (barVisible.value) {
            tween(
                durationMillis = 200,
                easing = LinearOutSlowInEasing // Bu yavaşça kapanmasını sağlar
            )
        } else {
            tween(
                durationMillis = 200,
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
                name.value = data?.get("name") as String ?: " "

            }
    }
    val pagerLoading = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        delay(750)
        pagerLoading.value = false
    }
    val airMax = remember {
        mutableStateOf("")
    }
    val offers = remember {
        mutableStateOf("")
    }
    val bluetooth = remember {
        mutableStateOf("")
    }
    val watchs = remember {
        mutableStateOf("")
    }

    db.collection("products").document("catalog")
        .get()
        .addOnSuccessListener {
            val data = it.data
            airMax.value = data?.get("airmax") as String
            offers.value = data["offers"] as String
            bluetooth.value = data["bluetooth"] as String
            watchs.value = data["watchs"] as String



        }
        .addOnFailureListener {
            println(it)
        }

    val airMaxPainter = rememberAsyncImagePainter(model = airMax.value)
    val offersPainter = rememberAsyncImagePainter(model = offers.value)
    val bluetoothPainter = rememberAsyncImagePainter(model = bluetooth.value)
    val watchsPainter = rememberAsyncImagePainter(model = watchs.value)


    val firstLetter = name.value.firstOrNull()?.uppercaseChar() ?: ' '
    Surface(modifier = Modifier.fillMaxSize()
        , color = MaterialTheme.colorScheme.primary) {
        Column(modifier = Modifier
            .fillMaxSize()
            .blur(radius = if (barVisible.value) 5.dp else 0.dp)) {
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
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.logonoback),
                    contentDescription = "Logo",
                    modifier = Modifier.size(90.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {
                    if (!isMenuVisible.value) {
                        if (currentUser == null) {
                            val popBackStackDestinationId = navController.previousBackStackEntry?.destination?.route
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
                        }else {
                            navController.navigate("profile_screen")
                        }
                    }


                }, enabled = !isMenuVisible.value, colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = MaterialTheme.colorScheme.primary
                )) {
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
                    }else {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = firstLetter.toString(), color = MaterialTheme.colorScheme.secondary,
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
            val pagerState = rememberPagerState(pageCount = { 4 })
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(30.dp))
            ) {

                if (pagerLoading.value) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        LinearProgressIndicator(color = MaterialTheme.colorScheme.secondary)
                    }
                }else {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    ) { page ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    when (page) {
                                        0 -> MaterialTheme.colorScheme.primary
                                        1 -> MaterialTheme.colorScheme.primary
                                        2 -> MaterialTheme.colorScheme.primary
                                        3 -> MaterialTheme.colorScheme.primary
                                        else -> MaterialTheme.colorScheme.primary
                                    }
                                )
                        ) {
                            // Her sayfanın içeriği burada olacak
                            when (page) {
                                0 -> {
                                    // İlk sayfa içeriği
                                    Box(modifier = Modifier.fillMaxWidth()) {
                                        Image(
                                            painter = offersPainter,
                                            contentDescription = "Kampanya",
                                            Modifier.border(
                                                width = 2.dp, color = MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(30.dp)
                                            )
                                        )
                                    }

                                }
                                1 -> {
                                    // İkinci sayfa içeriği
                                    Box(modifier = Modifier.fillMaxWidth()) {
                                        Image(
                                            painter = airMaxPainter,
                                            contentDescription = "Airmax",
                                            Modifier.border(
                                                width = 2.dp, color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(30.dp)
                                            )
                                        )
                                    }
                                }
                                2 -> {
                                    // Üçüncü sayfa içeriği
                                    Box(modifier = Modifier.fillMaxWidth()) {
                                        Image(
                                            painter = bluetoothPainter,
                                            contentDescription = "Bluetooth Hoparlör",
                                            Modifier.border(
                                                width = 2.dp, color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(30.dp)
                                            )
                                        )
                                    }
                                }
                                3 -> {
                                    // Dördüncü sayfa içeriği
                                    Box(modifier = Modifier.fillMaxWidth()) {
                                        Image(
                                            painter = watchsPainter,
                                            contentDescription = "Saat",
                                            Modifier.border(
                                                width = 2.dp,
                                                color = MaterialTheme.colorScheme.secondary,
                                                shape = RoundedCornerShape(30.dp)
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                    LaunchedEffect(Unit) {
                        while (true) {
                            delay(5000L)
                            coroutineScope.launch(Dispatchers.Main) {
                                pagerState.animateScrollToPage((pagerState.currentPage + 1) % pagerState.pageCount)
                            }
                        }
                    }
                }

            }




        }
        //SideBar


        if (isMenuVisible.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                        .background(Color(31, 31, 31, 255))) {
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
                                containerColor = Color(31,31,31,255)
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
                        Row(modifier = Modifier.fillMaxWidth(),
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
                        Row(modifier = Modifier.fillMaxWidth(),
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
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = "Canlı Destek", color = MaterialTheme.colorScheme.secondary,
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
}