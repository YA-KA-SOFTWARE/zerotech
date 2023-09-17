package com.yakasoftware.zerotech.views

import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine
import com.yakasoftware.zerotech.R
import kotlinx.coroutines.tasks.await
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
                val type = remember {
                    mutableStateOf("")
                }
                val favList =remember{ mutableStateListOf<FavProduct>() }
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
                    if (favList != null){



                }
            }
                LazyColumn{
                    items(favList.size){index ->
                        val favListData = favList[index]
                        Column {
                            //ürün çeşitlerine göre favListData.type üzerinden if else ile doğru detail sayfasına yönlendirme yapmayı düşünüyorum ona göre ayarlamanızı yapın M.K.
                            Text(text = favListData.title, color = MaterialTheme.colorScheme.secondary)
                            val painter = rememberAsyncImagePainter(model = favListData.photo1)
                            Image(painter = painter, contentDescription ="photo1", Modifier.size(50.dp))
                            Text(text = "${favListData.price}",color = MaterialTheme.colorScheme.secondary)
                            Text(text = "${favListData.oldPrice}", color = MaterialTheme.colorScheme.secondary)
                            Text(text = "${favListData.discount}", color = MaterialTheme.colorScheme.secondary)
                        }

                    }
                }
        }
    }
}

