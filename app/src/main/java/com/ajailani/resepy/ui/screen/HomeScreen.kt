package com.ajailani.resepy.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.ajailani.resepy.ui.component.CategoryCard
import com.ajailani.resepy.ui.component.NewRecipeCard
import com.ajailani.resepy.ui.component.TitleSection
import com.ajailani.resepy.ui.theme.poppinsFontFamily
import com.ajailani.resepy.ui.state.CategoriesState
import com.ajailani.resepy.ui.theme.SearchBackground
import com.ajailani.resepy.ui.theme.DarkGray
import com.ajailani.resepy.ui.theme.Primary
import com.ajailani.resepy.ui.state.NewRecipesState
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Search

@Composable
fun HomeScreen(
    newRecipesState: NewRecipesState,
    categoriesState: CategoriesState
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()
        SearchTextField()
        NewRecipesSection(newRecipesState)
        CategorySection(categoriesState)
    }
}

@Composable
fun HomeHeader() {
    Column(modifier = Modifier
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

@Composable
fun SearchTextField() {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 25.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = EvaIcons.Fill.Search,
                    contentDescription = "Search Icon",
                    tint = DarkGray
                )
            },
            singleLine = true,
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
    newRecipesState: NewRecipesState
) {
    Column(modifier = Modifier.padding(bottom = 25.dp)) {
        TitleSection(
            title = "Resep Terbaru",
            isViewAllEnabled = true,
            modifier = Modifier.padding(horizontal = 20.dp)
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
                            NewRecipeCard(recipe)

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
    categoriesState: CategoriesState
) {
    Column(modifier = Modifier
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
                        CategoryCard(category)

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