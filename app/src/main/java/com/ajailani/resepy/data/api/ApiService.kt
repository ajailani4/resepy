package com.ajailani.resepy.data.api

import com.ajailani.resepy.data.model.response.BaseResponse
import com.ajailani.resepy.data.model.Category
import com.ajailani.resepy.data.model.response.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipes-length")
    suspend fun getNewRecipes(
        @Query("limit") limit: Int
    ): Response<BaseResponse<List<RecipeResponse>>>

    @GET("recipes/{slug}")
    suspend fun getRecipesByCategory(
        slug: String
    ): Response<BaseResponse<List<Category>>>

    @GET("categorys/recipes")
    suspend fun getCategories(): Response<BaseResponse<List<Category>>>
}