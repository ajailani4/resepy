package com.ajailani.resepy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajailani.resepy.data.model.Recipe
import com.ajailani.resepy.data.model.response.RecipeResponse
import com.ajailani.resepy.ui.screen.HomeScreen
import com.ajailani.resepy.ui.screen.Screen
import com.ajailani.resepy.ui.viewstate.NewRecipesState

@Composable
fun Navigation(
    navController: NavHostController,
    newRecipesState: NewRecipesState
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                newRecipesState = newRecipesState
            )
        }
    }
}