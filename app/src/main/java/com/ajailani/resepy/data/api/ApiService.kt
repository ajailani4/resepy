package com.ajailani.resepy.data.api

import com.ajailani.resepy.data.model.Category
import com.ajailani.resepy.data.model.Recipe
import com.ajailani.resepy.data.model.response.BaseResponse
import com.ajailani.resepy.data.model.response.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes-length")
    suspend fun getNewRecipes(
        @Query("limit") limit: Int
    ): Response<BaseResponse<List<RecipeResponse>>>

    @GET("recipe/{key}")
    suspend fun getRecipeDetail(
        @Path("key") key: String
    ): Response<BaseResponse<Recipe>>

    @GET("recipes/{page}")
    suspend fun getPaginatedNewRecipes(
        @Path("page") page: Int
    ): Response<BaseResponse<List<RecipeResponse>>>

    @GET("categorys/recipes")
    suspend fun getCategories(): Response<BaseResponse<List<Category>>>

    @GET("categorys/recipes/{key}/{page}")
    suspend fun getRecipesByCategory(
        @Path("key") key: String,
        @Path("page") page: Int
    ): Response<BaseResponse<List<RecipeResponse>>>

    @GET("search")
    suspend fun getSearchedRecipes(
        @Query("q") searchQuery: String
    ): Response<BaseResponse<List<RecipeResponse>>>
}