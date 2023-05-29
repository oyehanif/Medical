package com.hanif.medical.Screens.commo

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanif.medical.R

//common field views
@Composable
fun CommonTextFiled(
    modifier: Modifier = Modifier,
    hint: String? = null,
    text: String = "",
    onValueChange: (String) -> Unit = {},
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    trailingIcon: Boolean = false,
    @DrawableRes TrailingIconImg: Int? = null,
    isTrailingIconClickable: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    isTrailingNotDefault: Boolean = false,
    onclick: () -> Unit = {},
    shape: Shape = RoundedCornerShape(40),
    errorMes: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,

    ) {
    var toggleClick by rememberSaveable { mutableStateOf(false) }
    Column() {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(40)),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            placeholder = {
                if (hint != null) {
                    Text(
                        text = hint,
                        color = Color.Gray,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (isTrailingIconClickable) {
                            onclick()
                        }
                    }
                ) {
                    if (TrailingIconImg != null){
                        Icon(
                            painter = painterResource(id = TrailingIconImg),
                            contentDescription = null
                        )
                    }
                }
            },
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            /*colors = TextFieldDefaults.textFieldColors(
                backgroundColor = LocalCustomColorsPalette.current.searchBackGroundColor,
                cursorColor = LocalCustomColorsPalette.current.textColor,
                disabledLabelColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = LocalCustomColorsPalette.current.textColor,
            ),*/
            shape = shape,
            isError = isError,
            textStyle = TextStyle()
        )
        AnimatedVisibility(visible = isError && errorMes != "") {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = errorMes,
                fontSize = 14.sp,
                color = Color.Red
            )
        }
    }
}


@Composable
fun CommonTextFiledForPassword(
    modifier: Modifier = Modifier,
    hint: String? = null,
    text: String = "",
    onValueChange: (String) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Done,
    trailingIcon: Boolean = false,
    isTrailingIconClickable: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    isError: Boolean = false,
    isTrailingNotDefault: Boolean = false,
    onclick: () -> Unit = {},
    shape: Shape = RoundedCornerShape(40),
    errorMes: String = ""
) {
    var toggleClick by rememberSaveable { mutableStateOf(true) }
    Column() {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(40)),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            placeholder = {
                if (hint != null) {
                    Text(
                        text = hint,
                        color = Color.Gray,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp
                    )
                }
            },
            visualTransformation =
            if (!toggleClick) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (trailingIcon) {
                    val image: Int = if (toggleClick) {
                        com.hanif.medical.R.drawable.visibility_off
                    } else {
                        R.drawable.visibility_on
                    }
                    IconButton(
                        onClick = {
                            if (isTrailingIconClickable) {
                                if (isTrailingNotDefault) {
                                    onclick()
                                } else {
                                    run { toggleClick = !toggleClick }
                                }
                            }
                        }
                    ) {
                        Icon(painter = painterResource(id = image), contentDescription = null)
                    }
                }
            },
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            /*colors = TextFieldDefaults.textFieldColors(
                backgroundColor = LocalCustomColorsPalette.current.searchBackGroundColor,
                cursorColor = LocalCustomColorsPalette.current.textColor,
                disabledLabelColor = RomanSilver,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = LocalCustomColorsPalette.current.textColor,
            )*/
            shape = shape,
            isError = isError,
            textStyle = TextStyle()
        )
        AnimatedVisibility(visible = isError && errorMes != "") {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = errorMes,
                fontSize = 14.sp,
                color = Color.Red
            )
        }
    }
}