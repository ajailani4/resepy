package com.ajailani.resepy.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajailani.resepy.ui.theme.poppinsFontFamily
import com.ajailani.resepy.util.generateRecipe

@Composable
fun IngredientItem(ingredient: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {
        var quantity = ingredient.split(" ")[0]
        val type: String
        var item: String

        if (quantity.toIntOrNull() != null || quantity.toFloatOrNull() != null) {
            type = ingredient.split(" ")[1]
            item = ingredient.substringAfter(type)
            item = item[1].uppercase() + item.substring(2)
        } else {
            quantity = ""
            type = ""
            item = ingredient[0].uppercase() + ingredient.substring(1)
        }

        Text(
            text = item,
            color = Color.Black,
            fontSize = 14.sp,
            fontFamily = poppinsFontFamily,
            modifier = Modifier.widthIn(max = 260.dp)
        )

        if (quantity.isNotEmpty()) {
            Text(
                text = "$quantity $type",
                color = Color.Gray,
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIngredientItem() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 15.dp, horizontal = 20.dp)
    ) {
        StepItem(generateRecipe().ingredients[2])
    }
}