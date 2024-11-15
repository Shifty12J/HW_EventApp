package com.example.v2eventapp

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun CreateEventPage(navController: NavHostController) {
    var eventName by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var usefulLocations by remember { mutableStateOf("") }
    var itemsNeeded by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create Event", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))

        OutlinedTextField(
            value = eventName,
            onValueChange = { eventName = it },
            label = { Text("Event Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = eventDate,
            onValueChange = { eventDate = it },
            label = { Text("Event Date") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = startTime,
            onValueChange = { startTime = it },
            label = { Text("Start Time") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = endTime,
            onValueChange = { endTime = it },
            label = { Text("End Time") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = usefulLocations,
            onValueChange = { usefulLocations = it },
            label = { Text("Useful Locations Nearby") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = itemsNeeded,
            onValueChange = { itemsNeeded = it },
            label = { Text("Items Needed") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val context = navController.context
                val sharedPreferences = context.getSharedPreferences("EventPrefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().apply {
                    putString("eventName", eventName)
                    putString("eventDate", eventDate)
                    putString("startTime", startTime)
                    putString("endTime", endTime)
                    putString("location", location)
                    putString("description", description)
                    putString("usefulLocations", usefulLocations)
                    putString("itemsNeeded", itemsNeeded)
                    apply()
                }

                navController.navigate("event_page/$eventName")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Publish Event")
        }
    }
}
