package com.ajailani.resepy.data.model

import com.squareup.moshi.Json

data class Recipe(
    val title: String = "",
    val thumb: String? = null,
    val servings: String = "",
    val times: String = "",
    @field:Json(name = "dificulty")
    val difficulty: String = "",
    val author: Author = Author(),
    val desc: String = "",
    @field:Json(name = "ingredient")
    val ingredients: List<String> = listOf(),
    @field:Json(name = "step")
    val steps: List<String> = listOf()
)
