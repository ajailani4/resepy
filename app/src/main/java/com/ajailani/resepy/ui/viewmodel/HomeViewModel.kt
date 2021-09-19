package com.ajailani.resepy.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajailani.resepy.data.repository.MainRepository
import com.ajailani.resepy.data.model.response.RecipeResponse
import com.ajailani.resepy.ui.event.HomeEvent
import com.ajailani.resepy.ui.viewstate.NewRecipesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var newRecipesState by mutableStateOf<NewRecipesState>(NewRecipesState.Loading)

    init {
        handleEvent(HomeEvent.GetNewRecipes)
    }

    private fun handleEvent(homeEvent: HomeEvent) {
        try {
            when (homeEvent) {
                is HomeEvent.GetNewRecipes -> getNewRecipes()
            }
        } catch (e: Exception) {
            Log.e("HomeEvent", e.message!!)
        }
    }

    private fun getNewRecipes() {
        viewModelScope.launch {
            newRecipesState = try {
                NewRecipesState.Success(mainRepository.getNewRecipes(5)?.results)
            } catch (e: Exception) {
                NewRecipesState.Error(e.localizedMessage)
            }
        }
    }
}