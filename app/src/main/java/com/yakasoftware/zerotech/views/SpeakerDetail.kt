package com.yakasoftware.zerotech.views


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yakasoftware.zerotech.Lines.SimpleLine
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.util.Calendar
import java.util.Date

@SuppressLint("SuspiciousIndentation")
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
    val context = LocalContext.current

    val photo1 = remember { mutableStateOf("") }
    val photo2 = remember { mutableStateOf("") }
    val photo3 = remember { mutableStateOf("") }
    val photo4 = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val oldPrice = remember { mutableStateOf("") }
    val discount = remember { mutableStateOf("") }
    val type = remember {
        mutableStateOf("")
    }

    val pagerLoading = remember {
        mutableStateOf(true)
    }

    val photoUrls = mutableListOf<String>()

    val isFavorite = remember {
        mutableStateOf(false)

    }

    Surface(Modifier.fillMaxSize()) {
        data class CommentData(
            val productTitle: String,
            val senderName: String,
            val date: Date?,
            val description: String,
            val senderSurName: String
        )

        val name = remember {
            mutableStateOf("")
        }
        val surName = remember {
            mutableStateOf("")
        }
        val date = remember {
            mutableStateOf<Date?>(null)
        }
        val description = remember {
            mutableStateOf("")
        }
        val commentList = remember {
            mutableStateListOf<CommentData>()
        }
        db.collection("comments").whereEqualTo("productTitle", productTitle)
            .get()
            .addOnSuccessListener { documents ->
                commentList.clear()
                for (document in documents) {
                    val commentDt: Map<String, Any> = document.data
                    name.value = commentDt["senderName"].toString()
                    surName.value = commentDt["senderSurName"].toString()
                    date.value = commentDt["date"] as? Timestamp
                    description.value = commentDt["description"].toString()
                    commentList.add(
                        CommentData(
                            productTitle,
                            name.value,
                            date.value,
                            description.value,
                            surName.value
                        )
                    )

                }
            }
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
                        type.value = document.getString("type")!!
                    }
                    pagerLoading.value = false
                }
                .addOnFailureListener {
                    println(it)
                }
        }

        val controlEmail = Firebase.auth.currentUser?.email
        val controlFavDb = Firebase.firestore
        LaunchedEffect(isFavorite.value){
            if (controlEmail != null) {
                val controlDocRef = controlFavDb.collection("fav").document(controlEmail)
                    .collection(controlEmail)
                    .document(productTitle)
                controlDocRef.get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            isFavorite.value = true
                        }
                    }
                    .addOnFailureListener {
                        println(it)
                    }
            }
        }

        val maxPage = photoUrls.size - 1

        val sepetSayisi = remember {
            mutableStateOf(1)
        }

        val floatPrice = price.value.toFloatOrNull() ?: 0.0f
        val floatOldPrice = oldPrice.value.toFloatOrNull() ?: 0.0f

        val para = floatPrice * sepetSayisi.value.toFloat()
        val oldPara = floatOldPrice * sepetSayisi.value.toFloat()


        val isAtTop = remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val targetHeight = animateDpAsState(
            if (isAtTop.value) 0.dp else 335.dp,
            animationSpec = spring(), label = ""
        )

        val onay = remember {
            mutableStateOf(false)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(targetHeight.value)
                    .animateContentSize(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
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
                                    .fillMaxSize(),
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
                                            MaterialTheme.colorScheme.primary    // Bitiş rengi
                                        ),
                                        startY = 300f,
                                        endY = 900f // Yüksekliği ayarlayın
                                    )
                                )
                        ) {

                        }
                        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Start) {
                            repeat(4) {
                                Box(
                                    modifier = Modifier
                                        .size(18.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Yıldızlar",
                                        tint = Color(255, 153, 102, 255)
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.StarBorder,
                                    contentDescription = "Yıldızlar",
                                    tint = Color(255, 153, 102, 255)
                                )
                            }

                            Text(
                                text = "${commentList.size} Değerlendirme",
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.ExtraLight
                            )

                            Spacer(modifier = Modifier.weight(1f))


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

                            val favData = hashMapOf(
                                "oldPrice" to oldPrice.value,
                                "price" to price.value,
                                "photo1" to photo1.value,
                                "discount" to discount.value,
                                "type" to type.value,
                                "title" to productTitle
                            )

                            val sizeState = remember {
                                androidx.compose.animation.core.Animatable(
                                    1f
                                )
                            }
                            Row(Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.Top) {

                                if (!isFavorite.value) {
                                    LaunchedEffect(!isFavorite.value) {
                                        if (!isFavorite.value) {
                                            sizeState.animateTo(1.2f)
                                            sizeState.animateTo(1f)
                                        }
                                    }
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Favorilerim",
                                        tint = MaterialTheme.colorScheme.onSecondary,
                                        modifier = Modifier
                                            .size(34.dp * sizeState.value)
                                            .background(Color(255, 255, 255, 255), CircleShape)
                                            .clickable {
                                                val favDb = Firebase.firestore
                                                val userEmail = Firebase.auth.currentUser?.email
                                                if (userEmail != null) {
                                                    val docReff = favDb
                                                        .collection("fav")
                                                        .document(userEmail)
                                                        .collection(userEmail)
                                                        .document(productTitle)
                                                    docReff
                                                        .set(favData)
                                                        .addOnSuccessListener {
                                                            println("ekledi")
                                                        }
                                                        .addOnFailureListener {
                                                            println(it)
                                                        }
                                                    isFavorite.value = true
                                                } else {
                                                    Toast
                                                        .makeText(
                                                            context,
                                                            "Oturum açmanız gerekiyor.",
                                                            Toast.LENGTH_SHORT
                                                        )
                                                        .show()
                                                }
                                            }

                                    )

                                }else {
                                    LaunchedEffect(isFavorite.value) {
                                        if (isFavorite.value) {
                                            sizeState.animateTo(1.2f)
                                            sizeState.animateTo(1f)
                                        }
                                    }
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorilerim",
                                        tint = Color(238, 69, 69, 255),
                                        modifier = Modifier
                                            .size(34.dp * sizeState.value)
                                            .background(Color(255, 255, 255, 255), CircleShape)
                                            .clickable {
                                                val favDb = Firebase.firestore
                                                val userEmail = Firebase.auth.currentUser?.email
                                                if (userEmail != null) {
                                                    val docReff = favDb
                                                        .collection("fav")
                                                        .document(userEmail)
                                                        .collection(userEmail)
                                                        .document(productTitle)
                                                    docReff
                                                        .delete()
                                                        .addOnSuccessListener {
                                                            isFavorite.value = false
                                                        }
                                                        .addOnFailureListener {
                                                            println(it)
                                                        }
                                                }
                                            }

                                    )
                                }

                            }
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            //Ürünün İsmi
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
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

            }


            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Ürün Detayları",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.padding(3.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.fillMaxWidth(0.650f)) {
                    Spacer(modifier = Modifier.weight(1f))
                    SimpleLine()
                    Spacer(modifier = Modifier.weight(1f))
                }
            }



            LazyColumn (
                modifier = Modifier.disabledVerticalPointerInputScroll(disabled = !isAtTop.value)
            ){
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
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
                                Text(text = detail1.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail2.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail3.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail4.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail5.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail6.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail7.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail9.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail10.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail11.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail12.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail13.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                                Text(text = detail14.value,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = with(LocalDensity.current) { detailFontSize.toSp() }
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(0.650f)) {
                            Spacer(modifier = Modifier.weight(1f))
                            SimpleLine()
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val comments = remember {
                            mutableStateOf("")
                        }
                        val userName = remember {
                            mutableStateOf("")
                        }
                        val userSurName = remember {
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
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.clickable {
                                        val auth = Firebase.auth
                                        val currentUser = auth.currentUser!!
                                        val email = currentUser.email
                                        db.collection("users").document(email!!).get()
                                            .addOnSuccessListener {
                                                val data = it.data
                                                userName.value =
                                                    data?.get("name") as? String ?: ""
                                                userSurName.value =
                                                    data?.get("surname") as? String ?: ""
                                                println(userName.value)
                                                println(userSurName.value)
                                                val calendar = Calendar.getInstance()

                                                val commentData = hashMapOf(
                                                    "senderName" to userName.value,
                                                    "senderSurName" to userSurName.value,
                                                    "description" to comments.value,
                                                    "date" to calendar.time,
                                                    "productTitle" to productTitle
                                                )
                                                if (comments.value.isNotEmpty()) {
                                                    db.collection("comments").add(commentData)
                                                        .addOnSuccessListener {
                                                            Toast.makeText(
                                                                context,
                                                                "Yorum Başarıyla Gönderildi",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                            comments.value = ""
                                                        }
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Yorum Alanı Boş Bırakılamaz",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }


                                    }
                                )
                            })
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }

                //yorum kısmı veri çekme dahil tamam fakat layout ile ilgili bi sıkıntı var front-endciler sizde yargılanacaksınız M.K.
                //yorum kısmı tasarım dahil tamam fakat BENİM SİZİNLE ilgili sorunum var gardaş back-endciler sizde yargılanacaksınız A.Ç.

                item(1) {
                    Column(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentHeight(),
                        ) {

                            for (i in 0 until commentList.size) {

                                val commentData = commentList[i]

                                OutlinedCard(
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.onPrimary,
                                    ),
                                    border = BorderStroke(
                                        1.dp,
                                        MaterialTheme.colorScheme.onSecondary
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(0.85f)
                                        .wrapContentHeight()
                                        .align(Alignment.CenterHorizontally),
                                )
                                {
                                    Row(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .height(20.dp),
                                        verticalAlignment = Alignment.Top,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Spacer(modifier = Modifier.padding(2.dp))
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = commentData.senderName + " " + commentData.senderSurName,
                                            color = Color.LightGray,
                                            fontSize = 14.sp
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        repeat(5) {
                                            Box(
                                                modifier = Modifier
                                                    .size(14.dp),
                                                contentAlignment = Alignment.TopCenter
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Star,
                                                    contentDescription = "Yıldızlar",
                                                    tint = MaterialTheme.colorScheme.onSecondary
                                                )
                                            }
                                        }
                                    }
                                    
                                    Spacer(modifier = Modifier.padding(5.dp))

                                 Row(
                                     Modifier
                                         .fillMaxSize(),
                                     verticalAlignment = Alignment.CenterVertically,
                                     horizontalArrangement = Arrangement.Center
                                 ) {

                                    Column(
                                        Modifier
                                            .fillMaxWidth(0.8f)
                                            .wrapContentHeight()
                                            .defaultMinSize(minHeight = 50.dp)
                                    )
                                    {
                                        Text(
                                            text = commentData.description,
                                            color = MaterialTheme.colorScheme.secondary,
                                            textAlign = TextAlign.Start

                                        )
                                    }
                                    }
                                }
                                Spacer(modifier = Modifier.height(15.dp))

                            }

                        }

                    }


                    /* Column(modifier = Modifier
                         .fillMaxWidth()
                         .height(50.dp)) {
                         Row(
                             Modifier
                                 .fillMaxWidth()
                                 .height(50.dp)) {
                             Text(
                                 text = commentData.senderName,
                                 color = MaterialTheme.colorScheme.secondary
                             )
                             Spacer(modifier = Modifier.padding(5.dp))
                             Text(
                                 text = commentData.senderSurName,
                                 color = MaterialTheme.colorScheme.secondary
                             )
                             Spacer(modifier = Modifier.padding(5.dp))
                             Text(
                                 text = commentData.description,
                                 color = MaterialTheme.colorScheme.secondary
                             )

                         }
                         Spacer(modifier = Modifier.height(15.dp))
                     } */
                }
                item {
                    Spacer(modifier = Modifier.height(300.dp))
                }
            }
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
                            contentDescription = "Yıldızlar",
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

            //LazyColumn Dışında kalacak FİYAT BİLGİSİ - SATIN ALMA - SEPETE EKLEME

        }
        val fontSizeIcon = 20.dp
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            val shoppingBarFontSize = 20.dp
            val oldPriceFontSize = 18.dp
            Spacer(modifier = Modifier.padding(start = 8.dp))
            Row(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, // Başlangıç rengi
                                MaterialTheme.colorScheme.primary  // Bitiş rengi
                            ),
                            startY = 10f,
                            endY = 100f // Yüksekliği ayarlayın
                        )
                    )
                    .fillMaxWidth()
                    .height(80.dp)
                    .clickable {
                        coroutineScope.launch {
                            isAtTop.value = !isAtTop.value
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                Text(
                    text = if(!isAtTop.value) "Daha Fazlası.." else "Küçült..",
                    fontSize = 20.sp,   fontWeight = FontWeight.W800,
                    color = Color(255, 207, 184, 255),
                    textDecoration = TextDecoration.Underline
                )

                Icon(
                    imageVector = if (!isAtTop.value)Icons.Default.ArrowDropDownCircle else Icons.Default.ArrowCircleUp ,
                    contentDescription = "Sola gitme",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(20.dp))
            }
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
                        .width(120.dp)
                        .height(100.dp)
                        .padding(start = 16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = String.format("%.2f", oldPara) + "₺",
                            color = Color(100, 100, 100, 255),
                            fontSize = with(LocalDensity.current) { oldPriceFontSize.toSp() },
                            textDecoration = TextDecoration.LineThrough
                        )
                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        Text(
                            text = String.format("%.2f", para) + "₺",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = with(LocalDensity.current) { shoppingBarFontSize.toSp() },
                            fontWeight = FontWeight.Bold
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

                    Row(
                        modifier = Modifier
                            .width(120.dp)
                            .height(45.dp)
                            .border(
                                BorderStroke(
                                    2.dp, MaterialTheme.colorScheme.primary
                                ),
                                RoundedCornerShape(20.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Spacer(modifier = Modifier.weight(0.2f))
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    MaterialTheme.colorScheme.primary
                                )
                                .clickable {
                                    if (sepetSayisi.value > 1) {
                                        sepetSayisi.value -= 1
                                    }
                                },
                            contentAlignment = Alignment.CenterStart
                        ) {

                            Text(
                                text = "-",
                                fontSize = with(LocalDensity.current) { fontSizeIcon.toSp() },
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )

                        }
                        Spacer(modifier = Modifier.weight(0.5f))

                        Text(
                            text = sepetSayisi.value.toString(),
                            fontSize = with(LocalDensity.current) { fontSizeIcon.toSp() },
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Spacer(modifier = Modifier.weight(0.5f))
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .clickable {
                                    sepetSayisi.value += 1
                                },
                            contentAlignment = Alignment.CenterEnd
                        ) {

                            Text(
                                text = "+",
                                fontSize = with(LocalDensity.current) { fontSizeIcon.toSp() },
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )

                        }
                        Spacer(modifier = Modifier.weight(0.2f))

                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = {
                            val calendar = Calendar.getInstance()

                            val currentUserEmailBasket = Firebase.auth.currentUser?.email
                            val dataBasket = hashMapOf(
                                "oldPrice" to oldPara.toString(),
                                "price" to para.toString(),
                                "photo1" to photo1.value,
                                "discount" to discount.value,
                                "type" to type.value,
                                "title" to productTitle,
                                "amount" to sepetSayisi.value.toString(),
                                "email" to currentUserEmailBasket,
                                "date" to calendar.time,
                                "onay" to onay.value

                            )
                            if(currentUserEmailBasket != null) {
                                val docrefBasket = db.collection("basket")
                                docrefBasket.add(dataBasket)
                                    .addOnSuccessListener {
                                        Toast.makeText(context,"Ürün sepete eklendi.",Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(context,"Ürün eklenirken hata oluştu.",Toast.LENGTH_SHORT).show()
                                    }
                            }else {
                                Toast.makeText(context,"Oturum açmanız gerekli.",Toast.LENGTH_SHORT).show()
                            }
                        },
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
                            fontSize = with(LocalDensity.current) { oldPriceFontSize.toSp() },
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
private val disableScrolll = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource) = available.copy(x = 0f)
    override suspend fun onPreFling(available: Velocity) = available.copy(x = 0f)
}


fun Modifier.disabledVerticalPointerInputScroll(disabled: Boolean) =
    if (disabled) this.nestedScroll(disableScrolll) else this

