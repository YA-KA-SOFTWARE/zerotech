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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpeakerDetailScreen(navController: NavHostController, productTitle: String) {
    val db = Firebase.firestore
    val proCollection = db.collection("products").document("speakers")
    val docRef = proCollection.collection("Aggiy AG-S21 Bluetooth Hoparlör")
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
                        .border(5.dp, MaterialTheme.colorScheme.onSecondary,
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
                                painter = painter, contentDescription = "Hoparlör Detayları",
                                modifier = Modifier.fillMaxSize()
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

            Spacer(modifier = Modifier.height(8.dp))

            //Yorum ve Yıldız ve FİYAT bilgisi
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
            Spacer(modifier = Modifier.weight(1f))

            //LazyColumn Dışında kalacak FİYAT BİLGİSİ - SATIN ALMA - SEPETE EKLEME
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(255, 172, 130, 72))
                    //BORDER EKLENEBİLİR
                        ,
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                //FİYAT BİLGİSİ
                Row(
                    modifier = Modifier
                        .size(100.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "5252",
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        Text(
                            text = "3131", color = MaterialTheme.colorScheme.secondary,
                            fontSize = 20.sp, fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
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
                                BorderStroke(2.dp, Color(255, 153, 102, 255)),
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
                                BorderStroke(2.dp, Color(255, 153, 102, 255)),
                                RoundedCornerShape(5.dp)
                            ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = "Sepete Ekle",
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontSize = 12.sp
                        )
                    }
                }
            }

        }
    }
}
