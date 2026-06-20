package com.example.modifiersdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.modifiersdemo.ui.theme.ModifiersDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModifiersDemoTheme {

            }
        }
    }
}

@Composable
fun DemoScreen(modifier: Modifier = Modifier) {
    val customized = modifier
        .padding(all = 10.dp)
        .border(width = 2.dp, color = Color.Black)

    Text(
        "Hello Compose",
        fontSize = 40.sp,
        modifier = customized,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CustomImage(image: Int, modifier: Modifier) {
    Image(
        modifier = modifier,
        contentDescription = null,
        painter = painterResource(image)
    )
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    ModifiersDemoTheme {
        DemoScreen()
    }
}