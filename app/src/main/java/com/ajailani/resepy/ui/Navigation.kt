package com.ajailani.resepy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ajailani.resepy.ui.screen.DetailScreen
import com.ajailani.resepy.ui.screen.HomeScreen
import com.ajailani.resepy.ui.screen.Screen
import com.ajailani.resepy.ui.state.CategoriesState
import com.ajailani.resepy.ui.state.NewRecipesState
import com.ajailani.resepy.util.generateRecipe

@Composable
fun Navigation(
    navController: NavHostController,
    newRecipesState: NewRecipesState,
    categoriesState: CategoriesState
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                newRecipesState = newRecipesState,
                categoriesState = categoriesState
            )
        }

        composable(route = Screen.DetailScreen.route) {
            DetailScreen(generateRecipe())
        }
    }
}