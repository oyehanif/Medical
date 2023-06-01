package com.hanif.medical.Screens.doctorModule.auth

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.Screens.commo.CommonProgress
import com.hanif.medical.Screens.commo.CommonTextFiled
import com.hanif.medical.Screens.commo.CommonTextFiledForPassword
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.utils.graphs.UIEvent

@Composable
fun DoctorLoginScreen(
    onPopBackStack: () -> Unit,
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val viewModel: DoctorLoginViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state



    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                UIEvent.PopBackStack -> onPopBackStack()
                is UIEvent.Navigate -> {
                    onNavigate(event)
                }

                is UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message, actionLabel = event.action
                    )
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CommonAppBar(
                navigationIconAction = { viewModel.onEvent(DoctorAuthEvent.OnPopBack()) }
            )
        }
    ) { paddingValues ->

        val innerPadding = paddingValues

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxSize(),
        ) {


            Text(
                text = "Welcome Back Doctor 👋",
                fontFamily = DMSans,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, color = MaterialTheme.colors.secondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.login_txt),
                fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "enter your email",
                fontFamily = DMSans,
                fontWeight = FontWeight(400),
                fontSize = 14.sp, color = MaterialTheme.colors.secondary
            )

            Spacer(modifier = Modifier.height(5.dp))

            CommonTextFiled(
                hint = "Hanif@careme.com",
                TrailingIconImg = R.drawable.email,
                isTrailingIconClickable = false,
                trailingIcon = true,
                text = viewModel.userEmail,
                onValueChange = { email -> viewModel.onEvent(DoctorAuthEvent.OnEmailChange(email)) },
                imeAction = ImeAction.Next,
                errorMes = viewModel.emailErrMsg,
                isError = viewModel.emailNameValid,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "enter your password",
                fontFamily = DMSans,
                fontWeight = FontWeight(400),
                fontSize = 14.sp, color = MaterialTheme.colors.secondary
            )

            Spacer(modifier = Modifier.height(5.dp))

            CommonTextFiledForPassword(
                hint = "************",
                isTrailingIconClickable = true,
                trailingIcon = true,
                text = viewModel.userPassword,
                onValueChange = { password ->
                    viewModel.onEvent(
                        DoctorAuthEvent.OnPasswordChange(
                            password
                        )
                    )
                },
                imeAction = ImeAction.Done,
                errorMes = viewModel.passwordErrMsg,
                isError = viewModel.passwordValid,
            )
            Column() {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = viewModel.isRememberValid,
                            onCheckedChange = { isChecked ->
                                viewModel.onEvent(DoctorAuthEvent.OnCheckChange(isChecked))
                            },
                            enabled = true,

                        )
                        Text(
                            text = "remember me",
                            fontFamily = DMSans,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                    Text(
                        text = "Forgot Password?",
                        fontFamily = DMSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = {
                    viewModel.onEvent(DoctorAuthEvent.OnSubmitClick(viewModel.userEmail))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(shape = CircleShape)
            ) {
                Text(
                    text ="sign in",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = DMSans,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

        }

        if (state.isLoading) {
            CommonProgress()
        }
    }
}

