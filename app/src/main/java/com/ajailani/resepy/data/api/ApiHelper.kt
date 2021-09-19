package com.ajailani.resepy.data.api

import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getNewRecipes() = apiService.getNewRecipes()
    suspend fun getCategories() = apiService.getCategories()
}