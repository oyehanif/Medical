package com.hanif.medical.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.hanif.medical.R
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hanif.medical.models.Resource
import com.hanif.medical.navigation.Screen
import com.hanif.medical.utils.CustomSpacer
import com.hanif.medical.utils.graphs.AuthScreen
import com.hanif.medical.utils.graphs.Graph
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.AuthViewModel


@Composable
fun LoginScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val loginResult = viewModel.loginFlow.collectAsStateWithLifecycle()

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
            viewModel.login(email, password)
        })

        Text(text = "Forgot the password ?",
            Modifier
                .clickable { navController.navigate(Screen.ForgetPassword.route) }
                .padding(top = 10.dp))



        MultiStyleText(
            "Don't have an account? \t",
            Color.Gray,
            "SignUp",
            color2 = Color.Blue,
            Modifier
                .clickable {onNavigate(UIEvent.Navigate(AuthScreen.SignUp.route)) },
        )

        loginResult.value?.let {
            when (it) {
                is Resource.Error -> Log.e("TAG", "LoginScreen: ${it.error}")
                is Resource.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        /*navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }*/

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

/*
@Preview
@Composable
fun CommonButtonPreview() {
    CommonButton("demo")
}
*/


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
        keyboardOptions = KeyboardOptions(
            KeyboardCapitalization.Characters,
            autoCorrect = false,
            keyboardType = keyboardType, imeAction
        ),

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


