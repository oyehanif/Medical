package com.hanif.medical.Screens.commo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

//custom dropdown view
@Composable
fun CustomDropDown(
    array: Array<String>,
    text: String,
    onValueChange: (String) -> Unit,
    hint: String,isError: Boolean = false,errorMes: String = "",
    modifier: Modifier = Modifier,
) {

    var mExpanded by remember { mutableStateOf(false) }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {

        OutlinedTextField(
            readOnly = true,
            enabled = false,
            value = text,
            onValueChange = {},
            placeholder = {
                Text(
                    text = hint,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    mExpanded = !mExpanded
                }
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(icon, "contentDescription")
            },
            /*colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Gray,
                disabledLabelColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = Color.Black,//LocalCustomColorsPalette.current.textColor,
                disabledTextColor = Color.Black,//LocalCustomColorsPalette.current.textColor,
                disabledIndicatorColor = Color.Transparent,
            )*/
            shape = RoundedCornerShape(40)
        )

        AnimatedVisibility(visible = isError && errorMes != "") {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = errorMes,
                fontSize = 14.sp,
                color = Color.Red
            )
        }

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                .background(MaterialTheme.colors.secondaryVariant)
        ) {
            array.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(label)
                        mExpanded = false
                    },
                ) {
                    Text(
                        text = label,
                        color = Color.Black,//LocalCustomColorsPalette.current.textColor,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }

}