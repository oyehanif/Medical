package com.hanif.medical.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hanif.medical.R
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.utils.graphs.UIEvent

@Composable
fun OrderSuccessfulScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ordersuccessfullanimation))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
        )

        Text(
            text = "Thanks for your Order. You have successfully purchased! 🥳",
            Modifier.padding(20.dp),
            fontWeight = FontWeight.Medium,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal, textAlign = TextAlign.Center,
            maxLines = 3,
            fontSize = 14.sp
        )
    }
}