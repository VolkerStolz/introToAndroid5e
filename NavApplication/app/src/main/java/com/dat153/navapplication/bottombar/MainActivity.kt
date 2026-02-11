package com.dat153.navapplication.bottombar

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.dat153.navapplication.bottombar.datatypes.Contact
import com.dat153.navapplication.ui.theme.NavApplicationTheme
import com.dat153.navapplication.bottombar.NavRoute.*


val contacts = listOf(
    Contact(0, "Ulises", "+54 (111) 231-4111"),
    Contact(1, "Bobby Babby", "+1 (555) 123-4567"),
    Contact(2, "Bob Bab", "+1 (555) 234-5678"),
    Contact(3, "Joe Doe", "+1 (555) 345-6789"),
)


class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            NavApplicationTheme {
                Scaffold(
                    topBar = { TopAppBar({ Text("Demo") }) },
                    content = {padding ->
                        NavigationHost(navController,Modifier.padding(padding))
                    },
                    bottomBar = {BottomNavigationBar(navController)}
                )
            }
        }
    }
}



@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeComponent(modifier,
                onContactPick = { id ->
                    navController.navigate(Details(id))
                },
                contacts = contacts
            )
        }
        composable<Profile> {ProfileComponent(modifier, 0, navController)}
        composable<Details> (
            deepLinks = listOf(
                navDeepLink<Details>(basePath = "dat153://navapp/details"))
            )
            { backStackEntry ->
            val details: Details = backStackEntry.toRoute()
            DetailsComponent(modifier, details.id, navController)
        }
        composable<Favorites> {FavoriteComponent(modifier)}
        dialog<Alert> {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(
                    text = "The developer hasn't implemented this yet!",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

