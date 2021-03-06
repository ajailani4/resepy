package com.ajailani.resepy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ajailani.resepy.data.api.ApiService
import com.ajailani.resepy.data.data_source.NewRecipesSource
import com.ajailani.resepy.data.data_source.RecipesByCategorySource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getNewRecipes(limit: Int) = apiService.getNewRecipes(limit).body()
    suspend fun getRecipeDetail(key: String) = apiService.getRecipeDetail(key).body()
    suspend fun getCategories() = apiService.getCategories().body()
    suspend fun getSearchedRecipes(searchQuery: String) =
        apiService.getSearchedRecipes(searchQuery).body()

    fun getPaginatedNewRecipes() =
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = {
                NewRecipesSource(apiService)
            }
        ).flow

    fun getRecipesByCategory(key: String) =
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = {
                RecipesByCategorySource(apiService, key)
            }
        ).flow
}