package com.yakasoftware.zerotech.views


import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SpeakerDetailScreen(navController: NavHostController, productTitle: String) {
    val db = Firebase.firestore
    val proCollection = db.collection("products").document("speakers")
    val docRef = proCollection.collection("Aggiy AG-S21 Bluetooth Hoparlör")
    val detail1 = remember {
        mutableStateOf("")
    }
    val detail2 = remember {
        mutableStateOf("")
    }
    val detail3 = remember {
        mutableStateOf("")
    }
    val detail4 = remember {
        mutableStateOf("")
    }
    val detail5 = remember {
        mutableStateOf("")
    } 
    val detail6 = remember {
        mutableStateOf("")
    } 
    val detail7 = remember {
        mutableStateOf("")
    } 
    val detail8 = remember {
        mutableStateOf("")
    } 
    val detail9 = remember {
        mutableStateOf("")
    } 
    val detail10 = remember {
        mutableStateOf("")
    } 
    val detail11 = remember {
        mutableStateOf("")
    }
    val detail12 = remember {
        mutableStateOf("")
    }
    val detail13 = remember {
        mutableStateOf("")
    }
    val detail14 = remember {
        mutableStateOf("")
    }
    val detail15 = remember {
        mutableStateOf("")
    }

    val photo1 = remember { mutableStateOf("") }
    val photo2 = remember { mutableStateOf("") }
    val photo3 = remember { mutableStateOf("") }
    val photo4 = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val oldPrice = remember { mutableStateOf("") }
    val discount = remember { mutableStateOf("") }
    val pagerLoading = remember {
        mutableStateOf(true)
    }

    val coroutineScope = rememberCoroutineScope()

    val photoUrls = mutableListOf<String>()

    Surface(Modifier.fillMaxSize()) {
        println(productTitle)
        LaunchedEffect(Unit) {
            pagerLoading.value = true
            docRef.whereEqualTo("title", productTitle)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        photo1.value = document.getString("photo1")!!
                        photo2.value = document.getString("photo2")!!
                        photo3.value = document.getString("photo3")!!
                        photo4.value = document.getString("photo4")!!
                        val detail1Value = document.getString("detail1")
                        if (detail1Value != null && detail1Value != ""){
                            detail1.value = detail1Value
                        }
                        val detail2Value = document.getString("detail2")
                        if (detail2Value != null && detail2Value != ""){
                            detail2.value = detail2Value
                        }
                        val detail3Value = document.getString("detail3")
                        if (detail3Value != null && detail3Value != ""){
                            detail3.value = detail3Value
                        }
                        val detail4Value = document.getString("detail4")
                        if (detail4Value != null && detail4Value != ""){
                            detail4.value = detail4Value
                        }
                        val detail5Value = document.getString("detail5")
                        if (detail5Value != null && detail5Value != ""){
                            detail5.value = detail5Value
                        }
                        val detail6Value = document.getString("detail6")
                        if (detail6Value != null && detail6Value != ""){
                            detail6.value = detail6Value
                        }
                        val detail7Value = document.getString("detail7")
                        if (detail7Value != null && detail7Value != ""){
                            detail7.value = detail7Value
                        }
                        val detail8Value = document.getString("detail8")
                        if (detail8Value != null && detail8Value != ""){
                            detail8.value = detail8Value
                        }
                        val detail9Value = document.getString("detail9")
                        if (detail9Value != null && detail9Value != ""){
                            detail9.value = detail9Value
                        }
                        val detail10Value = document.getString("detail10")
                        if (detail10Value != null && detail10Value != ""){
                            detail10.value = detail10Value
                        }
                        val detail11Value = document.getString("detail11")
                        if (detail11Value != null && detail11Value != ""){
                            detail11.value = detail11Value
                        }
                        val detail12Value = document.getString("detail12")
                        if (detail12Value != null && detail12Value != ""){
                            detail12.value = detail12Value
                        }
                        val detail13Value = document.getString("detail13")
                        if (detail13Value != null && detail13Value != ""){
                            detail13.value = detail13Value
                        }
                        val detail14Value = document.getString("detail14")
                        if (detail14Value != null && detail14Value != ""){
                            detail14.value = detail14Value
                        }
                        val detail15Value = document.getString("detail15")
                        if (detail15Value != null && detail15Value != "") {
                            detail15.value = detail15Value
                        }
                        if (photo1.value.isNotEmpty() && photo1.value != "") {
                            photoUrls.add(photo1.value)
                        }
                        if (photo2.value.isNotEmpty() && photo2.value != "") {
                            photoUrls.add(photo2.value)
                        }
                        if (photo3.value.isNotEmpty() && photo3.value != "") {
                            photoUrls.add(photo3.value)
                        }
                        if (photo4.value.isNotEmpty() && photo4.value != "") {
                            photoUrls.add(photo4.value)
                        }
                        price.value = document.getString("price")!!
                        oldPrice.value = document.getString("oldPrice")!!
                        discount.value = document.getString("discount")!!
                    }
                    pagerLoading.value = false
                }
                .addOnFailureListener {
                    println(it)
                }
        }
        println(photoUrls)
        println(photo1)
        val maxPage = photoUrls.size - 1

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .border(BorderStroke(5.dp, MaterialTheme.colorScheme.onSecondary)),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            5.dp, MaterialTheme.colorScheme.onSecondary,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(20.dp))

                ) {

                    if (pagerLoading.value) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LinearProgressIndicator(color = MaterialTheme.colorScheme.secondary)
                        }

                    } else {
                        val pagerState = rememberPagerState(pageCount = { photoUrls.size })

                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxWidth()
                        ) { page ->

                            val painter = rememberAsyncImagePainter(model = photoUrls[page])
                                Image(
                                    painter = painter,
                                    contentDescription = "Hoparlör Detayları",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(20.dp)),
                                    contentScale = ContentScale.Crop
                                )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Transparent,// Başlangıç rengi
                                            MaterialTheme.colorScheme.onPrimary    // Bitiş rengi
                                        ),
                                        startY = 0f,
                                        endY = 1000f // Yüksekliği ayarlayın
                                    )
                                )
                        ) {

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (pagerState.currentPage > 0) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowLeft,
                                    contentDescription = "Sola gitme",
                                    tint = MaterialTheme.colorScheme.onSecondary
                                    , modifier = Modifier
                                        .size(36.dp)
                                        .clickable {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                            }
                                        }
                                )
                            }

                            if (pagerState.currentPage < maxPage) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "Sağa gitme",
                                    tint = MaterialTheme.colorScheme.onSecondary
                                    , modifier = Modifier
                                        .size(36.dp)
                                        .clickable {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                            }
                                        }
                                )
                            }
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            //Ürünün İsmi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = productTitle,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Ürün Detayları",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 20.sp,
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
            //Özellikler
            LazyColumn() {
                item {
                    Column(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Spacer(modifier = Modifier.padding(top = 32.dp))
                        val detailFontSize = 16.dp
                        if (detail1.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail1.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail2.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail2.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail3.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail3.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail4.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail4.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail5.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail5.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail6.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail6.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail7.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail7.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail8.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail9.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail10.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail10.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail11.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail11.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail12.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail12.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail13.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail13.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }
                        if (detail14.value != "") {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Circle, // İkonun türünü ve rengini ayarlayın
                                    contentDescription = "Detay Belirtme",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = detail14.value, color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp()}
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    //Yorumlar
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Yorumlar",
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 20.sp,
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
                    Row(Modifier.fillMaxWidth()) {
                        Text(text = "Yorumları Çekicez Buraya ")
                    }
                    Row(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        val comments = remember {
                            mutableStateOf("")
                        }
                        OutlinedTextField(value = comments.value,
                            onValueChange = {
                                comments.value = it
                            },
                            modifier = Modifier.fillMaxWidth(0.9f),
                            label = {
                                Text(
                                    text = "Yorum Yap!",
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
                                imeAction = ImeAction.Send
                            ),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Send,
                                    contentDescription = "Yorum Yapma",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            })
                    }
                }
                item { 
                    Spacer(modifier = Modifier.height(105.dp))
                }
            }


            //Yorum ve Yıldız ve FİYAT bilgisi
            /*
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {

                //Yıldızlar
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.StarBorder,
                            contentDescription = "Sepetim",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }

                //Yorum sayısı - Puanı - FİYAT BİLGİSİ
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)

                ) {
                    Text(
                        text = "31 Yorum - 4.9 puan",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            
             */
/*
            //LazyColumn Dışında kalacak FİYAT BİLGİSİ - SATIN ALMA - SEPETE EKLEME


 */

        }
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom) {
            val shoppingBarFontSize = 20.dp
            val oldPriceFontSize = 18.dp
            Spacer(modifier = Modifier.padding(start = 8.dp))
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.secondary)
                //BORDER EKLENEBİLİR
                ,
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                //FİYAT BİLGİSİ
                Row(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = oldPrice.value,
                            color = Color(100,100,100,255),
                            fontSize = with(LocalDensity.current) {oldPriceFontSize.toSp()},
                            textDecoration = TextDecoration.LineThrough
                        )
                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        Text(
                            text = price.value, color = MaterialTheme.colorScheme.primary,
                            fontSize = with(LocalDensity.current) {shoppingBarFontSize.toSp()}, fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(120.dp)
                            .height(60.dp)
                            .border(
                                BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                                RoundedCornerShape(5.dp)
                            ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(text = "Satın Al", color = MaterialTheme.colorScheme.secondary)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(120.dp)
                            .height(60.dp)
                            .border(
                                BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                                RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Sepete Ekle",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = with(LocalDensity.current) {oldPriceFontSize.toSp()},
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
