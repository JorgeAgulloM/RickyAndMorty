package org.example.rickyandmorty.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.example.rickyandmorty.ui.core.BackgroundPrimaryColor
import org.example.rickyandmorty.ui.core.BackgroundSecondaryColor
import org.example.rickyandmorty.ui.core.BackgroundTertiaryColor
import org.example.rickyandmorty.ui.core.DefaultTextColor
import org.example.rickyandmorty.ui.core.Green
import org.example.rickyandmorty.ui.core.navigation.buttonnavigation.BottomBarItem
import org.example.rickyandmorty.ui.core.navigation.buttonnavigation.NavigationBottomWrapper
import org.jetbrains.compose.resources.painterResource
import rickyandmorty.composeapp.generated.resources.Res
import rickyandmorty.composeapp.generated.resources.ricktoolbar

@Composable
fun HomeScreen(mainNavController: NavHostController) {

    val items = listOf(BottomBarItem.Episodes(), BottomBarItem.Characters())
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = { BottomNavigation(items, navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            NavigationBottomWrapper(navController, mainNavController)
        }
    }
}

@Composable
fun TopBar() {
    Box(modifier = Modifier.fillMaxWidth().background(color = BackgroundPrimaryColor), contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(Res.drawable.ricktoolbar),
            contentDescription = "Top bar",
            modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = BackgroundSecondaryColor, contentColor = Green
    ) {
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Green,
                    selectedIconColor = BackgroundTertiaryColor,
                    unselectedIconColor = Green
                ),
                onClick = {
                    navController.navigate(route = item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = item.icon,
                label = { Text(text = item.title, color = DefaultTextColor) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                alwaysShowLabel = false
            )
        }
    }
}
