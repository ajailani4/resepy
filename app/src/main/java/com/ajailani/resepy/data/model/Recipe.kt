package com.ajailani.resepy.data.model

data class Recipe(
    val title: String,
    val thumb: String?,
    val servings: String,
    val time: String,
    val difficulty: String,
    val author: Author,
    val desc: String,
    val ingredient: List<String>,
    val step: List<String>
)
