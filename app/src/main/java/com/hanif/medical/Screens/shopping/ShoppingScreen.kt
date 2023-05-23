package com.hanif.medical.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.Screens.shopping.ShoppingSharedViewModel
import com.hanif.medical.repository.MedicineModel
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.HomeViewModel

@Composable
fun ShoppingScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    sharedViewModel: ShoppingSharedViewModel,
    viewModel: HomeViewModel = hiltViewModel()
) {
    // EmptyScreen("Working Process", "Sorry But this service is not available Currently")
    val state = viewModel.medicineState
    androidx.compose.material.Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = { /*TODO*/ },
            title = "Shopping Item's"
        )
    }) {
        val innerPAdding = it
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(state.companies.size) { i ->
                val medicine = state.companies[i]
                ItemLayout(medicine) {
                    sharedViewModel.addMedicalValue(medicine)
                    onNavigate(UIEvent.Navigate(route = Routes.SHOPPING_DETAIL_SCREEN))
                }
            }
        }
    }
}


@Composable
fun ItemLayout(item: MedicineModel, onClick: () -> Unit = {}) {
    Card(
        Modifier
            .padding(5.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column() {
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = "",
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )


            androidx.compose.material.Text(
                text = item.name, Modifier.padding(
                    10.dp
                ), maxLines = 4, overflow = TextOverflow.Ellipsis, minLines = 4
            )

            Text(
                text = "Price :- " + item.price.toString(),
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
            )
        }
    }
}