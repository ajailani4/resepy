package com.ajailani.resepy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.ajailani.resepy.data.model.Recipe
import com.ajailani.resepy.data.model.response.BaseResponse
import com.ajailani.resepy.data.repository.MainRepository
import com.ajailani.resepy.ui.state.RecipeState
import com.ajailani.resepy.ui.viewmodel.RecipeDetailViewModel
import com.ajailani.resepy.util.generateRecipe
import com.ajailani.resepy.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RecipeDetailViewModelTest {
    private val key = "resep-test"

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Test
    fun getDetail_ShouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            // Arrange
            doReturn(
                BaseResponse(
                    method = "GET",
                    status = true,
                    results = generateRecipe()
                )
            )
                .`when`(mainRepository)
                .getRecipeDetail(key)

            val savedStateHandle = SavedStateHandle().apply { set("key", key) }
            val recipeDetailViewModel = RecipeDetailViewModel(mainRepository, savedStateHandle)

            // Act
            val recipeState = recipeDetailViewModel.recipe
            var recipe = Recipe()

            when (recipeState) {
                is RecipeState.Loading -> { }
                is RecipeState.Success -> {
                    recipe = recipeState.recipe!!
                }
                is RecipeState.Error -> { }
            }

            // Assert
            assertNotNull("Should not null", recipe)

            // Verify
            verify(mainRepository).getRecipeDetail(key)
        }
    }

}