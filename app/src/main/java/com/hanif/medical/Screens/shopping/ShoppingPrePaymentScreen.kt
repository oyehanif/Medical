package com.hanif.medical.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.hanif.medical.Screens.shopping.ShoppingSharedViewModel
import com.hanif.medical.Screens.shopping.shppingaddress.ShoppingAddressEvent
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.ShoppingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val getRandom = getRandomString()

@Composable
fun ShoppingPrePaymentScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ShoppingViewModel = hiltViewModel(),
    sharedViewModel: ShoppingSharedViewModel,
    onPopBackStack: () -> Unit
) {

    val model = sharedViewModel.shoppingProcessModel
    val shoppingItem = sharedViewModel.medicalModel
    Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = {  navController.popBackStack()},
            title = "Order Conformation"
        )
    }) { paddingValues ->
        val innerPaddings = paddingValues

        Column(Modifier.verticalScroll(rememberScrollState())) {
            Text(
                text = "PayWith",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            var selected by rememberSaveable { mutableStateOf("COD") }
            DisplayCheckGroup(selected) {
                selected = it
            }


            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp), shape = RoundedCornerShape(10)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            text = model!!.fullName,
                            Modifier.padding(vertical = 10.dp),
                            fontWeight = FontWeight.Normal,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "+91 ${model.mobileNumber}",
                            Modifier.padding(vertical = 10.dp),
                            fontWeight = FontWeight.Normal,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "NO.1 Bhotya Gali,Setan chok,kabristan ,1234",
                            Modifier.padding(vertical = 10.dp),
                            fontWeight = FontWeight.Normal,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 12.sp
                        )
                    }

                    /* Row(verticalAlignment = Alignment.CenterVertically) {
                         Image(Icons.Default.Edit, contentDescription = "", Modifier.size(12.dp))
                         Text(
                             text = "EDit",
                             Modifier.padding(end = 4.dp),
                             fontWeight = FontWeight.Normal,
                             fontFamily = DMSans,
                             fontSize = 12.sp
                         )
                     }*/
                }
            }

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
                            painter = rememberAsyncImagePainter(model = model?.medicineModel!!.image),
                            modifier = Modifier
                                .size(100.dp)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(10)),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Medicine"
                        )
                        Column() {
                            Text(
                                text = model.medicineModel.name,
                                Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontFamily = DMSans,
                                fontStyle = FontStyle.Normal,
                                maxLines = 3,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Rs. " + model.medicineModel.price.toString(),
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

            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp), shape = RoundedCornerShape(10)
            ) {
                Column() {
                    Text(
                        text = "Summary",
                        Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = DMSans,
                        fontStyle = FontStyle.Normal,
                        maxLines = 3,
                        fontSize = 16.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, modifier =
                        Modifier.padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Subtotal:",
                            Modifier.padding(10.dp),
                            fontWeight = FontWeight.Normal,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 16.sp
                        )
                        Log.e("TAG", "ShoppingPrePaymentScreen: ${model!!.qty}")
                        Text(
                            text = ((model.medicineModel!!.price.toInt()) * model.qty).toString(),
                            Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, modifier =
                        Modifier.padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Order Code:",
                            Modifier.padding(10.dp),
                            fontWeight = FontWeight.Normal,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 16.sp
                        )

                        Text(
                            text = getRandom,
                            Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, modifier =
                        Modifier.padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Shipping Fee:",
                            Modifier.padding(10.dp),
                            fontWeight = FontWeight.Normal,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Rs. 200",
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


            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier =
                Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Total:",
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Normal,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Normal,
                    maxLines = 3,
                    fontSize = 16.sp
                )
                Text(
                    text = ((model?.medicineModel!!.price.toInt() * model.qty) + 200).toString(),
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Normal,
                    maxLines = 3,
                    fontSize = 16.sp
                )
            }

            CommonButton(
                "Place Order", modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp), shape = RoundedCornerShape(50), onClick = {
                    sharedViewModel.addShoppingProcessModelValue(
                        model!!.copy(
                            paymentOption = selected,
                            orderDate = System.currentTimeMillis(),
                            orderID = getRandom
                        )
                    )
                    if (selected == "COD") {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.insertShoppingOrder(sharedViewModel.shoppingProcessModel!!)
                        }
                        onNavigate(UIEvent.Navigate(Routes.SHOPPING_SUCCESSFUL_SCREEN))
                    } else {
                        onNavigate(UIEvent.Navigate(Routes.SHOPPING_CARD_PAYMENT_SCREEN))
                    }
                })
        }
    }
}

fun getRandomString(): String {
    val allowedChars = ('A'..'Z') + ('0'..'9')
    return (1..8)
        .map { allowedChars.random() }
        .joinToString("")
}

@Composable
fun DisplayCheckGroup(selected: String, onValueChange: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            modifier = Modifier.padding(0.dp),
            checked = selected == "COD",
            onCheckedChange = { onValueChange("COD") })
        Text(
            text = "COD",
            modifier = Modifier
                .clickable(onClick = { onValueChange("COD") })
                .padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Checkbox(
            modifier = Modifier.padding(0.dp),
            checked = selected == "Credit/Debit Card",
            onCheckedChange = { onValueChange("Credit/Debit Card") })
        Text(
            text = "Credit/Debit Card",
            modifier = Modifier
                .clickable(onClick = { onValueChange("Credit/Debit Card") })
                .padding(start = 4.dp)
        )
    }
}


