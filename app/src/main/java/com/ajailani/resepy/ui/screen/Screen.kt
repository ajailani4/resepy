package com.ajailani.resepy.ui.screen

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object RecipeDetailScreen : Screen("recipe_detail_screen")
    object SearchScreen : Screen("search_screen")
    object RecipesListScreen : Screen("recipes_list_screen")
    object RecipesByCategoryScreen : Screen("recipes_by_category_screen")
}
