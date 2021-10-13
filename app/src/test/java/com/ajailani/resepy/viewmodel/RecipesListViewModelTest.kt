package com.ajailani.resepy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ajailani.resepy.data.api.ApiService
import com.ajailani.resepy.data.data_source.NewRecipesSource
import com.ajailani.resepy.data.data_source.RecipesByCategorySource
import com.ajailani.resepy.data.model.response.BaseResponse
import com.ajailani.resepy.data.model.response.RecipeResponse
import com.ajailani.resepy.data.repository.MainRepository
import com.ajailani.resepy.ui.event.RecipesListEvent
import com.ajailani.resepy.ui.state.SearchedRecipesState
import com.ajailani.resepy.ui.viewmodel.RecipesListViewModel
import com.ajailani.resepy.util.generateRecipes
import com.ajailani.resepy.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertNotNull
import org.junit.Before
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
class RecipesListViewModelTest {
    private val title = "Test"
    private val key = "test"
    private val searchQuery = "test-query"

    private val testCoroutineScope = TestCoroutineScope()
    private var savedStateHandle = SavedStateHandle()

    private lateinit var recipesListViewModel: RecipesListViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var mainRepository: MainRepository

    @Before
    fun setUp() {
        savedStateHandle = SavedStateHandle().apply {
            set("title", title)
            set("key", key)
            set("searchQuery", searchQuery)
        }
        recipesListViewModel = RecipesListViewModel(mainRepository, savedStateHandle)
    }

    @Test
    fun getPaginatedNewRecipes_ShouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            // Arrange
            doReturn(
                Pager(
                    config = PagingConfig(enablePlaceholders = false, pageSize = 10),
                    pagingSourceFactory = {
                        NewRecipesSource(apiService)
                    }
                ).flow.cachedIn(testCoroutineScope)
            )
                .`when`(mainRepository)
                .getPaginatedNewRecipes()

            // Act
            val recipes = recipesListViewModel.getPaginatedNewRecipes()

            // Assert
            assertNotNull("Should not null", recipes)

            // Verify
            verify(mainRepository).getPaginatedNewRecipes()
        }
    }

    @Test
    fun getRecipesByCategory_ShouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            // Arrange
            doReturn(
                Pager(
                    config = PagingConfig(enablePlaceholders = false, pageSize = 10),
                    pagingSourceFactory = {
                        RecipesByCategorySource(apiService, key)
                    }
                ).flow.cachedIn(testCoroutineScope)
            )
                .`when`(mainRepository)
                .getRecipesByCategory(key)

            // Act
            val recipes = recipesListViewModel.getRecipesByCategory()

            // Assert
            assertNotNull("Should not null", recipes)

            // Verify
            verify(mainRepository).getRecipesByCategory(key)
        }
    }

    @Test
    fun getSearchedRecipes_ShouldReturnSuccess() {
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
                .getSearchedRecipes(searchQuery)

            // Act
            recipesListViewModel.handleEvent(RecipesListEvent.GetSearchedRecipes)

            val searchedRecipesState = recipesListViewModel.searchedRecipes
            var searchedRecipes = listOf<RecipeResponse>()

            when (searchedRecipesState) {
                is SearchedRecipesState.Loading -> {
                }

                is SearchedRecipesState.Success -> {
                    searchedRecipes = searchedRecipesState.searchedRecipes!!
                }

                is SearchedRecipesState.Error -> {
                }
            }

            // Assert
            assertNotNull("Should not null", searchedRecipes)

            // Verify
            verify(mainRepository).getSearchedRecipes(searchQuery)
        }
    }
}