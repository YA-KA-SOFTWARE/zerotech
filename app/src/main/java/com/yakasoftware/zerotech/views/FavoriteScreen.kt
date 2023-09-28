package com.yakasoftware.zerotech.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine
import com.yakasoftware.zerotech.R
import androidx.compose.foundation.lazy.LazyColumn as LazyColumn


@Composable
fun FavoriteScreen(navController: NavHostController) {

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
            Spacer(modifier = Modifier.padding(top = 28.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Favorilerim", color = MaterialTheme.colorScheme.secondary,
                    fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    painter = painterResource(R.drawable.likestr),
                    contentDescription = "favorilerim",
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
            Spacer(modifier = Modifier.padding(top = 12.dp))
            data class FavProduct (
                val title: String,
                val photo1: String,
                val price: String,
                val oldPrice: String,
                val discount: String,
                val type: String
            )
            val db = Firebase.firestore

            val title = remember { mutableStateOf("") }
            val photo1 = remember{ mutableStateOf("") }
            val price = remember{ mutableStateOf("") }
            val oldPrice = remember { mutableStateOf("") }
            val discount = remember{ mutableStateOf("") }

            val fontSize = 12.dp
            val fontSizePrice = 16.dp
            val type = remember {
                mutableStateOf("")
            }
            val favList = remember{ mutableStateListOf<FavProduct>() }
            val collectionRef = db.collection("fav").document(email!!).collection(email)
            collectionRef.get().addOnSuccessListener { documents ->
                favList.clear()
                for(document in documents){
                    val favData: Map<String,Any> = document.data
                    title.value = favData["title"].toString()
                    photo1.value = favData["photo1"].toString()
                    price.value = favData["price"].toString()
                    oldPrice.value = favData["oldPrice"].toString()
                    discount.value = favData["discount"].toString()
                    type.value = favData["type"].toString()
                    favList.add(FavProduct(title.value,photo1.value,price.value,oldPrice.value,discount.value,type.value))
                }
            }
            LazyColumn{
                items(if(favList.size%2==0)favList.size/2 else favList.size/2 + 1) { rowIndex ->
                    val firstSpeakerIndex = rowIndex * 2
                    val secondSpeakerIndex = rowIndex * 2 + 1
                    val favListData = favList[firstSpeakerIndex]
                    val secondSpeakerData = favList.getOrNull(secondSpeakerIndex)
                    Column {

                        //ürün çeşitlerine göre favListData.type üzerinden if else ile doğru detail sayfasına yönlendirme yapmayı düşünüyorum ona göre ayarlamanızı yapın M.K.
                        val painter = rememberAsyncImagePainter(model = favListData.photo1)
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primary)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(280.dp)
                                    .padding(10.dp),
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
                                        .clickable {
                                            if (favListData.type == "speakers") {
                                                navController.navigate("speaker_detail_screen/${favListData.title}")
                                            }
                                            if (favListData.type == "accesories") {
                                                navController.navigate("accesories_detail_screen/${favListData.title}")
                                            }
                                            if (favListData.type == "headphones") {
                                                navController.navigate("headphones_detail_screen/${favListData.title}")
                                            }
                                            if (favListData.type == "bands") {
                                                navController.navigate("band_detail_screen/${favListData.title}")
                                            }
                                        }
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
                                        Image(
                                            painter = painter,
                                            contentDescription = "Hoparlör",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                        Icon(
                                            imageVector = Icons.Default.Favorite,
                                            contentDescription = "Favorilerim",
                                            tint = Color(238, 69, 69, 255),
                                            modifier = Modifier
                                                .size(34.dp)
                                                .align(alignment = Alignment.TopEnd)
                                                .background(Color(255, 255, 255, 255),
                                                    CircleShape)
                                                .clickable {
                                                    val favDb = Firebase.firestore
                                                    val userEmail =
                                                        Firebase.auth.currentUser?.email
                                                    if (userEmail != null) {
                                                        val docRef = favDb
                                                            .collection("fav")
                                                            .document(userEmail)
                                                            .collection(userEmail)
                                                            .document(favListData.title)
                                                        docRef
                                                            .delete()
                                                            .addOnSuccessListener {
                                                                navController.navigate("favorite_screen"){
                                                                    popUpTo("favorite_screen"){
                                                                        inclusive = true
                                                                    }
                                                                }
                                                            }
                                                            .addOnFailureListener {
                                                                println(it)
                                                            }
                                                    }
                                                }

                                        )

                                        Column(
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
                                                        endY = 500f // Yüksekliği ayarlayın
                                                    )
                                                ),
                                            verticalArrangement = Arrangement.Bottom,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {

                                        }
                                    }
                                    //Sepete ekleme - ürün fiyat
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1.5f)
                                            .background(
                                                MaterialTheme.colorScheme.onPrimary,
                                                RoundedCornerShape(10.dp)
                                            )

                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Start
                                        ) {
                                            Column(
                                                modifier = Modifier.fillMaxSize(),
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Row(modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.Center,
                                                    verticalAlignment = Alignment.CenterVertically) {
                                                    Text(
                                                        text = favListData.title, color = Color(
                                                            255,
                                                            231,
                                                            208,
                                                            255
                                                        ), fontWeight = FontWeight.Bold,
                                                        fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                        textAlign = TextAlign.Center, lineHeight = 12.sp
                                                    )
                                                }
                                                Spacer(modifier = Modifier.weight(1f))
                                                Column(modifier = Modifier.fillMaxSize(),
                                                    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                                    Text(
                                                        text = favListData.oldPrice + "₺",
                                                        color = Color(100, 100, 100, 255),
                                                        fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                        textAlign = TextAlign.Center,
                                                        textDecoration = TextDecoration.LineThrough
                                                    )
                                                    Text(
                                                        text = favListData.price + "₺",
                                                        color = MaterialTheme.colorScheme.secondary,
                                                        fontSize = with(LocalDensity.current) { fontSizePrice.toSp() },
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center
                                                    )
                                                }
                                                Spacer(modifier = Modifier.weight(1f))

                                            }

                                        }

                                    }

                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                if (secondSpeakerData != null) {
                                    val painter2 =
                                        rememberAsyncImagePainter(model = secondSpeakerData.photo1)
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxSize()
                                            .background(
                                                MaterialTheme.colorScheme.secondary,
                                                RoundedCornerShape(14.dp)
                                            )
                                            .clickable {
                                                if (secondSpeakerData.type == "speakers") {
                                                    navController.navigate("speaker_detail_screen/${secondSpeakerData.title}")
                                                }
                                                if (secondSpeakerData.type == "accesories") {
                                                    navController.navigate("accesories_detail_screen/${secondSpeakerData.title}")
                                                }
                                                if (secondSpeakerData.type == "headphones") {
                                                    navController.navigate("headphones_detail_screen/${secondSpeakerData.title}")
                                                }
                                                if (secondSpeakerData.type == "bands") {
                                                    navController.navigate("band_detail_screen/${secondSpeakerData.title}")
                                                }
                                            }
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
                                            Image(
                                                painter = painter2,
                                                contentDescription = "Hoparlör",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .clip(RoundedCornerShape(10.dp))
                                            )

                                            Icon(
                                                imageVector = Icons.Default.Favorite,
                                                contentDescription = "Favorilerim",
                                                tint = Color(238, 69, 69, 255),
                                                modifier = Modifier
                                                    .size(34.dp)
                                                    .align(alignment = Alignment.TopEnd)
                                                    .background(Color(255, 255, 255, 255),
                                                        CircleShape)
                                                    .clickable {
                                                        val favDb = Firebase.firestore
                                                        val userEmail =
                                                            Firebase.auth.currentUser?.email
                                                        if (userEmail != null) {
                                                            val docRef = favDb
                                                                .collection("fav")
                                                                .document(userEmail)
                                                                .collection(userEmail)
                                                                .document(secondSpeakerData.title)
                                                            docRef
                                                                .delete()
                                                                .addOnSuccessListener {
                                                                    navController.navigate("favorite_screen"){
                                                                        popUpTo("favorite_screen"){
                                                                            inclusive = true
                                                                        }
                                                                    }
                                                                }
                                                                .addOnFailureListener {
                                                                    println(it)
                                                                }
                                                        }
                                                    }

                                            )

                                            Column(
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
                                                            endY = 500f // Yüksekliği ayarlayın
                                                        )
                                                    ),
                                                verticalArrangement = Arrangement.Bottom,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {

                                            }
                                        }
                                        //Sepete ekleme - ürün fiyat
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(1.5f)
                                                .background(
                                                    MaterialTheme.colorScheme.onPrimary,
                                                    RoundedCornerShape(10.dp)
                                                )

                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                                Column(
                                                    modifier = Modifier.fillMaxSize(),
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    Box(modifier = Modifier.fillMaxWidth()) {
                                                        Text(
                                                            text = secondSpeakerData.title, color = Color(
                                                                255,
                                                                231,
                                                                208,
                                                                255
                                                            ), fontWeight = FontWeight.Bold,
                                                            fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                            textAlign = TextAlign.Center, lineHeight = 12.sp
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.weight(1f))
                                                    Column(modifier = Modifier.fillMaxSize(),
                                                        verticalArrangement = Arrangement.Center,
                                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                                        Text(
                                                            text = secondSpeakerData.oldPrice + "₺",
                                                            color = Color(100, 100, 100, 255),
                                                            fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                            textAlign = TextAlign.Center,
                                                            textDecoration = TextDecoration.LineThrough
                                                        )
                                                        Text(
                                                            text = secondSpeakerData.price + "₺",
                                                            color = MaterialTheme.colorScheme.secondary,
                                                            fontSize = with(LocalDensity.current) { fontSizePrice.toSp() },
                                                            fontWeight = FontWeight.Bold,
                                                            textAlign = TextAlign.Center
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.weight(1f))

                                                }

                                            }
                                        }
                                    }
                                }
                                else{
                                    println("Tek sayı hatası")
                                }
                            }


                        }

                    }
                }

            }
        }
    }
}

