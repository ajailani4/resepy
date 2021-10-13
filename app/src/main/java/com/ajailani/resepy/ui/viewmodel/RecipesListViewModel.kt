package com.ajailani.resepy.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ajailani.resepy.data.repository.MainRepository
import com.ajailani.resepy.ui.event.RecipesListEvent
import com.ajailani.resepy.ui.state.SearchedRecipesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var title by mutableStateOf("")
    var key by mutableStateOf<String?>(null)
    var searchQuery by mutableStateOf<String?>(null)
    var searchedRecipes by mutableStateOf<SearchedRecipesState>(SearchedRecipesState.Loading)

    init {
        title = savedStateHandle.get<String>("title")!!
        key = savedStateHandle.get<String>("key")
        searchQuery = savedStateHandle.get<String>("searchQuery")
    }

    fun handleEvent(recipesListEvent: RecipesListEvent) {
        when (recipesListEvent) {
            is RecipesListEvent.GetSearchedRecipes -> getSearchedRecipes()
        }
    }

    fun getPaginatedNewRecipes() = mainRepository.getPaginatedNewRecipes().cachedIn(viewModelScope)

    fun getRecipesByCategory() = mainRepository.getRecipesByCategory(key!!).cachedIn(viewModelScope)

    private fun getSearchedRecipes() =
        viewModelScope.launch {
            searchedRecipes = try {
                SearchedRecipesState.Success(
                    mainRepository.getSearchedRecipes(searchQuery!!)?.results
                )
            } catch (e: Exception) {
                SearchedRecipesState.Error(e.localizedMessage)
            }
        }
}