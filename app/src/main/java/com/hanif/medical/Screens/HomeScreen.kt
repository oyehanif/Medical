package com.hanif.medical.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hanif.medical.models.OnBoardingPage

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Medical Home Screen", fontSize = MaterialTheme.typography.h4.fontSize)
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeScreen()
    }
}
