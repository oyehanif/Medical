package com.hanif.medical.Screens.commo

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanif.medical.R
import com.hanif.medical.ui.theme.DMSans

//app bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonAppBar(
    modifier: Modifier = Modifier,
    @DrawableRes actionIcon: Int? = null,
    navigationIconAction: () -> Unit,
    actions: () -> Unit = {},
    title: String? = null,
    ifActionText: Boolean = true,
    actionText: String = "",
) {
    CenterAlignedTopAppBar(
        title = {
            if (title != null) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colors.secondary
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { navigationIconAction() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = null, tint = MaterialTheme.colors.secondary
                )
            }
        },
        modifier = modifier,
        actions = {
            if (ifActionText) {
                /*Text(
                    text = actionText,
                    modifier = modifier
                        .padding(end = 10.dp)
                        .clickable { actions() },
                    fontWeight = FontWeight.Medium,
                    fontFamily = DMSans,
                    color = if (isSystemInDarkTheme()) RomanSilver else MajorelleBlue
                )*/
            } else {
                IconButton(onClick = { actions() }) {
                    Icon(
                        painter = painterResource(id = actionIcon!!),
                        contentDescription = null
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colors.background)
    )
}