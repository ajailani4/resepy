package com.ajailani.resepy.ui.screen

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object DetailScreen : Screen("detail_screen")
    object SearchScreen : Screen("search_screen")
    object FavoritesScreen : Screen("favorites_screen")
}
