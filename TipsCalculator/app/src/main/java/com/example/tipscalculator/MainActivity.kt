package com.example.tipscalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipscalculator.ui.theme.TipsCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipsCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipsCalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TipsCalculatorScreen(modifier: Modifier = Modifier) {
    var billAmount by remember { mutableStateOf("") }
    var dishCount by remember { mutableStateOf("") }
    var tipPercent by remember { mutableFloatStateOf(0f) }

    val discountPercent = when (dishCount.toIntOrNull() ?: 0) {
        in 1..2 -> 3
        in 3..5 -> 5
        in 6..10 -> 7
        else -> if ((dishCount.toIntOrNull() ?: 0) > 10) 10 else 0
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun TipsCalculatorScreenPreview() {
    TipsCalculatorTheme {
        TipsCalculatorScreen()
    }
}
