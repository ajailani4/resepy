package com.ajailani.resepy.data.repository

import com.ajailani.resepy.data.api.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getNewRecipes(limit: Int) = apiService.getNewRecipes(limit).body()
    suspend fun getCategories() = apiService.getCategories()
}