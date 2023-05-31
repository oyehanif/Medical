package com.hanif.medical.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.Screens.shopping.ShoppingProcessModel
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.ShoppingViewModel
import java.util.Date

@Composable
fun ShoppingOrdersScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ShoppingViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                /*is UIEvent.PopBacKStackWithDestination -> {
                    onPopBackStack(event)
                }*/
                is UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message, actionLabel = event.action
                    )
                }

                is UIEvent.Navigate -> {
                    onNavigate(event)
                }

                else -> Unit
            }
        }
    }

    Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = {},
            title = "Shopping Address"
        )
    }) { paddingValues ->

        val innerPaddings = paddingValues

        val state = viewModel.state

        LazyColumn(){
            items(state.companies){
                 /*{
                    onNavigate(UIEvent.Navigate(Routes.DETAIL_DOCTOR_SCREEN))
                }*/
            }
            items(state.companies){
                OrderItem(it)
            }
        }
    }
}


@Composable
fun OrderItem(i: ShoppingProcessModel) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(10.dp), shape = RoundedCornerShape(10)
    ) {
        Column() {
            Text(
                text = "Medically Pharmacy",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            Row {
                Image(
                    painter = rememberAsyncImagePainter(model = i.medicineModel!!.image),
                    modifier = Modifier
                        .size(100.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10)),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Medicine"
                )
                Column() {
                    Text(
                        text = i.fullName,
                        Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = DMSans,
                        fontStyle = FontStyle.Normal,
                        maxLines = 3,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "RS " + i.medicineModel.price,
                        Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = DMSans,
                        fontStyle = FontStyle.Normal,
                        maxLines = 3,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Buying Date :- ",//${Date(i)}.",
                        Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = DMSans,
                        fontStyle = FontStyle.Normal,
                        maxLines = 3,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


