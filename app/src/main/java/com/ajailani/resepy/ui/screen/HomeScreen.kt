package com.ajailani.resepy.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajailani.resepy.ui.component.CategoryCard
import com.ajailani.resepy.ui.component.NewRecipeCard
import com.ajailani.resepy.ui.component.SectionTitle
import com.ajailani.resepy.ui.component.fontFamily
import com.ajailani.resepy.ui.theme.BackgroundGray
import com.ajailani.resepy.ui.theme.DarkGray
import com.ajailani.resepy.ui.theme.Primary
import com.ajailani.resepy.util.generateCategories
import com.ajailani.resepy.util.generateRecipes
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Search

@Composable
fun HomeScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        Header()
        SearchTextField()
        NewRecipesSection()
        CategorySection()
    }
}

@Composable
fun Header() {
    Column(modifier = Modifier
        .padding(20.dp)
    ) {
        Text(
            text = "Hello",
            color = Primary,
            fontFamily = fontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "What you want to cook today?",
            color = Color.Gray,
            fontFamily = fontFamily,
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
                backgroundColor = BackgroundGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            ),
            placeholder = {
                Text(
                    text = "Search Recipes",
                    color = DarkGray,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun NewRecipesSection() {
    val dummyNewRecipes = generateRecipes()

    Column(modifier = Modifier.padding(bottom = 25.dp)) {
        SectionTitle(
            title = "New Recipes",
            isViewAllEnabled = true,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        LazyRow(contentPadding = PaddingValues(horizontal = 20.dp)) {
            items(dummyNewRecipes) { recipe ->
                NewRecipeCard(recipe)
                Spacer(modifier = Modifier.width(17.dp))

                /*if (recipe != dummyNewRecipes.last()) {
                    Spacer(modifier = Modifier.width(17.dp))
                }*/
            }
        }
    }
}

@Composable
fun CategorySection() {
    val dummyCategories = generateCategories()
    
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        SectionTitle(title = "Category")
        for (category in dummyCategories) {
            CategoryCard(category)
            Spacer(modifier = Modifier.height(17.dp))

            /*if (category != dummyCategories.last()) {
                Spacer(modifier = Modifier.width(20.dp))
            }*/
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}