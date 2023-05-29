package com.hanif.medical.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.hanif.medical.R
import com.hanif.medical.Screens.shopping.ShoppingProcessModel
import com.hanif.medical.Screens.shopping.ShoppingSharedViewModel
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.HomeViewModel


@Composable
fun DetailShoppingScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    sharedViewModel: ShoppingSharedViewModel,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val medicineModel = sharedViewModel.medicalModel

    var qty by rememberSaveable { mutableStateOf(0) }

    medicineModel?.let {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column() {
                Image(
                    painter = rememberAsyncImagePainter(it.image),
                    contentDescription = "",
                    contentScale = ContentScale.Inside, modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(vertical = 10.dp)
                )


                Text(
                    text = it.name,
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 20.sp
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {

                    Text(
                        text = it.price.toString(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = DMSans,
                        fontStyle = FontStyle.Normal,
                        maxLines = 3,
                        fontSize = 16.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "4.8")
                        Image(Icons.Default.Star, contentDescription = "Star")
                        Text(text = "(4 Reviews)")
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(text = "Qty : ")
                    Spacer(modifier = Modifier.width(10.dp))

                    Image(
                        painterResource(id = R.drawable.baseline_horizontal_rule_24),
                        contentDescription = "Star", Modifier.clickable {
                            qty--
                        }
                    )

                    Text(text = qty.toString(), Modifier.padding(horizontal = 10.dp))

                    Image(
                        painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = "Star", Modifier.clickable {
                            qty++
                        }
                    )
                }

                Text(
                    text = it.desc,
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Normal,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Normal,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Reviews ",
                        Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = DMSans,
                        fontStyle = FontStyle.Italic,
                        maxLines = 3,
                        fontSize = 20.sp
                    )


                    Text(
                        text = "Read All",
                        Modifier.padding(10.dp),
                        fontWeight = FontWeight.Normal,
                        fontFamily = DMSans, fontStyle = FontStyle.Normal
                    )
                }
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                CommonButton(
                    text = "Buy Now", modifier = Modifier
                        .fillMaxWidth(.5f)
                        .padding(5.dp)
                ) {
                    sharedViewModel.addShoppingProcessModelValue(ShoppingProcessModel(qty = qty))
                    onNavigate(UIEvent.Navigate(Routes.SHOPPING_ADDRESS_SCREEN))
                }
                CommonButton(
                    text = "Add To card", modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {

                }
            }
        }
    }
}