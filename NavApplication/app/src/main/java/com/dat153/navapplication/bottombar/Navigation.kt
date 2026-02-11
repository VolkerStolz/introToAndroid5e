package com.dat153.navapplication.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute(val route: String) {
    @Serializable
    object Home : NavRoute("home")
    @Serializable
    object Profile : NavRoute("profile")
    @Serializable
    object Favorites : NavRoute("favorites")
    @Serializable
    class Details(val id: Int) : NavRoute("details/$id")
    @Serializable
    object Alert : NavRoute("alert")
}
data class BarItem(val title: String, val image: ImageVector, val navRoute: NavRoute, val route: String)
object NavBarItems {
    val BarItems = listOf<BarItem>(
        BarItem("Home", Icons.Filled.Home, NavRoute.Home, NavRoute.Home.route),
        BarItem("Profile", Icons.Filled.AccountCircle, NavRoute.Profile, NavRoute.Profile.route ),
        BarItem("Favorites", Icons.Filled.Favorite, NavRoute.Favorites, NavRoute.Favorites.route ),
    )
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar() {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.navRoute) {
                        // what happens if we comment this out?
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon =  { Icon(imageVector = navItem.image, contentDescription = navItem.title) },
                label = { Text(navItem.title) }
            )
        }
    }
}

