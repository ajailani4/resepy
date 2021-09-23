package com.ajailani.resepy.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajailani.resepy.data.model.Recipe
import com.ajailani.resepy.data.repository.MainRepository
import com.ajailani.resepy.ui.event.RecipeDetailEvent
import com.ajailani.resepy.ui.state.RecipeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var recipe by mutableStateOf<RecipeState>(RecipeState.Loading)

    init {
        handleIntent(RecipeDetailEvent.GetRecipeDetail)
    }

    private fun handleIntent(recipeDetailEvent: RecipeDetailEvent) {
        when (recipeDetailEvent) {
            is RecipeDetailEvent.GetRecipeDetail -> getDetail()
        }
    }

    private fun getDetail() {
        savedStateHandle.get<String>("key").let { key ->
            viewModelScope.launch {
                recipe = try {
                    RecipeState.Success(mainRepository.getRecipeDetail(key!!)?.results)
                } catch (e: Exception) {
                    RecipeState.Error(e.localizedMessage)
                }
            }
        }
    }
}