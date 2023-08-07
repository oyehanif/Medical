@file:OptIn(ExperimentalMaterial3Api::class)

package com.hanif.medical.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.Screens.commo.CommonTextFiled
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent
import com.steliospapamichail.creditcardmasker.utils.CardType
import com.steliospapamichail.creditcardmasker.utils.getCardTypeFromNumber
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask
import com.steliospapamichail.creditcardmasker.viewtransformations.ExpirationDateMask


@Composable
fun CardPaymentScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
) {
    Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = {},
            title = "Payment"
        )
    }) { paddingValues ->
        val innerPaddings = paddingValues

        Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.SpaceBetween) {
            Column() {


                Text(
                    text = "Card Number",
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Normal,
                    maxLines = 3,
                    fontSize = 16.sp
                )

                CardNumber()

                Text(
                    text = "Name of card holder",
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Normal,
                    maxLines = 3,
                    fontSize = 16.sp
                )

                CommonTextFiled(hint = "Hanif",
                    modifier = Modifier.padding(horizontal = 10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth(.5f)
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Expire date",
                            Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 16.sp
                        )

                        Expiration(onValueChange = {})
                    }

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp)
                    ) {
                        Text(
                            text = "CVV",
                            Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = DMSans,
                            fontStyle = FontStyle.Normal,
                            maxLines = 3,
                            fontSize = 16.sp
                        )

                        CommonTextFiled(
                            hint = "xxx",
                            keyboardType = KeyboardType.NumberPassword
                        )
                    }
                }
                var isRemeber by rememberSaveable {
                    mutableStateOf(false)
                }
                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 10.dp)
                ) {
                    Checkbox(checked = isRemeber, onCheckedChange = { isRemeber = !isRemeber })

                    Text(
                        text = "Save my detail for next time?",
                        Modifier.align(Alignment.CenterVertically),
                        fontSize = 16.sp
                    )
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
                        text = "Rs.1200",
                        Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = DMSans,
                        fontStyle = FontStyle.Normal,
                        maxLines = 3,
                        fontSize = 16.sp
                    )
                }
            }

            CommonButton("Place Order", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp), shape = RoundedCornerShape(50), onClick = {

                onNavigate(UIEvent.Navigate(Routes.SHOPPING_SUCCESSFUL_SCREEN))

            })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expiration(expiration:String = "",onValueChange :(String) -> Unit) {
//    var expiration by remember { mutableStateOf("") }
    OutlinedTextField(
        value = expiration,
        visualTransformation = ExpirationDateMask(),
        onValueChange = {
            if (it.length <= 4) onValueChange(it)
        }, label = { Text("Expiration") }
    )
}

@Composable
fun CardNumber(number :String ="",onValueChange :(String) -> Unit={}) {

    OutlinedTextField(
        value = number,
        visualTransformation = CardNumberMask("-"),
        trailingIcon = {
            val iconRes = when (getCardTypeFromNumber(number)) {
                CardType.VISA -> R.drawable.visa
                CardType.MASTERCARD -> R.drawable.mastercard
                CardType.AMERICAN_EXPRESS -> R.drawable.american_express
                CardType.MAESTRO -> R.drawable.maestro
                CardType.DINNERS_CLUB -> R.drawable.dinners_club
                CardType.DISCOVER -> R.drawable.discover
                CardType.JCB -> R.drawable.discover
                else -> R.drawable.ic_launcher_background
            }
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "card_type",
                modifier = Modifier
                    .height(30.dp)
                    .width(40.dp)
                    .padding(end = 10.dp)
            )
        },
        onValueChange = {
            if (it.length <= 16) onValueChange(it)
        }, label = { Text("Card number") }
    )
}