package com.hanif.medical.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
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
import com.hanif.medical.ui.theme.DMSans

@Preview(showBackground = true)
@Composable
fun ShoppingAddressScreen() {
    var isRemeber by rememberSaveable {
        mutableStateOf(false)
    }
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Full Name",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal,
            maxLines = 3,
            fontSize = 16.sp
        )

        commonTextFiled(hint = "Hanif")

        Text(
            text = "Mobile Number",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal,
            maxLines = 3,
            fontSize = 16.sp
        )

        commonTextFiled(hint = "90xxxxxxxxxx")

        Text(
            text = "Flat, House No., Building",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal,
            maxLines = 3,
            fontSize = 16.sp
        )

        commonTextFiled(hint = " Xyz xxxxxxxxxx")

        Text(
            text = "Area, Street",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal,
            maxLines = 3,
            fontSize = 16.sp
        )

        commonTextFiled(hint = " Xyz xxxxxxxxxx")

        Text(
            text = "Landmark",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal,
            maxLines = 3,
            fontSize = 16.sp
        )

        commonTextFiled(hint = " Xyz")

        Text(
            text = "Pincode",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal,
            maxLines = 3,
            fontSize = 16.sp
        )

        commonTextFiled(hint = "123456")

        Text(
            text = "Town/City",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal,
            maxLines = 3,
            fontSize = 16.sp
        )

        commonTextFiled(hint = "abcd")

        Text(
            text = "State",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal,
            maxLines = 3,
            fontSize = 16.sp
        )

        commonTextFiled(hint = "abcd")

        Text(
            text = "Country",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Normal,
            maxLines = 3,
            fontSize = 16.sp
        )

        commonTextFiled(hint = "abcd")

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
            //viewModel.login(email, password, isRemeber)
        })
    }
}