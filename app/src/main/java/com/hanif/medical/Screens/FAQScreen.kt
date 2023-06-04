package com.hanif.medical.Screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.utils.graphs.UIEvent

@Composable
fun FAQScreen(
        onPopBackStack: () -> Unit,
        onNavigate: (UIEvent.Navigate) -> Unit,
        navController: NavController,
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CommonAppBar(
                navigationIconAction = {navController.popBackStack() }, title = "FAQ's"
            )
        }) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(paddingValues)
        ) {
            Column {

                Text(
                    text = "how can we help",
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.secondary,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Spacer(modifier = Modifier.height(20.dp))

                //FAQ questions and answers section
                FAQListItem()
            }
        }
    }
}

@Composable
fun FAQListItem() {
    LazyColumn {
        items(faqListItem) { faq ->
            FAQItem(faq)
        }
    }
}

@Composable
fun FAQItem(faq: FAQ) {

    var isExpanded by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = faq.question,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                color = MaterialTheme.colors.secondary,
            )

            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = null,
                modifier = Modifier.clickable { isExpanded = !isExpanded },
                colorFilter = ColorFilter.tint(color =Color.Black)
            )

        }

        if (isExpanded) {
            Text(
                text = faq.answer,
                fontWeight = FontWeight(400),
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier
                    .width(360.dp)
                    .padding(bottom = 10.dp)
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}