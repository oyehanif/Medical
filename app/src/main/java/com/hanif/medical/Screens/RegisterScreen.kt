package com.hanif.medical.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonTextFiled
import com.hanif.medical.utils.graphs.UIEvent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.hanif.medical.RegisterViewModel
import com.hanif.medical.Screens.auth.AuthEvent
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.Screens.commo.CommonProgress
import com.hanif.medical.Screens.commo.CommonTextFiledForPassword
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.ui.theme.RomanSilver

@Composable
fun RegisterScreen(
    onPopBackStack: () -> Unit,
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    var phone by rememberSaveable { mutableStateOf("") }
    val state = viewModel.state
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                UIEvent.PopBackStack -> onPopBackStack()
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


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CommonAppBar(
                navigationIconAction = { viewModel.onEvent(AuthEvent.OnPopBack()) }
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
                text = "Create an Account",
                fontFamily = DMSans,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, color = MaterialTheme.colors.secondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Please Enter Your Details Below",
                fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                color = RomanSilver,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text ="Enter Your Name",
                fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                color = MaterialTheme.colors.secondary
            )

            CommonTextFiled(
                hint = "john Smith",
                TrailingIconImg = R.drawable.user,
                isTrailingIconClickable = false,
                trailingIcon = true,
                text = viewModel.name,
                onValueChange = { string ->
                    viewModel.onEvent(AuthEvent.OnNameChange(string))
                },
                errorMes = viewModel.nameErrMsg,
                isError = viewModel.nameValid,
                imeAction = ImeAction.Next
            )

            Text(
                text = "Enter Your Email",
                fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp, color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
            )

            CommonTextFiled(
                hint = stringResource(id = R.string.email_hint),
                TrailingIconImg = R.drawable.email,
                isTrailingIconClickable = false,
                trailingIcon = true,
                text = viewModel.email,
                onValueChange = { string ->
                    viewModel.onEvent(AuthEvent.OnEmailChange(string))
                },
                errorMes = viewModel.emailErrMsg,
                isError = viewModel.emailNameValid,
                imeAction = ImeAction.Next
            )

            Text(
                text = stringResource(R.string.enter_your_phone),
                fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                color = MaterialTheme.colors.secondary
            )

            viewModel.fieldResult = selectCountryWithCountryCode(
                text = viewModel.phone,
                onValueChange = { string -> viewModel.onEvent(AuthEvent.OnPhoneNumberChange(string)) },
                isValidPhone = viewModel.phoneNumberValid,
            )


            Text(
                text = stringResource(id = R.string.input_password),
                fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                color = MaterialTheme.colors.secondary
            )

            CommonTextFiledForPassword(
                hint = stringResource(id = R.string.password_hint),
                isTrailingIconClickable = true,
                trailingIcon = true,
                text = viewModel.password,
                onValueChange = { string ->
                    viewModel.onEvent(AuthEvent.OnPasswordChange(string))
                },
                errorMes = viewModel.passwordErrMsg,
                isError = viewModel.passwordValid,
                imeAction = ImeAction.Done
            )
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = viewModel.isCheckedTC,
                        onCheckedChange = {
                            viewModel.onEvent(AuthEvent.OnCheckChange(!viewModel.isCheckedTC))
                        },
                        enabled = true,
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = RomanSilver
                        )
                    )

                    Text(
                        text = stringResource(R.string.register_term_condition_txt),
                        fontFamily = DMSans,
                        fontWeight = FontWeight.Medium,
                        color = RomanSilver,
                        fontSize = 12.sp
                    )
                }

                AnimatedVisibility(visible = viewModel.isCheckedValid && viewModel.checkboxError != "") {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = viewModel.checkboxError,
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { viewModel.onEvent(AuthEvent.OnSubmitClick(viewModel.email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(shape = CircleShape),
                colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = DMSans, color = White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(R.string.already_have_an_account),
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 14.sp
                )
                Text(
                    text = stringResource(id = R.string.sign_in),
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Medium,
                    color = Blue,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { viewModel.onEvent(AuthEvent.OnSignInClick) }
                )
            }
        }

        if (state.isLoading) {
            CommonProgress()
        }
    }
}
/*
@Composable
fun RegisterScreen(
    onPopBackStack: () -> Unit,
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController, viewModel: AuthViewModel = hiltViewModel()
) {

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
            CommonTextFiled(hint = "Name", icon = Icons.Default.Person, imeAction = ImeAction.Next)

        CustomSpacer(20.dp)


        val email =
            CommonTextFiled(hint = "Email", icon = Icons.Default.Email, imeAction = ImeAction.Next)

        CustomSpacer(20.dp)

        val phone =
            CommonTextFiled(
                hint = "Phone No",
                icon = Icons.Default.Phone,
                imeAction = ImeAction.Next
            )

        CustomSpacer(20.dp)

        val password = CommonTextFiled(
            hint = "Password",
            icon = Icons.Default.Lock,
            imeAction = ImeAction.Done,
            trailingIcon = true
        )

        CustomSpacer(20.dp)

        CommonButton("Register", modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), shape = RoundedCornerShape(50), onClick = {
          //  viewModel.register(name, email.lowercase(Locale.ROOT), phone, password)
        })


       */
/* MultiStyleText(
            "Already Have have an account? \t",
            Color.Gray,
            "SignIn",
            color2 = Color.Blue,
            Modifier
                .clickable { navController.popBackStack() },
        )
*//*

        registerResult.value?.let {
            when (it) {
                is Resource.Error -> Log.e("TAG", "RegisterScreen: ${it.error}")
                is Resource.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is Resource.Success -> {
                    Log.e("TAG", "RegisterScreen: successfully")
                    onNavigate(UIEvent.Navigate(Graph.HOME))
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPrev() {
    // RegisterScreen()
}*/
