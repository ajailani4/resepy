package com.ajailani.resepy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ajailani.resepy.data.model.Category
import com.ajailani.resepy.data.model.response.BaseResponse
import com.ajailani.resepy.data.model.response.RecipeResponse
import com.ajailani.resepy.data.repository.MainRepository
import com.ajailani.resepy.ui.state.CategoriesState
import com.ajailani.resepy.ui.state.NewRecipesState
import com.ajailani.resepy.ui.viewmodel.HomeViewModel
import com.ajailani.resepy.util.generateCategories
import com.ajailani.resepy.util.generateRecipes
import com.ajailani.resepy.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private val pageLimit = 5

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Test
    fun getNewRecipes_ShouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            // Arrange
            doReturn(
                BaseResponse(
                    method = "GET",
                    status = true,
                    results = generateRecipes()
                )
            )
                .`when`(mainRepository)
                .getNewRecipes(pageLimit)

            val homeViewModel = HomeViewModel(mainRepository)

            // Act
            val newRecipesState = homeViewModel.newRecipesState
            var newRecipes = listOf<RecipeResponse>()

            when (newRecipesState) {
                is NewRecipesState.Loading -> {
                }

                is NewRecipesState.Success -> {
                    newRecipes = newRecipesState.recipes!!
                }

                is NewRecipesState.Error -> {
                }
            }

            // Assert
            assertNotNull("Should not null", newRecipes)
            assertEquals("Size should be $pageLimit", pageLimit, newRecipes.size)

            // Verify
            verify(mainRepository).getNewRecipes(pageLimit)
        }
    }

    @Test
    fun getCategories_ShouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            // Arrange
            doReturn(
                BaseResponse(
                    method = "GET",
                    status = true,
                    results = generateCategories()
                )
            )
                .`when`(mainRepository)
                .getCategories()

            val homeViewModel = HomeViewModel(mainRepository)

            // Act
            val categoriesState = homeViewModel.categoriesState
            var categories = listOf<Category>()

            when (categoriesState) {
                is CategoriesState.Loading -> {
                }
                is CategoriesState.Success -> {
                    categories = categoriesState.categories!!
                }
                is CategoriesState.Error -> {
                }
            }

            // Assert
            assertNotNull("Should not null", categories)
            assertEquals("Size should be 5", 5, categories.size)

            // Verify
            verify(mainRepository).getCategories()
        }
    }
}