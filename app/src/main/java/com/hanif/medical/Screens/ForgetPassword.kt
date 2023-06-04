package com.hanif.medical.Screens

import android.graphics.Paint.Align
import android.util.Patterns
import androidx.compose.foundation.Image
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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonProgress
import com.hanif.medical.models.Resource
import com.hanif.medical.navigation.Screen
import com.hanif.medical.utils.CustomSpacer
import com.hanif.medical.utils.graphs.Graph
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun ForgetPasswordScreen(
    onPopBackStack: () -> Unit,
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController, viewModel: AuthViewModel = hiltViewModel()
) {
    val forgetpasswordresult = viewModel.loginFlow.collectAsStateWithLifecycle()

    val scaffoldState = rememberScaffoldState()
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


        Text(
            text = "Please Enter Your Register Email!",
            Modifier.padding(start = 25.dp, top = 30.dp)
        )
        CustomSpacer(10.dp)

        val email =
            commonTextFiled(hint = "Email", icon = Icons.Default.Email, imeAction = ImeAction.Next)

        CustomSpacer(20.dp)
        val scope = rememberCoroutineScope()
        CommonButton("Send", modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), shape = RoundedCornerShape(50), onClick = {
            viewModel.forgetPassword(email)
            })

    }

    val scope = rememberCoroutineScope()
    forgetpasswordresult.value?.let {
        when (it) {

            is Resource.Error -> {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.error.toString(),
                    )
                }
            }
            is Resource.Loading -> CommonProgress()
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    /*navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }*/

                    UIEvent.PopBackStack
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ForgetPasswordScreenPrev() {
    // ForgetPasswordScreen()
}