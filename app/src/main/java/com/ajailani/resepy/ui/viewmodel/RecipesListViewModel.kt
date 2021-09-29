package com.ajailani.resepy.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ajailani.resepy.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var title by mutableStateOf("")
    var key by mutableStateOf("")

    init {
        title = savedStateHandle.get<String>("title")!!
        key = savedStateHandle.get<String>("key")!!
    }

    fun getPaginatedNewRecipes() = mainRepository.getPaginatedNewRecipes().cachedIn(viewModelScope)
    fun getRecipesByCategory(key: String) = mainRepository.getRecipesByCategory(key).cachedIn(viewModelScope)
}