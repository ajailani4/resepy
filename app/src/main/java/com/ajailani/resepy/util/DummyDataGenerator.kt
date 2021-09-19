package com.ajailani.resepy.util

import com.ajailani.resepy.data.model.Author
import com.ajailani.resepy.data.model.Category
import com.ajailani.resepy.data.model.Recipe
import com.ajailani.resepy.data.model.response.RecipeResponse

fun generateRecipe() =
    RecipeResponse(
        title = "Resep Buntut Sapi Kuah Asam Padeh Khas Minang",
        thumb = "https://www.masakapahariini.com/wp-content/uploads/2019/09/shutterstock_586982774-780x440.jpg",
        key = "test",
        servings = "4 Porsi",
        times = "1jam",
        difficulty = "Medium"
    )

fun generateRecipes() =
    listOf(
        generateRecipe(),
        generateRecipe(),
        generateRecipe(),
        generateRecipe()
    )

fun generateCategory() =
    Category(
        category = "Dessert",
        key = "dessert"
    )

fun generateCategories() =
    listOf(
        generateCategory(),
        generateCategory(),
        generateCategory(),
        generateCategory()
    )