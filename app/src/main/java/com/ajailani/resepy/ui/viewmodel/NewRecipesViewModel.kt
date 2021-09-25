package com.ajailani.resepy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ajailani.resepy.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewRecipesViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    fun getPaginatedNewRecipes() = mainRepository.getPaginatedNewRecipes().cachedIn(viewModelScope)
}