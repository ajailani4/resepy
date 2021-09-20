package com.ajailani.resepy.ui.state

import com.ajailani.resepy.data.model.Category

sealed class CategoriesState {
    object Loading : CategoriesState()
    data class Success(val categories: List<Category>?) : CategoriesState()
    data class Error(val errorMessage: String?) : CategoriesState()
}