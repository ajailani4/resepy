package com.ajailani.resepy.data.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ajailani.resepy.data.api.ApiService
import com.ajailani.resepy.data.model.response.RecipeResponse
import javax.inject.Inject

class RecipesByCategorySource @Inject constructor(
    private val apiService: ApiService,
    private val key: String
) : PagingSource<Int, RecipeResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeResponse> {
        return try {
            val currentLoadingPage = params.key ?: 1
            val response = apiService.getRecipesByCategory(key, currentLoadingPage)
            val data = response.body()?.results ?: emptyList()

            val prevKey = if (currentLoadingPage == 1) null else currentLoadingPage - 1

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = currentLoadingPage.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RecipeResponse>): Int? {
        return state.anchorPosition
    }
}