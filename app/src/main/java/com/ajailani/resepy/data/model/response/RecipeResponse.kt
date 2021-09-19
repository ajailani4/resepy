package com.ajailani.resepy.data.model.response

import com.squareup.moshi.Json

data class RecipeResponse(
    val title: String,
    val thumb: String?,
    val key: String,
    val times: String,
    @field:Json(name = "portion")
    val servings: String,
    @field:Json(name = "dificulty")
    val difficulty: String
)