package com.ajailani.resepy.data.model

data class BaseResponse<T>(
    val method: String,
    val status: Boolean,
    val results: T
)