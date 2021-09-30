package com.ajailani.resepy.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.ajailani.resepy.ui.component.CategoryCard
import com.ajailani.resepy.ui.component.NewRecipeCard
import com.ajailani.resepy.ui.component.TitleSection
import com.ajailani.resepy.ui.state.CategoriesState
import com.ajailani.resepy.ui.state.NewRecipesState
import com.ajailani.resepy.ui.theme.DarkGray
import com.ajailani.resepy.ui.theme.Primary
import com.ajailani.resepy.ui.theme.SearchBackground
import com.ajailani.resepy.ui.theme.poppinsFontFamily
import com.ajailani.resepy.ui.viewmodel.HomeViewModel
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Search

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val searchText = homeViewModel.searchText
    val onSearchTextChanged = homeViewModel::onSearchTextChanged
    val newRecipesState = homeViewModel.newRecipesState
    val categoriesState = homeViewModel.categoriesState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()
        SearchTextField(
            navController = navController,
            searchText = searchText,
            onSearchTextChanged = onSearchTextChanged
        )
        NewRecipesSection(
            navController = navController,
            newRecipesState = newRecipesState
        )
        CategorySection(
            navController = navController,
            categoriesState = categoriesState
        )
    }
}

@Composable
fun HomeHeader() {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(
            text = "Halo",
            color = Primary,
            fontFamily = poppinsFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "Mau masak apa hari ini?",
            color = Color.Gray,
            fontFamily = poppinsFontFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    navController: NavController,
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 25.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        TextField(
            value = searchText,
            onValueChange = onSearchTextChanged,
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = EvaIcons.Fill.Search,
                    contentDescription = "Search Icon",
                    tint = DarkGray
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                navController.navigate(
                    Screen.RecipesListScreen.route + "?title=$searchText&searchQuery=$searchText"
                )
                keyboardController?.hide()
            }),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = SearchBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal
            ),
            placeholder = {
                Text(
                    text = "Cari Resep",
                    color = DarkGray,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@ExperimentalCoilApi
@Composable
fun NewRecipesSection(
    navController: NavController,
    newRecipesState: NewRecipesState
) {
    Column(modifier = Modifier.padding(bottom = 25.dp)) {
        TitleSection(
            title = "Resep Terbaru",
            isViewAllEnabled = true,
            modifier = Modifier.padding(horizontal = 20.dp),
            onClickViewAll = {
                navController.navigate(
                    Screen.RecipesListScreen.route + "?title=Resep Terbaru"
                )
            }
        )

        when (newRecipesState) {
            is NewRecipesState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
            }

            is NewRecipesState.Success -> {
                val recipes = newRecipesState.recipes

                if (recipes != null) {
                    LazyRow(contentPadding = PaddingValues(horizontal = 20.dp)) {
                        items(recipes) { recipe ->
                            NewRecipeCard(
                                recipe = recipe,
                                onClick = {
                                    navController.navigate(
                                        Screen.RecipeDetailScreen.route + "?key=${recipe.key}"
                                    )
                                }
                            )

                            if (recipe != recipes.last()) {
                                Spacer(modifier = Modifier.width(17.dp))
                            }
                        }
                    }
                }
            }

            is NewRecipesState.Error -> {
                Log.e("GetNewRecipes", newRecipesState.errorMessage!!)
            }
        }
    }
}

@Composable
fun CategorySection(
    navController: NavController,
    categoriesState: CategoriesState
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 20.dp)
    ) {
        TitleSection(title = "Kategori")

        when (categoriesState) {
            is CategoriesState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
            }

            is CategoriesState.Success -> {
                val categories = categoriesState.categories

                if (categories != null) {
                    for (category in categories) {
                        if (category.key == "dessert") {
                            category.key = "resep-dessert"
                        }

                        CategoryCard(
                            category = category,
                            onClick = {
                                navController.navigate(
                                    Screen.RecipesListScreen.route + "?title=${category.category}&key=${category.key}"
                                )
                            }
                        )

                        if (category != categories.last()) {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }

            is CategoriesState.Error -> {
                Log.e("GetCategories", categoriesState.errorMessage!!)
            }
        }
    }
}