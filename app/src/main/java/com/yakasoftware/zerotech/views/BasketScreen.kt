package com.yakasoftware.zerotech.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@SuppressLint("SimpleDateFormat")
@Composable
fun BasketScreen(navController: NavHostController) {

    val basketDialog = remember {
        mutableStateOf(false)
    }
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
    val adressDialog = remember {
        mutableStateOf(false)
    }
    data class BasketProduct(
        val title: String,
        val photo1: String,
        val price: String,
        val oldPrice: String,
        val discount: String,
        val type: String,
        val amount: String,
        val date: com.google.firebase.Timestamp?,
        val onay: Boolean,
        val docId: String,
        val color: String
    )
//Adres kısmı
    data class Adresses(
        val adresstitle: String,
        val city: String,
        val direction: String,
        val district: String,
        val neighbourhood: String,
        val documentId: String,
        val street: String,
    )
    val adressList = remember { mutableStateListOf<Adresses>() }
    val context = LocalContext.current
    val adresstitle = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val direction = remember { mutableStateOf("") }
    val district = remember { mutableStateOf("") }
    val neighbourhood = remember { mutableStateOf("") }
    val documentId = remember { mutableStateOf("") }
    val street = remember { mutableStateOf("") }


    val oldPrice = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val photo1 = remember { mutableStateOf("") }
    val discount = remember { mutableStateOf("") }
    val type = remember { mutableStateOf("") }
    val title = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf("") }
    val date = remember { mutableStateOf<com.google.firebase.Timestamp?>(null) }
    val onay = remember { mutableStateOf(false) }
    val docId = remember {
        mutableStateOf("")
    }
    val totalPrice = remember { mutableStateOf(0.0) }

    val color = remember { mutableStateOf("") }

    val basList = remember { mutableStateListOf<BasketProduct>() }
    fun calculateTotalPrice(basList: List<BasketProduct>): Double {
        var totalPrice = 0.0
        for (baskets in basList) {
            totalPrice += baskets.price.toFloat() * baskets.amount.toInt()
        }
        return totalPrice
    }
    totalPrice.value = calculateTotalPrice(basList)
    db.collection("basket").whereEqualTo("email", email).get()
        .addOnSuccessListener { documents ->
            basList.clear()
            for (document in documents) {
                val basketData: Map<String, Any> = document.data
                title.value = basketData["title"].toString()
                oldPrice.value = basketData["oldPrice"].toString()
                price.value = basketData["price"].toString()
                photo1.value = basketData["photo1"].toString()
                discount.value = basketData["discount"].toString()
                type.value = basketData["type"].toString()
                docId.value = basketData["docId"].toString()
                color.value = basketData["color"].toString()
                val dateValue = basketData["date"]
                date.value = if (dateValue is com.google.firebase.Timestamp) {
                    dateValue
                } else {
                    null // Değer "com.google.firebase.Timestamp" değilse, null olarak ayarlayın
                }
                amount.value = basketData["amount"].toString()
                onay.value = basketData["onay"] as? Boolean ?: false
                basList.add(
                    BasketProduct(
                        title.value,
                        photo1.value,
                        price.value,
                        oldPrice.value,
                        discount.value,
                        type.value,
                        amount.value,
                        date.value,
                        onay.value,
                        docId.value,
                        color.value
                    )
                )
            }
        }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {

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
                                    .border(
                                        BorderStroke(
                                            2.dp,
                                            MaterialTheme.colorScheme.onBackground
                                        ), CircleShape
                                    )
                                    .background(MaterialTheme.colorScheme.onSecondary)
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
                    Text(text = "Sepetim", color = MaterialTheme.colorScheme.secondary,
                        fontSize = with(LocalDensity.current) { sideBarFontSize.toSp() })
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Icon(
                        imageVector = Icons.Default.ShoppingBasket,
                        contentDescription = "Sepetim",
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


                val emptyFontSize = 18.dp
                if (basList.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .size(250.dp)
                            .background(MaterialTheme.colorScheme.onTertiary),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column(modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.RemoveShoppingCart,
                                    contentDescription = "Boş Sepet",
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(text = "Sepetinizde Ürün Bulunmamaktadır.", color = MaterialTheme.colorScheme.secondary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = with(LocalDensity.current){emptyFontSize.toSp()},
                                    textAlign = TextAlign.Center
                                )

                            }
                        }
                    }


                }else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        val fontSize = 12.dp
                        val fontSizePrice = 16.dp
                        items(basList.size) { index ->
                            val baskets = basList[index]
                            val painter = rememberAsyncImagePainter(model = baskets.photo1)
                            val sepetSayisi =
                                remember { mutableStateOf(baskets.amount.toInt()) }


                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.primary)
                                    .clickable {
                                        if (baskets.type == "speakers") {
                                            navController.navigate("speaker_detail_screen/${baskets.title}")
                                        }
                                        if (baskets.type == "headphones") {
                                            navController.navigate("headphones_detail_screen/${baskets.title}")
                                        }
                                        if (baskets.type == "accesories") {
                                            navController.navigate("accesories_detail_screen/${baskets.title}")
                                        }
                                        if (baskets.type == "bands") {
                                            navController.navigate("band_detail_screen/${baskets.title}")
                                        }
                                        if (baskets.type == "watchs") {
                                            navController.navigate("watch_detail_screen/${baskets.title}")
                                        }
                                        if (baskets.type == "campaign") {
                                            navController.navigate("main_detail_screen/${baskets.title}")
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .size(280.dp)
                                        .padding(10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    // İlk dikdörtgeni üç parçaya böl
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxSize()
                                            .background(
                                                MaterialTheme.colorScheme.onSecondary,
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
                                            Image(
                                                painter = painter,
                                                contentDescription = "Sepetteki ürün",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .clip(
                                                        RoundedCornerShape(
                                                            10.dp,
                                                            10.dp,
                                                            0.dp,
                                                            0.dp
                                                        )
                                                    )
                                            )
                                            Icon(
                                                imageVector = Icons.Default.RemoveShoppingCart,
                                                contentDescription = "Sepetten Çıkar",
                                                tint = MaterialTheme.colorScheme.onSecondary,
                                                modifier = Modifier
                                                    .size(34.dp)
                                                    .align(alignment = Alignment.TopEnd)
                                                    .background(
                                                        Color(255, 255, 255, 255),
                                                        CircleShape
                                                    )
                                                    .clickable {
                                                        val controlEmail =
                                                            Firebase.auth.currentUser?.email
                                                        val controlDb = Firebase.firestore
                                                        if (controlEmail != null) {
                                                            val query = controlDb
                                                                .collection("basket")
                                                                .whereEqualTo("email", controlEmail)
                                                            query
                                                                .get()
                                                                .addOnSuccessListener { documents ->
                                                                    for (document in documents) {
                                                                        val docEmail =
                                                                            document.getString("email")
                                                                        if (docEmail == controlEmail) {
                                                                            // Oturum açmış kullanıcıya ait belgeyi silme işlemi
                                                                            val docIdDelete =
                                                                                baskets.docId
                                                                            controlDb
                                                                                .collection("basket")
                                                                                .document(
                                                                                    docIdDelete
                                                                                )
                                                                                .delete()
                                                                                .addOnSuccessListener {
                                                                                    // Belge başarıyla silindi
                                                                                    navController.navigate(
                                                                                        "basket_screen"
                                                                                    ) {
                                                                                        popUpTo("basket_screen") {
                                                                                            inclusive =
                                                                                                true
                                                                                        }
                                                                                    }
                                                                                    println("Belge silindi: $docIdDelete")
                                                                                }
                                                                                .addOnFailureListener { e ->
                                                                                    // Hata durumunda bildirim veya kayıt yapabilirsiniz
                                                                                    println("Belge silme hatası: $e")
                                                                                }
                                                                        } else {
                                                                            // Bu belge oturum açmış kullanıcıya ait değil
                                                                            println("Bu belgeyi silemezsiniz.")
                                                                        }
                                                                    }
                                                                }
                                                                .addOnFailureListener { e ->
                                                                    // Sorgu başarısız oldu
                                                                    println("Sorgu hatası: $e")
                                                                }
                                                        }
                                                    }
                                            )
                                            val colorShape = if (baskets.color != "null") 0.dp else 10.dp
                                            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
                                                Row (Modifier.fillMaxSize()){
                                                Text(
                                                    text = sepetSayisi.value.toString() + " " + "adet",
                                                    color = Color.Black,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                    modifier = Modifier
                                                        .background(
                                                            MaterialTheme.colorScheme.onSecondary,
                                                            shape = RoundedCornerShape(
                                                                0.dp,
                                                                0.dp,
                                                                colorShape,
                                                                0.dp
                                                            )
                                                        )
                                                        .padding(8.dp)
                                                )

                                                if (baskets.color != "null") {
                                                    Text(
                                                        text = baskets.color,
                                                        color = Color.DarkGray,
                                                        fontWeight = FontWeight.Light,
                                                        fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                        modifier = Modifier
                                                            .background(
                                                                MaterialTheme.colorScheme.onSecondary,
                                                                shape = RoundedCornerShape(
                                                                    0.dp,
                                                                    0.dp,
                                                                    10.dp,
                                                                    0.dp
                                                                )
                                                            )
                                                            .padding(8.dp)
                                                    )
                                                }
                                            }
                                                Spacer(modifier = Modifier.padding(top = 25.dp))
                                                //RENK YAZISI BURADA

                                            }
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
                                                            endY = 700f // Yüksekliği ayarlayın
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
                                                .weight(1.4f)
                                                .background(
                                                    MaterialTheme.colorScheme.onPrimary,
                                                    RoundedCornerShape(10.dp)
                                                )

                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxSize(),
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    Box(modifier = Modifier.fillMaxWidth(),
                                                        contentAlignment = Alignment.Center) {
                                                        Text(
                                                            text = baskets.title,
                                                            color = MaterialTheme.colorScheme.onBackground,
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                            textAlign = TextAlign.Center,
                                                            lineHeight = 12.sp
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.weight(1f))
                                                    Row(
                                                        Modifier.fillMaxWidth(),
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        horizontalArrangement = Arrangement.SpaceBetween
                                                    ) {


                                                        Column(
                                                            modifier = Modifier
                                                                .wrapContentSize(),
                                                            verticalArrangement = Arrangement.Center,
                                                            horizontalAlignment = Alignment.Start
                                                        ) {
                                                            Text(
                                                                text = String.format(
                                                                    "%.2f",
                                                                    baskets.oldPrice.toFloat() * sepetSayisi.value
                                                                ) + "₺",
                                                                color = Color(100, 100, 100, 255),
                                                                fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                                textAlign = TextAlign.Center,
                                                                textDecoration = TextDecoration.LineThrough
                                                            )

                                                            Text(
                                                                text = String.format(
                                                                    "%.2f",
                                                                    baskets.price.toFloat() * sepetSayisi.value
                                                                ) + "₺",
                                                                color = MaterialTheme.colorScheme.onSurface,
                                                                fontSize = with(LocalDensity.current) { fontSizePrice.toSp() },
                                                                fontWeight = FontWeight.Bold,
                                                                textAlign = TextAlign.Center
                                                            )

                                                        }

                                                        Spacer(modifier = Modifier.weight(1f))


                                                        Spacer(modifier = Modifier.weight(0.2f))
                                                        Box(
                                                            modifier = Modifier
                                                                .size(20.dp)
                                                                .clip(RoundedCornerShape(20.dp))
                                                                .background(
                                                                    MaterialTheme.colorScheme.onSecondary
                                                                )
                                                                .clickable {
                                                                    if (sepetSayisi.value > 1) {
                                                                        sepetSayisi.value -= 1
                                                                        val documentReference = db
                                                                            .collection("basket")
                                                                            .document(baskets.docId)

                                                                        documentReference.update(
                                                                            "amount",
                                                                            sepetSayisi.value.toString()
                                                                        )
                                                                    }
                                                                },
                                                            contentAlignment = Alignment.CenterStart
                                                        ) {

                                                            Text(
                                                                text = "-",
                                                                fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                                modifier = Modifier
                                                                    .align(Alignment.Center)
                                                            )

                                                        }
                                                        Spacer(modifier = Modifier.weight(0.5f))

                                                        Text(
                                                            text = sepetSayisi.value.toString(),
                                                            fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                            color = MaterialTheme.colorScheme.secondary
                                                        )

                                                        Spacer(modifier = Modifier.weight(0.5f))
                                                        Box(
                                                            modifier = Modifier
                                                                .size(20.dp)
                                                                .clip(RoundedCornerShape(20.dp))
                                                                .background(MaterialTheme.colorScheme.onSecondary)
                                                                .clickable {
                                                                    sepetSayisi.value += 1
                                                                    val documentReference = db
                                                                        .collection("basket")
                                                                        .document(baskets.docId)

                                                                    documentReference.update(
                                                                        "amount",
                                                                        sepetSayisi.value.toString()
                                                                    )

                                                                },
                                                            contentAlignment = Alignment.CenterEnd
                                                        ) {

                                                            Text(
                                                                text = "+",
                                                                fontSize = with(LocalDensity.current) { fontSize.toSp() },
                                                                modifier = Modifier
                                                                    .align(Alignment.Center)
                                                            )

                                                        }
                                                        Spacer(modifier = Modifier.weight(0.2f))


                                                    }

                                                }

                                            }

                                        }

                                    }

                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(120.dp))
                        }
                    }
                }

            }
        }
        if (basList.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                val shoppingBarFontSize = 18.dp
                val buttonFontSize = 14.dp
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(MaterialTheme.colorScheme.secondary),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(text = "Toplam Fiyat:",
                        fontSize = with(LocalDensity.current) {
                            shoppingBarFontSize.toSp()
                        },
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "${String.format("%.2f", totalPrice.value)}₺",
                        fontSize = with(LocalDensity.current){shoppingBarFontSize.toSp()},
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = {
                        basketDialog.value = true
                    }) {
                        Text(text = "Siparişi Onayla", color = MaterialTheme.colorScheme.tertiary,
                            fontSize = with(LocalDensity.current) {
                                buttonFontSize.toSp()
                            })
                    }

                }

            }
        }

    }
    val firestoreData = basList.map { basketProduct ->
        mapOf(
            "title" to basketProduct.title,
            "photo1" to basketProduct.photo1,
            "price" to basketProduct.price,
            "oldPrice" to basketProduct.oldPrice,
            "discount" to basketProduct.discount,
            "type" to basketProduct.type,
            "amount" to basketProduct.amount,
            "date" to basketProduct.date,
            "onay" to basketProduct.onay,
            "docId" to basketProduct.docId,
            "color" to basketProduct.color
        )
    }

    db.collection("adress").document(email!!).collection(email).get().addOnSuccessListener { documents ->
        adressList.clear()
        for (document in documents){
            val adressesData: Map<String, Any> = document.data
            adresstitle.value = adressesData["adresstitle"].toString()
            city.value = adressesData["city"].toString()
            direction.value = adressesData["direction"].toString()
            district.value = adressesData["district"].toString()
            documentId.value = adressesData["documentId"].toString()
            neighbourhood.value = adressesData["neighbourhood"].toString()
            street.value = adressesData["street"].toString()

            adressList.add(Adresses(adresstitle.value,city.value,direction.value, district.value,documentId.value,neighbourhood.value,street.value))

        }

    }

    if (basketDialog.value) {
        val alertFontSize = 12.dp
        AlertDialog(
            onDismissRequest = { basketDialog.value = false },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Shop,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Sipariş Onaylama",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.padding(top = 6.dp))
                        Text(text = "Siparişi onaylamak istediğinize emin misiniz?", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize =
                            with(LocalDensity.current){alertFontSize.toSp()},
                            textAlign = TextAlign.Center)

                    }


                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        basketDialog.value = false
                        if (adressList.isNotEmpty()) {
                            adressDialog.value = true

                        }else {
                            Toast.makeText(context,"Siparişi tamamlayabilmek için adres bilgileri gereklidir.",Toast.LENGTH_LONG).show()
                            navController.navigate("adress_screen")
                        }

                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary)
                )
                {
                    Text(text = "Evet")
                }
            },
            dismissButton = {
                // Kapat düğmesi
                Button(
                    onClick = {
                        basketDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary)
                )
                {
                    Text(text = "Hayır")
                }
            }
        )

    }

    if (adressDialog.value){
        AlertDialog(onDismissRequest = { adressDialog.value = false },modifier = Modifier.wrapContentHeight() ,confirmButton = { /*TODO*/ },
            title = {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Adresinizi Seçiniz",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            },
            text = {
            LazyColumn{
                val adressFontSize = 16.dp
                val directionFontSize = 12.dp
                items(adressList.size){index ->
                    val adressData = adressList[index]
                    val adresMap = hashMapOf(
                        "adresstitle" to adressData.adresstitle,
                        "city" to adressData.city,
                        "direction" to adressData.direction,
                        "district" to adressData.district,
                        "neighbourhood" to adressData.neighbourhood,
                        "street" to adressData.street

                    )
                    Row(modifier = Modifier.fillMaxWidth() ){
                        Button(onClick = { adressDialog.value = false
                                if (currentUser.email != null) {
                                    db.collection("confirm")
                                        .document(currentUser.email!!)
                                        .collection(currentUser.email!!)
                                        .add(mapOf("basketData" to firestoreData,
                                                "adressData" to adresMap)).addOnSuccessListener {
                                                    println("veri başarı ile gönderildi")
                                        }

                                }
                            navController.navigate("confirm_screen")}, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                            containerColor =MaterialTheme.colorScheme.onTertiary,
                            disabledContentColor = Color.Blue ,
                            disabledContainerColor = Color.White
                        )) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = adressData.adresstitle, fontSize = with(LocalDensity.current){
                                        adressFontSize.toSp()
                                    })
                                }
                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(text = adressData.direction, fontSize = with(LocalDensity.current){
                                    directionFontSize.toSp()
                                })
                            }

                            }

                        }

                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }

            }
            }

        )
    }
}
