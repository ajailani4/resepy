package com.ajailani.resepy.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.ajailani.resepy.data.model.response.RecipeResponse
import com.ajailani.resepy.ui.component.RecipeCard
import com.ajailani.resepy.ui.theme.NewRecipesScreenBg
import com.ajailani.resepy.ui.theme.poppinsFontFamily
import com.ajailani.resepy.ui.viewmodel.RecipesListViewModel
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowIosBack

@Composable
fun RecipesListScreen(
    navController: NavController,
    recipesListViewModel: RecipesListViewModel = hiltViewModel()
) {
    val title = recipesListViewModel.title
    val key = recipesListViewModel.key
    var recipesList = recipesListViewModel.getPaginatedNewRecipes().collectAsLazyPagingItems()

    if (title != "Resep Terbaru") {
        recipesList = recipesListViewModel.getRecipesByCategory(key).collectAsLazyPagingItems()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        color = Color.Black,
                        fontFamily = poppinsFontFamily
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = EvaIcons.Fill.ArrowIosBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp
            )
        }
    ) {
        ContentSection(
            navController = navController,
            recipesList = recipesList
        )
    }
}

@Composable
fun ContentSection(
    navController: NavController,
    recipesList: LazyPagingItems<RecipeResponse>
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = NewRecipesScreenBg)
        .padding(horizontal = 20.dp)
    ) {
        LazyColumn(contentPadding = PaddingValues(vertical = 20.dp)) {
            if (recipesList.loadState.refresh == LoadState.Loading) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 200.dp)
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            itemsIndexed(recipesList) { _, item ->
                RecipeCard(
                    recipeResponse = item,
                    onClick = {
                        navController.navigate(
                            Screen.RecipeDetailScreen.route + "?key=${item?.key}"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            if (recipesList.loadState.append == LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}