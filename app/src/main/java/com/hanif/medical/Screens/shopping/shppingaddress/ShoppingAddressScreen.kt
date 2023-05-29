package com.hanif.medical.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.ui.theme.DMSans

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonTextFiled
import com.hanif.medical.Screens.shopping.shppingaddress.ShoppingAddressViewModel
import com.hanif.medical.utils.Routes

import com.hanif.medical.utils.graphs.UIEvent


@Composable
fun ShoppingAddressScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ShoppingAddressViewModel = hiltViewModel()
) {
    var isRemeber by rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = {},
            title = "Shopping Address"
        )
    }) { paddingValues ->

        val innerPaddings = paddingValues
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Full Name",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            CommonTextFiled(hint = "Hanif",
                text = viewModel.name,
                onValueChange = { string ->
                  //  viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.nameErrMsg,
                isError = viewModel.nameValid,
                imeAction = ImeAction.Next)

            Text(
                text = "Mobile Number",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            CommonTextFiled(hint = "90xxxxxxxxxx",
                text = viewModel.phone,
                onValueChange = { string ->
                    //viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.phoneErrMsg,
                isError = viewModel.phoneNameValid,
                imeAction = ImeAction.Next)

            Text(
                text = "Flat, House No., Building",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            CommonTextFiled(hint = " Xyz xxxxxxxxxx",
                text = viewModel.address,
                onValueChange = { string ->
                   // viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.addressMsg,
                isError = viewModel.addressValid,
                imeAction = ImeAction.Next)

            Text(
                text = "Area, Street",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            CommonTextFiled(hint = " Xyz xxxxxxxxxx",
                text = viewModel.address1,
                onValueChange = { string ->
                    // viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.addressMsg1,
                isError = viewModel.addressValid1,
                imeAction = ImeAction.Next)

            Text(
                text = "Landmark",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            CommonTextFiled(hint = " Xyz",
                text = viewModel.landMark,
                onValueChange = { string ->
                    // viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.landMarkMsg,
                isError = viewModel.landMarkValid,
                imeAction = ImeAction.Next)

            Text(
                text = "Pincode",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            CommonTextFiled(hint = "123456",
                text = viewModel.pinCode,
                onValueChange = { string ->
                    // viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.pinCodeMsg,
                isError = viewModel.pinCodeValid,
                imeAction = ImeAction.Next)

            Text(
                text = "Town/City",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            CommonTextFiled(hint = "abcd",
                text = viewModel.city,
                onValueChange = { string ->
                    // viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.cityMsg,
                isError = viewModel.cityValid,
                imeAction = ImeAction.Next)

            Text(
                text = "State",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            CommonTextFiled(hint = "abcd",
                text = viewModel.state,
                onValueChange = { string ->
                    // viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.stateMsg,
                isError = viewModel.stateValid,
                imeAction = ImeAction.Next)

            Text(
                text = "Country",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal,
                maxLines = 3,
                fontSize = 16.sp
            )

            CommonTextFiled(hint = "abcd",
                text = viewModel.country,
                onValueChange = { string ->
                    // viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.countryMsg,
                isError = viewModel.countryValid,
                imeAction = ImeAction.Next)

            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp)
            ) {
                Checkbox(checked = isRemeber, onCheckedChange = { isRemeber = !isRemeber })

                Text(
                    text = "Make This my Defult Address!",
                    Modifier.align(Alignment.CenterVertically),
                    fontSize = 16.sp
                )
            }

            CommonButton("Save", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp), shape = RoundedCornerShape(50), onClick = {
                onNavigate(UIEvent.Navigate(Routes.SHOPPING_PRE_PAYMENT_SCREEN))
            })
        }
    }
}

