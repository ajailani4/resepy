package com.ajailani.resepy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ajailani.resepy.ui.screen.RecipeDetailScreen
import com.ajailani.resepy.ui.screen.HomeScreen
import com.ajailani.resepy.ui.screen.NewRecipesScreen
import com.ajailani.resepy.ui.screen.Screen
import com.ajailani.resepy.ui.state.CategoriesState
import com.ajailani.resepy.ui.state.NewRecipesState
import com.ajailani.resepy.ui.state.RecipeState
import com.ajailani.resepy.util.generateRecipe

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.RecipeDetailScreen.route + "?key={key}",
            arguments = listOf(
                navArgument("key") {
                    type = NavType.StringType
                }
            )
        ) {
            RecipeDetailScreen(navController)
        }

        composable(route = Screen.NewRecipesScreen.route) {
            NewRecipesScreen()
        }
    }
}