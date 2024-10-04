package org.example.rickyandmorty.ui.core.navigation.buttonnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.rickyandmorty.ui.core.navigation.Routes
import org.example.rickyandmorty.ui.home.tabs.characters.CharactersScreen
import org.example.rickyandmorty.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Episodes.route) {
        composable(route = Routes.Episodes.route) {
            EpisodesScreen()
        }
        composable(route = Routes.Characters.route) {
            CharactersScreen()
        }
    }
}
