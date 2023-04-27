package com.hanif.medical.Screens

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.navigation.Screen
import com.hanif.medical.utils.CustomSpacer

@Composable
fun ForgetPasswordScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 80.dp)
                .size(150.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Pager Image"
        )

        CustomSpacer(40.dp)

        Text(
            text = "Forget Password ",
            modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 25.sp
        )


        Text(text = "Please Enter Your Register Email!", Modifier.padding(start = 25.dp,top=30.dp))
        CustomSpacer(10.dp)

        val email =
            commonTextFiled(hint = "Email", icon = Icons.Default.Email, imeAction = ImeAction.Next)

        CustomSpacer(20.dp)
        
        CommonButton("Send", modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), shape = RoundedCornerShape(50), onClick = { navController.popBackStack()})

    }
}

@Preview(showBackground = true)
@Composable
fun ForgetPasswordScreenPrev() {
   // ForgetPasswordScreen()
}