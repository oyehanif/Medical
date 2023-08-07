package com.hanif.medical.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.hanif.medical.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hanif.medical.utils.graphs.UIEvent
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.hanif.medical.BannerAd
import com.hanif.medical.LoginViewModel
import com.hanif.medical.Screens.auth.AuthEvent
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.Screens.commo.CommonProgress
import com.hanif.medical.Screens.commo.CommonTextFiled
import com.hanif.medical.Screens.commo.CommonTextFiledForPassword
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.ui.theme.RomanSilver

@Composable
fun LoginScreen(
    onPopBackStack: () -> Unit,
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val viewModel: LoginViewModel = hiltViewModel()
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

            BannerAd(adId = "ca-app-pub-3940256099942544/6300978111")
            Text(
                text = "Welcome Back! \uD83D\uDC4B",
                fontFamily = DMSans,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, color = MaterialTheme.colors.secondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.login_txt),
                fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                color = RomanSilver,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text ="Enter Your Email",
                fontFamily = DMSans,
                fontWeight = FontWeight(400),
                fontSize = 14.sp, color = MaterialTheme.colors.secondary
            )

            Spacer(modifier = Modifier.height(5.dp))

            CommonTextFiled(
                hint = "Jhon Smit",
                TrailingIconImg = R.drawable.email,
                isTrailingIconClickable = false,
                trailingIcon = true,
                text = viewModel.emailState.text,
                onValueChange = { email -> viewModel.onEvent(AuthEvent.OnEmailChange(email)) },
                imeAction = ImeAction.Next,
                errorMes = viewModel.emailState.errorMes,
                isError = viewModel.emailState.isError,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text ="Enter Your Password",
                fontFamily = DMSans,
                fontWeight = FontWeight(400),
                fontSize = 14.sp, color = MaterialTheme.colors.secondary
            )

            Spacer(modifier = Modifier.height(5.dp))

            CommonTextFiledForPassword(
                hint = "************",
                isTrailingIconClickable = true,
                trailingIcon = true,
                text = viewModel.passwordState.text,
                onValueChange = { password ->
                    viewModel.onEvent(
                        AuthEvent.OnPasswordChange(
                            password
                        )
                    )
                },
                imeAction = ImeAction.Done,
                errorMes = viewModel.passwordState.errorMes,
                isError = viewModel.passwordState.isError,
            )
           /* Column() {
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
                                viewModel.onEvent(AuthEvent.OnCheckChange(isChecked))
                            },
                            enabled = true,
                        )
                        Text(
                            text = "Remember Me!",
                            fontFamily = DMSans,
                            fontWeight = FontWeight.Medium,
                            color = RomanSilver,
                            fontSize = 12.sp
                        )
                    }
                    Text(
                        text = "Forgot Password?",
                        fontFamily = DMSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,modifier =Modifier.clickable { viewModel.onEvent(event = AuthEvent.OnForgetPasswordClick) }
                    )
                }
            }*/

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = {
                    viewModel.onEvent(AuthEvent.OnSubmitClick(viewModel.emailState.text))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(shape = CircleShape),

            ) {
                Text(
                    text ="Sign In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = DMSans,
                    color = White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don’t have an account? ",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 14.sp
                )
                Text(text = "Sign up.",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Medium,
                    color = Blue,
                    fontSize = 14.sp,
                    modifier = modifier.clickable { viewModel.onEvent(AuthEvent.OnSignUpClick) })
            }
        }

        if (state.isLoading) {
            CommonProgress()
        }
    }
}

@Composable
fun CommonButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = CutCornerShape(10), onClick: () -> Unit,
) {
    Button(modifier = modifier, onClick = { onClick() }, shape = shape) {
        Text(text = text)
    }
}

/*
@Composable
fun LoginScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val loginResult = viewModel.loginFlow.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        var isRemeber by rememberSaveable {
            mutableStateOf(false)
        }
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
            text = "Login to your account",
            modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 25.sp
        )

        CustomSpacer(20.dp)

        val email =
            commonTextFiled(hint = "Email", icon = Icons.Default.Email, imeAction = ImeAction.Next)

        CustomSpacer(20.dp)

        val password = commonTextFiled(
            hint = "Password",
            icon = Icons.Default.Lock,
            imeAction = ImeAction.Done,
            trailingIcon = true
        )


        Row(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp)
        ) {
            Checkbox(checked = isRemeber, onCheckedChange = { isRemeber = !isRemeber })

            Text(
                text = "Remember Me!",
                Modifier.align(Alignment.CenterVertically),
                fontSize = 16.sp
            )
        }

        CommonButton("Login", modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), shape = RoundedCornerShape(50), onClick = {
            viewModel.login(email, password, isRemeber)
        })

        Text(text = "Forgot the password ?",
            Modifier
                .clickable { navController.navigate(AuthScreen.ForgetPassword.route) }
                .padding(top = 10.dp))



        MultiStyleText(
            "Don't have an account? \t",
            Color.Gray,
            "SignUp",
            color2 = Color.Blue,
            Modifier
                .clickable {onNavigate(UIEvent.Navigate(AuthScreen.SignUp.route)) },
        )

        val scope = rememberCoroutineScope()
        loginResult.value?.let {
            when (it) {

                is Resource.Error -> {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = it.error.toString(),
                        )
                    }
                }
                is Resource.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        */
/*navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }*//*


                        onNavigate(UIEvent.Navigate(Graph.HOME))
                    }
                }
            }
        }

    }
}

@Composable
fun CommonButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = CutCornerShape(10), onClick: () -> Unit,
) {
    Button(modifier = modifier, onClick = { onClick() }, shape = shape) {
        Text(text = text)
    }
}

@Composable
fun MultiStyleText(
    text1: String,
    color1: Color,
    text2: String,
    color2: Color,
    modifier: Modifier = Modifier
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = color1)) {
                append(text1)
            }
            withStyle(style = SpanStyle(color = color2)) {
                append(text2)
            }
        }, modifier = modifier
    )
}

*/
/*
@Preview
@Composable
fun CommonButtonPreview() {
    CommonButton("demo")
}
*//*



@Composable
fun commonTextFiled(
    hint: String? = null,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    trailingIcon: Boolean = false
): String {
    var textOfEditText by rememberSaveable { mutableStateOf("") }
    var toggleClick by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = textOfEditText,
        onValueChange = { textOfEditText = it },

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp), shape = RoundedCornerShape(12.dp),
        placeholder = {
            if (hint != null) {
                Text(text = hint)
            }
        },
        visualTransformation = if (!toggleClick) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            if (icon != null) {
                Icon(icon, contentDescription = null)
            }
        },
        trailingIcon = {
            if (trailingIcon) {
                val image: Int = if (toggleClick) {
                    R.drawable.visibility
                } else {
                    R.drawable.visibility_off
                }

                IconButton(onClick = { toggleClick = !toggleClick }) {
                    Icon(painter = painterResource(id = image), contentDescription = null)
                }
            }
        },
        singleLine = true
    )
    return textOfEditText
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPrev() {
    //LoginScreen()
}


*/
