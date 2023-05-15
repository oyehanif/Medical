package com.hanif.medical.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanif.medical.R
import com.matrixhive.subsalert.component.notification.EmptyScreen

@Composable
fun ShoppingScreen() {
    // EmptyScreen("Working Process", "Sorry But this service is not available Currently")

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(10) {
            ItemLayout()
        }
    }
}


@Preview
@Composable
fun ItemLayout() {
    Card(
        Modifier.padding(5.dp),
        shape = RoundedCornerShape(8),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.img_6),
                contentDescription = "",
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(text = "Benylin Dry and Ticky cough syrup 100 ML")

            Text(text = "Rs. 1000")
        }
    }
}