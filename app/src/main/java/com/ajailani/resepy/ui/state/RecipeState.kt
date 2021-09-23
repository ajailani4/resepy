package com.ajailani.resepy.ui.state

import com.ajailani.resepy.data.model.Recipe

sealed class RecipeState {
    object Loading : RecipeState()
    data class Success(val recipe: Recipe?) : RecipeState()
    data class Error(val errorMessage: String?) : RecipeState()
}