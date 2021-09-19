package com.ajailani.resepy.data.api

import com.ajailani.resepy.data.model.BaseResponse
import com.ajailani.resepy.data.model.Category
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("recipes")
    suspend fun getNewRecipes(): Response<BaseResponse<List<Category>>>

    @GET("recipes/{slug}")
    suspend fun getRecipesByCategory(
        slug: String
    ): Response<BaseResponse<List<Category>>>

    @GET("categorys/recipes")
    suspend fun getCategories(): Response<BaseResponse<Category>>
}