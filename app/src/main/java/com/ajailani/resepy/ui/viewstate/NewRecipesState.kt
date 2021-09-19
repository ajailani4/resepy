package com.ajailani.resepy.ui.viewstate

import com.ajailani.resepy.data.model.response.RecipeResponse

sealed class NewRecipesState {
    object Loading : NewRecipesState()
    data class Success(val recipes: List<RecipeResponse>?) : NewRecipesState()
    data class Error(val message: String?) : NewRecipesState()
}