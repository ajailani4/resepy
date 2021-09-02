package com.ajailani.resepmakanan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ajailani.resepmakanan.ui.theme.ResepMakananTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                Content()
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    ResepMakananTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Content() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App {
        Content()
    }
}