package com.matrixhive.subsalert.component.setting

/*
import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun EditProfileScreen() {
    Scaffold(topBar = {
        */
/*CommonAppBar(
             
            navigationIconAction = { }, title = stringResource(id = R.string.edit_profile)
        )*//*

    }) {
        var name by rememberSaveable() { mutableStateOf("") }
        var email by rememberSaveable() { mutableStateOf("") }
        var phone by rememberSaveable() { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // animation
                val progressAnimate by animateFloatAsState(
                    targetValue = 1f,
                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                )

                CircularProgressIndicator(
                    progress = progressAnimate,
                    modifier = Modifier
                        .size(size = 100.dp)
                        .padding(2.dp)
                        .scale(scaleX = -1f, scaleY = 1f),
                    color = MajorelleBlue,
                    backgroundColor = Lavender,
                    strokeWidth = 3.dp,
                )



                Image(
                    painter = painterResource(id = R.drawable.setting_profile_img),
                    contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(id = R.drawable.edit_img_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(6.dp)
                        .align(Alignment.BottomEnd)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.enter_your_name), fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp, modifier = Modifier.fillMaxWidth(),
            )
            CommonTextFiled(
                hint = "Jhon Smit",
                TrailingIconImg = R.drawable.user,
                isTrailingIconClickable = false, trailingIcon = true,
                text = name, onValueChange = {
                    name = it
                })

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = stringResource(R.string.enter_your_email), fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp, modifier = Modifier.fillMaxWidth()
            )
            CommonTextFiled(
                hint = "Jhonsmit007@gmail.com",
                TrailingIconImg = R.drawable.email,
                isTrailingIconClickable = true, trailingIcon = true, text = email, onValueChange = {
                    email = it
                })

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.enter_your_phone), fontFamily = DMSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp, modifier = Modifier.fillMaxWidth()
            )
            phone = selectCountryWithCountryCode()


            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = { }, modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(
                        shape = CircleShape
                    )
            ) {
                Text(text = stringResource(R.string.save))
            }

        }
    }
}*/
