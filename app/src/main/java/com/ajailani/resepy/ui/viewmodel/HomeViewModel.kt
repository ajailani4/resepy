package com.ajailani.resepy.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajailani.resepy.data.model.response.RecipeResponse
import com.ajailani.resepy.data.repository.MainRepository
import com.ajailani.resepy.ui.event.HomeEvent
import com.ajailani.resepy.ui.state.CategoriesState
import com.ajailani.resepy.ui.state.NewRecipesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var newRecipesState by mutableStateOf<NewRecipesState>(NewRecipesState.Loading)
    var categoriesState by mutableStateOf<CategoriesState>(CategoriesState.Loading)
    var searchText by mutableStateOf("")

    init {
        handleEvent(HomeEvent.GetNewRecipes)
        handleEvent(HomeEvent.GetCategories)
    }

    private fun handleEvent(homeEvent: HomeEvent) {
        try {
            when (homeEvent) {
                is HomeEvent.GetNewRecipes -> getNewRecipes()
                is HomeEvent.GetCategories -> getCategories()
            }
        } catch (e: Exception) {
            Log.e("HomeEvent", e.message!!)
        }
    }

    fun onSearchTextChanged(text: String) {
        searchText = text
    }

    private fun getNewRecipes() {
        viewModelScope.launch {
            newRecipesState = try {
                NewRecipesState.Success(
                    mainRepository.getNewRecipes(5).body()?.results
                )
            } catch (e: Exception) {
                NewRecipesState.Error(e.localizedMessage)
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            categoriesState = try {
                CategoriesState.Success(mainRepository.getCategories().body()?.results)
            } catch (e: Exception) {
                CategoriesState.Error(e.localizedMessage)
            }
        }
    }
}