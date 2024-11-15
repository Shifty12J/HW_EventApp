package com.example.v2eventapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.v2eventapp.ui.theme.V2EventAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            V2EventAppTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home", // Initial screen
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomePage(navController)
                        }
                        composable("create_event") {
                            CreateEventPage(navController)
                        }
                        composable("event_page/{eventName}") { backStackEntry ->
                            val eventName = backStackEntry.arguments?.getString("eventName") ?: "No Event"
                            EventPage(eventName, navController)

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomePage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Occasion Creation",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Button(onClick = {
            navController.navigate("create_event")
        }) {
            Text(text = "Create Event")
        }
    }
}
fun shareEventDetails(
    eventName: String,
    eventDate: String?,
    startTime: String?,
    endTime: String?,
    location: String?,
    description: String?,
    itemsNeeded: String?,
    usefulLocations: String?,
    context: android.content.Context
) {
    val shareText = """
        Event: $eventName
        Date: ${eventDate ?: "Unknown Date"}
        Start Time: ${startTime ?: "Unknown Time"}
        End Time: ${endTime ?: "Unknown Time"}
        Location: ${location ?: "Unknown Location"}
        Description: ${description ?: "No Description"}
        Items Needed: ${itemsNeeded ?: "None"}
        Useful Locations Nearby: ${usefulLocations ?: "None"}
    """.trimIndent()

    val intent = android.content.Intent().apply {
        action = android.content.Intent.ACTION_SEND
        putExtra(android.content.Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    val chooser = android.content.Intent.createChooser(intent, "Share Event")
    context.startActivity(chooser)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    V2EventAppTheme {
        HomePage(rememberNavController())
    }
}
