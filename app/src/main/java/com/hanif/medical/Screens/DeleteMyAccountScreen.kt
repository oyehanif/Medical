package com.hanif.medical.Screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.utils.graphs.UIEvent

@Composable
fun DeleteMyAccountScreen(
    onPopBackStack: () -> Unit,
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CommonAppBar(
                navigationIconAction = { navController.popBackStack() }, title = "Delete My Account"
            )
        }
    ) { paddingValue ->

        val padding = paddingValue
        var notes by rememberSaveable { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            Column(horizontalAlignment = CenterHorizontally) {

                Text(
                    text = "select reason",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.secondary
                )

                Spacer(modifier = Modifier.height(5.dp))

                DeleteMyAccountRadioOptions()

                Text(
                    text = "anything else you want to add",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.secondary
                )

                Spacer(modifier = Modifier.height(5.dp))

                NoteView(
                    hint = "Write some suggest to improve our app.",
                    text = notes,
                    onValueChange = { notes = it }
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "*Your account will be permanently removed from the application. All your data will be lost.",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Normal,
                    color = Red,
                    fontSize = 16.sp, modifier = Modifier.fillMaxWidth(),
                )
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(shape = CircleShape),
            ) {
                Text(
                    text = "delete my account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = DMSans,
                    color = White
                )
            }
        }
    }
}


@Composable
fun DeleteMyAccountRadioOptions() {
    val radioOptions = listOf(
        "i don't want to use Care-me)",
        "i have another account)",
        "privacy concerns)",
        "too many ads)",
        "others)"
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    .padding(vertical = 5.dp)
                    .clip(RoundedCornerShape(20))
                    .background(MaterialTheme.colors.secondaryVariant),
                verticalAlignment = CenterVertically) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) },

                    )
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp),
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun NoteView(hint: String, text: String = "", onValueChange: (String) -> Unit = {}) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        minLines = 5,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            cursorColor = Color.Gray,
            textColor = Color.Black,
            disabledLabelColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(20),
        placeholder = {
            Text(
                text = hint,
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight(400)
            )
        },
    )
}