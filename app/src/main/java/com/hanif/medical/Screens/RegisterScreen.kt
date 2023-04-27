package com.hanif.medical.Screens

import android.util.Log
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.models.Resource
import com.hanif.medical.navigation.Screen
import com.hanif.medical.utils.CustomSpacer
import com.hanif.medical.viewmodel.AuthViewModel
import java.util.Locale

@Composable
fun RegisterScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {

    val registerResult = viewModel.registerFlow.collectAsState()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

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
            text = "Create New Account",
            modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 25.sp
        )

        CustomSpacer(20.dp)

        val name =
            commonTextFiled(hint = "Name", icon = Icons.Default.Person, imeAction = ImeAction.Next)

        CustomSpacer(20.dp)


        val email =
            commonTextFiled(hint = "Email", icon = Icons.Default.Email, imeAction = ImeAction.Next)

        CustomSpacer(20.dp)

        val phone =
            commonTextFiled(
                hint = "Phone No",
                icon = Icons.Default.Phone,
                imeAction = ImeAction.Next
            )

        CustomSpacer(20.dp)

        val password = commonTextFiled(
            hint = "Password",
            icon = Icons.Default.Lock,
            imeAction = ImeAction.Done,
            trailingIcon = true
        )

        CustomSpacer(20.dp)

        CommonButton("Register", modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), shape = RoundedCornerShape(50), onClick = {
                viewModel.register(name, email.lowercase(Locale.ROOT), phone, password)
        })


        MultiStyleText(
            "Already Have have an account? \t",
            Color.Gray,
            "SignIn",
            color2 = Color.Blue,
            Modifier
                .clickable { navController.popBackStack() },
        )

        registerResult.value?.let {
            when(it){
                is Resource.Error -> Log.e("TAG", "RegisterScreen: ${it.error}", )
                is Resource.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is Resource.Success -> {
                    Log.e("TAG", "RegisterScreen: successfully", )
                    navController.navigateUp()
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPrev() {
    // RegisterScreen()
}