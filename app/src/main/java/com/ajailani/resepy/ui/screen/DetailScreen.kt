package com.ajailani.resepy.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajailani.resepy.R
import com.ajailani.resepy.data.model.Author
import com.ajailani.resepy.data.model.Recipe
import com.ajailani.resepy.ui.component.AuthorCard
import com.ajailani.resepy.ui.component.IngredientItem
import com.ajailani.resepy.ui.component.TitleSection
import com.ajailani.resepy.ui.theme.poppinsFontFamily
import com.ajailani.resepy.util.generateRecipe
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowIosBack

@Composable
fun DetailScreen(
    recipe: Recipe
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        DetailHeader(
            thumb = recipe.thumb,
            author = recipe.author
        )
        ContentSection(recipe)
    }
}

@Composable
fun DetailHeader(
    thumb: String?,
    author: Author
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
    ) {
        Image(
            painter = /*rememberImagePainter(thumb)*/painterResource(id = R.drawable.recipe_thumb),
            contentDescription = "Recipe Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = { /*TODO*/ },
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
    Column(modifier = Modifier
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
        Spacer(modifier = Modifier.height(20.dp))
        TitleSection(
            title = "Bahan-bahan",
            info = "${recipe.ingredients.size} bahan"
        )

        recipe.ingredients.forEach { ingredient ->
            IngredientItem(ingredient)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(generateRecipe())
}