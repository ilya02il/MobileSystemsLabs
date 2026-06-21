package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geoquiz.ui.theme.GeoQuizTheme

data class Question(val text: String, val answer: Boolean)

val questions = listOf(
    Question("Canberra is the capital of Australia.", true),
    Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
    Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
    Question("The source of the Nile River is in Egypt.", false),
    Question("The Amazon River is the longest river in the Americas.", true),
    Question("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizTheme {
                GeoQuizScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeoQuizScreen() {
    var currentIndex by remember { mutableIntStateOf(0) }
    var answered by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("GeoQuiz") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = questions[currentIndex].text,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (!answered) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        if (questions[currentIndex].answer) score++
                        answered = true
                    }) {
                        Text("TRUE")
                    }
                    Button(onClick = {
                        if (!questions[currentIndex].answer) score++
                        answered = true
                    }) {
                        Text("FALSE")
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (currentIndex < questions.lastIndex || !answered) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        currentIndex++
                        answered = false
                    }) {
                        Text("NEXT")
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.NavigateNext,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GeoQuizScreenPreview() {
    GeoQuizTheme {
        GeoQuizScreen()
    }
}
