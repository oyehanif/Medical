package com.matrixhive.subsalert.component.setting

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.Routes.DeleteMyAccountScreen
import com.hanif.medical.utils.Routes.FAQ
import com.hanif.medical.utils.graphs.UIEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun SettingScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = {
        CommonAppBar(

            navigationIconAction = { navController.popBackStack() }, title = "Settings"
        )
    }) {
        val openDialog = remember { mutableStateOf(false) }
        Surface(
            Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center) {
                        // animation
                        val progressAnimate by animateFloatAsState(
                            targetValue = 0.75f,
                            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                        )

                        CircularProgressIndicator(
                            progress = progressAnimate,
                            modifier = Modifier
                                .size(size = 100.dp)
                                .padding(2.dp)
                                .scale(scaleX = -1f, scaleY = 1f),
//                            color = MajorelleBlue,
//                            backgroundColor = Lavender,
                            strokeWidth = 3.dp,
                        )



                        Image(
                            painter = painterResource(id = R.drawable.setting_profile_img),
                            contentDescription = "",
                            modifier = modifier
                                .size(80.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                //.background(MajorelleBlue)
                                .align(Alignment.CenterEnd)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(Modifier.padding(vertical = 10.dp)) {
                        Text(
                            text = "Hanif Shaikh",
                            fontWeight = FontWeight(500),
                            fontSize = 16.sp, //color = YankeesBlue1
                        )

                        Text(
                            text = "HanifShaikh@gmail.com",
                            // fontFamily = DMSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            //                           color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))


                //Delete My Account
                CommonCardViewForSettingScreen(
                    lightIcon = R.drawable.setting_delete_ligth,
                    text = "Delete My Account",
                    darkIcon = R.drawable.setting_delete_dark, onClick = {navController.navigate(
                        DeleteMyAccountScreen) })

                //FAQ's
                CommonCardViewForSettingScreen(
                    lightIcon = R.drawable.setting_faq_ligth,
                    text = "FAQ's",
                    darkIcon = R.drawable.setting_faq_dark,
                    onClick = { navController.navigate(FAQ) })

                //Logout
                CommonCardViewForSettingScreen(
                    lightIcon = R.drawable.setting_logout_ligth,
                    text = "Logout",
                    darkIcon = R.drawable.setting_logout_dark, onClick = {})

            }
        }



        /*AnimatedVisibility(visible = openDialog.value) {
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {*//*dismiss the dialog here.*//* }
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        shape = RoundedCornerShape(10),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colors.secondaryVariant)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp),
                            horizontalAlignment = CenterHorizontally
                        ) {

                            Text(
                                text = "Log out",
                                fontFamily = DMSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                color = Color.Red
                            )

                            Divider(
                                modifier.padding(horizontal = 15.dp, vertical = 14.dp),
                                color = Color.Gray,
                                thickness = 1.dp
                            )

                            Text(
                                text = "Are you sure you want to log out ?",
                                fontFamily = DMSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = {
                                        openDialog.value = !openDialog.value
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth(.5f)
                                        .padding(end = 5.dp)
                                        .height(48.dp)
                                        .clip(shape = CircleShape),

                                    ) {
                                    Text(
                                        text = "Cancel",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(500),
                                        fontFamily = DMSans,
                                        color = Blue
                                    )
                                }

                                Button(
                                    onClick = {
                                        openDialog.value = !openDialog.value

                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 5.dp)
                                        .height(48.dp)
                                        .clip(shape = CircleShape),
                                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                        Blue
                                    )
                                ) {
                                    Text(
                                        text = "Yes, Logout",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(500),
                                        fontFamily = DMSans, color = White
                                    )
                                }
                            }
                        }
                    }
                }
            }


        }*/
    }
}

@Composable
fun CommonCardViewForSettingScreen(
    text: String,
    @DrawableRes lightIcon: Int,
    @DrawableRes darkIcon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25))
            // .background(Magnolia)
            .padding(20.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row {
            Image(
                painter = painterResource(id = if (!isDark) lightIcon else darkIcon),
                contentDescription = ""
            )

            Spacer(modifier = modifier.width(10.dp))

            Text(
                text = text,
                // fontFamily = DMSans,
                fontWeight = FontWeight.Medium, textAlign = TextAlign.Center
            )
        }

        Image(
            painter = painterResource(id = R.drawable.arrow_down),
            contentDescription = ""
        )
    }
}