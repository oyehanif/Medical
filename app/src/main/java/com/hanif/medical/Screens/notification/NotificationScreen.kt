package com.matrixhive.subsalert.component.notification

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.utils.graphs.UIEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationScreen(onNavigate: (UIEvent.Navigate) -> Unit, navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        /*topBar = {
            CommonAppBar(title = "Notifications",

                 
                navigationIconAction = { }, actions = { }, actionText = "Clear all"
            )
        }*/
    ) {
        Surface(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            EmptyScreen()

            /*LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(20) {
                    NotificationItem(modifier)
                }
            }*/
        }
    }
}



@Composable
fun NotificationItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20))
          //  .background(Magnolia),

        ) {
       /* Image(
            painter = painterResource(id = R.drawable.netflix),
            modifier = modifier
                .size(60.dp)
                .padding(10.dp),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )*/
        Column(Modifier.padding(vertical = 10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Netflix \u2022 ",
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp, color = Color.Black
                )

                Text(
                    text = "Now",
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp, color = Color.Black
                )
            }
            Text(
                text = "This is a reminder that your membership with Netflix expired on 12 Jun, 2023 and you are now within your membership grace period.",
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Color.Blue
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EmptyScreen(
    title: String = "no_notifictations_yet",
    desc: String = "stringResource(R.string.empty_notification_desc)"
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        //Image(painter = painterResource(id = R.drawable.empty_list), contentDescription = "")

        Text(
            text = title,

            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = desc,

            fontWeight = FontWeight.Medium,
            fontSize = 12.sp, color = Color.Blue, textAlign = TextAlign.Center
        )
    }
}