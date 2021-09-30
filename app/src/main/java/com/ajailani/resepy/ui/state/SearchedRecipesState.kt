package com.ajailani.resepy.ui.state

import com.ajailani.resepy.data.model.response.RecipeResponse

sealed class SearchedRecipesState {
    object Loading : SearchedRecipesState()
    data class Success(var searchedRecipes: List<RecipeResponse>?) : SearchedRecipesState()
    data class Error(var errorMessage: String?) : SearchedRecipesState()
}
