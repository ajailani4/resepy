package com.ajailani.resepy.data.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ajailani.resepy.data.api.ApiService
import com.ajailani.resepy.data.model.response.RecipeResponse
import javax.inject.Inject

class NewRecipesSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, RecipeResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeResponse> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getPaginatedNewRecipes(currentLoadingPageKey)
            val data = response.body()?.results ?: emptyList()

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RecipeResponse>): Int? {
        return state.anchorPosition
    }
}