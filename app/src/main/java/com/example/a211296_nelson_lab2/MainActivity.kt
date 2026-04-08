package com.example.a211296_nelson_lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a211296_nelson_lab2.ui.theme.A211296_Nelson_Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A211296_Nelson_Lab2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNav()
                    }
                ) { padding ->
                    MainScreen(padding)
                }
            }
        }
    }
}

@Composable
fun MainScreen(padding: PaddingValues) {

    var location by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Please enter your location ") }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgrounds),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            TopSection(
                location = location,
                status = status,
                onLocationChange = { location = it },
                onStatusChange = { status = it }
            )

            StatsCard()
            FeatureRow()
            HighlightSection()
            EventSection()
        }
    }
}

@Composable
fun TopSection(
    location: String,
    status: String,
    onLocationChange: (String) -> Unit,
    onStatusChange: (String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF00897B))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "RecycleMate",
                color = Color(0xFFFFF59D),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )


            Column(
                horizontalAlignment = Alignment.End

            ) {
                TextField(
                    value = location,
                    onValueChange = {

                        onLocationChange(it)

                        if (it.isBlank()) {
                            onStatusChange("Please enter your location")
                        }
                    },
                    label = { Text("Location") },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .width(130.dp)
                        .height(56.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {

                        val input = location.lowercase()

                        val result = if (input.isBlank()) {
                            "Please enter your location"
                        } else if (
                            input.contains("kolej pendeta za'ba") ||
                            input.contains("kolej keris mas") ||
                            input.contains("kolej ibrahim yaakub") ||
                            input.contains("kolej burhanuddin helmi") ||
                            input.contains("kolej aminuddin baki") ||
                            input.contains("kolej rahim kajai") ||
                            input.contains("kolej ungku omar") ||
                            input.contains("kolej tun hussein onn") ||
                            input.contains("kolej 13") ||
                            input.contains("kolej dato' onn") ||
                            input.contains("kolej ibu zain") ||
                            input.contains("kolej tun syed nasir") ||
                            input.contains("kolej tun dr. ismail")
                        ) {
                            "♻️ Pickup available at $location"
                        } else {
                            "⚠️ Sorry, we only cover UKM college area."
                        }

                        onStatusChange(result)
                    },
                    modifier = Modifier
                        .width(90.dp)
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFF59D)
                    )
                ) {
                    Text("Check", color = Color(0xFF00695C),  fontWeight = FontWeight.SemiBold)
                }
            }
        }



        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Hi, Dina.",
            color = Color(0xFFFFFFFF),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontFamily = FontFamily.Serif

            )
        )
        Text(
            text = "Current selected location: $location",
            color = Color.White
        )

        Text(
            text = status,
            color = Color(0xFFFFF59D),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Consume wisely, care for tomorrow ♻\uFE0F",
            color = Color(0xFFFFFFFF)
        )

        Spacer(modifier = Modifier.height(13.dp))

        Row {
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFF59D)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Request Pickup", color = Color(0xFF00695C))
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFF59D)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Recycle Now", color = Color(0xFF00695C))
            }
        }
    }
}
@Composable
fun StatsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "0",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFF00695C) // KEKAL
                    )
                )
                Text(
                    "Waste prevented (KG)",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFF616161) // KEKAL
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "RM 0",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFF00695C) // KEKAL
                    )
                )
                Text(
                    "Value recovered",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFF616161) // KEKAL
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun FeatureRow() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                FeatureItem("Recycle Centre", Icons.Default.Place)
                FeatureItem("Eco Calculator", Icons.Default.Calculate)
                FeatureItem("Connect", imageRes = R.drawable.tng)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                FeatureItem("Feedback", Icons.Default.Email)
                FeatureItem("Report", Icons.Default.Warning)
                FeatureItem("Bulk Request", Icons.Default.ShoppingCart)
                FeatureItem("Event", Icons.Default.DateRange)
            }
        }
    }
}

@Composable
fun FeatureItem(title: String, icon: ImageVector? = null, imageRes: Int? = null) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        if (imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.height(24.dp)
            )
        } else if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF424242)
            )
        }

        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = Color(0xFF424242)
        )
    }
}

@Composable
fun HighlightSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Highlight",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF00695C),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))


        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            PosterItem(R.drawable.posters2)
            PosterItem(R.drawable.posters1)

        }
    }
}

@Composable
fun PosterItem(imageRes: Int) {
    Card(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(250.dp)
            .height(180.dp),
        border = BorderStroke(2.dp, Color(0xFF00695C))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Poster",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun EventSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Events",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF00695C),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            EventItem(R.drawable.event1)
            EventItem(R.drawable.event2)
            EventItem(R.drawable.event3)
        }
    }
}

@Composable
fun EventItem(imageRes: Int) {
    Card(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(250.dp)
            .height(180.dp),
        border = BorderStroke(2.dp, Color(0xFF00695C))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Event Poster",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun BottomNav() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF00897B))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Home, contentDescription = "Home")
            Text("Home", color = Color.White)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Add, contentDescription = "Scan")
            Text("Scan", color = Color.White)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.List, contentDescription = "Transactions")
            Text("Transactions", color = Color.White)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Person, contentDescription = "Profile")
            Text("Profile", color = Color.White)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    A211296_Nelson_Lab2Theme {

    }
}