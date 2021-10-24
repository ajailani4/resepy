package com.ajailani.resepy.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajailani.resepy.ui.theme.Primary
import com.ajailani.resepy.ui.theme.poppinsFontFamily

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    title: String,
    info: String? = null,
    isViewAllEnabled: Boolean = false,
    onClickViewAll: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(bottom = 15.dp)
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontFamily = poppinsFontFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )

        if (info != null) {
            Text(
                text = info,
                color = Primary,
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        if (isViewAllEnabled) {
            Text(
                text = "View All",
                color = Color.Gray,
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    if (onClickViewAll != null) {
                        onClickViewAll()
                    }
                }
            )
        }
    }
}