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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.ui.theme.DMSans

import androidx.compose.ui.text.input.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanif.medical.Screens.commo.CommonTextFiled
import com.hanif.medical.Screens.shopping.shppingaddress.ShoppingAddressEvent
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
                    viewModel.onEvent(ShoppingAddressEvent.FullName(string))
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
                    viewModel.onEvent(ShoppingAddressEvent.Mobile(string))
                },
                errorMes = viewModel.phoneErrMsg,
                isError = viewModel.phoneNameValid,
                imeAction = ImeAction.Next, keyboardType = KeyboardType.Number)

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
                    viewModel.onEvent(ShoppingAddressEvent.FlatHouse(string))
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
                text = viewModel.area,
                onValueChange = { string ->
                     viewModel.onEvent(ShoppingAddressEvent.AreaStreet(string))
                },
                errorMes = viewModel.areaMsg1   ,
                isError = viewModel.areaValid,
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
                     viewModel.onEvent(ShoppingAddressEvent.Landmark(string))
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
                     viewModel.onEvent(ShoppingAddressEvent.PinCode(string))
                },
                errorMes = viewModel.pinCodeMsg,
                isError = viewModel.pinCodeValid,
                imeAction = ImeAction.Next, keyboardType = KeyboardType.Number)

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
                     viewModel.onEvent(ShoppingAddressEvent.TownCity(string))
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
                     viewModel.onEvent(ShoppingAddressEvent.State(string))
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
                     viewModel.onEvent(ShoppingAddressEvent.Country(string))
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
                viewModel.onEvent(ShoppingAddressEvent.OnSubmitClick)
            })
        }
    }
}

