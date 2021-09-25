package com.ajailani.resepy.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
import com.ajailani.resepy.ui.viewmodel.NewRecipesViewModel
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowIosBack

@Composable
fun NewRecipesScreen(
    navController: NavController,
    newRecipesViewModel: NewRecipesViewModel = hiltViewModel()
) {
    val paginatedNewRecipes = newRecipesViewModel.getPaginatedNewRecipes().collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Resep Terbaru",
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
            paginatedNewRecipes = paginatedNewRecipes
        )
    }
}

@Composable
fun ContentSection(
    navController: NavController,
    paginatedNewRecipes: LazyPagingItems<RecipeResponse>
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = NewRecipesScreenBg)
        .padding(horizontal = 20.dp)
    ) {
        LazyColumn(contentPadding = PaddingValues(vertical = 20.dp)) {
            if (paginatedNewRecipes.loadState.refresh == LoadState.Loading) {
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

            itemsIndexed(paginatedNewRecipes) { _, item ->
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

            if (paginatedNewRecipes.loadState.append == LoadState.Loading) {
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