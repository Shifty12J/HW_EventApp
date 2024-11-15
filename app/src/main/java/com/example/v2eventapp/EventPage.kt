package com.example.v2eventapp

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun EventPage(eventName: String, navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("EventPrefs", Context.MODE_PRIVATE)

    // Load data
    val eventDate = sharedPreferences.getString("eventDate", "Unknown Date")
    val startTime = sharedPreferences.getString("startTime", "Unknown Time")
    val endTime = sharedPreferences.getString("endTime", "Unknown Time")
    val location = sharedPreferences.getString("location", "Unknown Location")
    val description = sharedPreferences.getString("description", "No Description")
    val itemsNeeded = sharedPreferences.getString("itemsNeeded", "None")
    val usefulLocations = sharedPreferences.getString("usefulLocations", "None")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                text = eventName,
                style = TextStyle(fontSize = 24.sp, color = Color(0xFF388E3C)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textAlign = TextAlign.Center
            )
        },
        containerColor = Color(0xFF616161)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            BoxedText(label = "Date:", value = eventDate)
            BoxedText(label = "Start Time:", value = startTime)
            BoxedText(label = "End Time:", value = endTime)
            BoxedText(label = "Location:", value = location)
            BoxedText(label = "Description:", value = description)
            BoxedText(label = "Items Needed:", value = itemsNeeded)
            BoxedText(label = "Useful Locations Nearby:", value = usefulLocations)

            Button(
                onClick = {
                    shareEventDetails(
                        eventName = eventName,
                        eventDate = eventDate,
                        startTime = startTime,
                        endTime = endTime,
                        location = location,
                        description = description,
                        itemsNeeded = itemsNeeded,
                        usefulLocations = usefulLocations,
                        context = context
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            ) {
                Text(text = "Share Event", color = Color.White)
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            ) {
                Text(text = "Back")
            }
        }
    }
}

@Composable
fun BoxedText(label: String, value: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(color = Color(0xFFE0E0E0))
    ) {
        Text(
            text = "$label $value",
            style = TextStyle(fontSize = 18.sp, color = Color(0xFF000000)),
            modifier = Modifier.padding(8.dp)
        )
    }
}
