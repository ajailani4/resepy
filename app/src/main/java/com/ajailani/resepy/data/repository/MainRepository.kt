package com.ajailani.resepy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ajailani.resepy.data.api.ApiService
import com.ajailani.resepy.data.data_source.NewRecipesSource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getNewRecipes(limit: Int) = apiService.getNewRecipes(limit)
    suspend fun getRecipeDetail(key: String) = apiService.getRecipeDetail(key)

    fun getPaginatedNewRecipes() =
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = {
                NewRecipesSource(apiService)
            }
        ).flow

    suspend fun getCategories() = apiService.getCategories()
}