package com.ajailani.resepy.ui.event

sealed class RecipesListEvent {
    object GetSearchedRecipes : RecipesListEvent()
}