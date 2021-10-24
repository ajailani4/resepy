package com.ajailani.resepy.ui.screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ajailani.resepy.data.model.Author
import com.ajailani.resepy.data.model.Recipe
import com.ajailani.resepy.ui.component.AuthorCard
import com.ajailani.resepy.ui.component.IngredientItem
import com.ajailani.resepy.ui.component.StepItem
import com.ajailani.resepy.ui.component.TitleSection
import com.ajailani.resepy.ui.state.RecipeState
import com.ajailani.resepy.ui.theme.poppinsFontFamily
import com.ajailani.resepy.ui.viewmodel.RecipeDetailViewModel
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowIosBack

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    recipeDetailViewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val recipeState = recipeDetailViewModel.recipe

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        when (recipeState) {
            is RecipeState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 200.dp)
                ) {
                    CircularProgressIndicator()
                }
            }

            is RecipeState.Success -> {
                val recipe = recipeState.recipe!!

                DetailHeader(
                    navController = navController,
                    thumb = recipe.thumb,
                    author = recipe.author
                )
                ContentSection(recipe)
            }

            is RecipeState.Error -> {
                Log.e("Recipe", recipeState.errorMessage!!)
            }
        }
    }
}

@Composable
fun DetailHeader(
    navController: NavController,
    thumb: String?,
    author: Author
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = rememberImagePainter(thumb),
            contentDescription = "Recipe Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .padding(20.dp)
                .size(30.dp)
                .border(1.5.dp, Color.White.copy(0.7f), CircleShape)
        ) {
            Icon(
                imageVector = EvaIcons.Fill.ArrowIosBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            AuthorCard(author)
        }
    }
}

@Composable
fun ContentSection(recipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = recipe.title,
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${recipe.times} | ${recipe.servings}",
            color = Color.Gray,
            fontSize = 14.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(25.dp))
        IngredientsSection(recipe.ingredients)
        StepsSection(recipe.steps)
    }
}

@Composable
fun IngredientsSection(ingredients: List<String>) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        TitleSection(
            title = "Bahan-bahan",
            info = "${ingredients.size} bahan"
        )
        Spacer(modifier = Modifier.height(5.dp))

        ingredients.forEach { ingredient ->
            IngredientItem(ingredient)
        }
    }
}

@Composable
fun StepsSection(steps: List<String>) {
    TitleSection(
        title = "Langkah-langkah"
    )
    Spacer(modifier = Modifier.height(5.dp))

    steps.forEach { step ->
        StepItem(step)
    }
}