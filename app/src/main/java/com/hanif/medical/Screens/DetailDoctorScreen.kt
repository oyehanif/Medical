package com.hanif.medical.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.hanif.medical.R
import com.hanif.medical.Screens.doctor.DoctorSharedViewModel
import com.hanif.medical.Screens.shopping.ShoppingSharedViewModel
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent


@Composable
fun DetailDoctorScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    sharedViewModel: DoctorSharedViewModel, modifier: Modifier = Modifier
) {

    val doctorModel = sharedViewModel.doctorModel
    doctorModel?.let {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()
        ) {

            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp), shape = RoundedCornerShape(30.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        Image(
                            Icons.Default.ArrowBack,
                            contentDescription = "",
                            modifier.padding(start = 10.dp, top = 10.dp)
                        )
                        Image(
                            rememberAsyncImagePainter(it.image),
                            contentDescription = "", contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(30))
                        )
                        Image(
                            Icons.Default.Settings,
                            contentDescription = "",
                            modifier.padding(start = 10.dp, top = 10.dp)
                        )
                    }
                    Text(
                        text = it.name,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = it.specialize,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {

                        //Patients
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Patients", fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Light
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = it.patient.toString(),
                                fontSize = 18.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        //Rating
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Rating", fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Light
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = it.rating,
                                fontSize = 18.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        //Experience
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Experience", fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Light
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = it.exp.toString(),
                                fontSize = 18.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                }
            }
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp), shape = RoundedCornerShape(30.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start, modifier = modifier.padding(20.dp)
                ) {
                    Text(
                        text = "About",
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = it.desc,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Light
                    )
                }
            }


            Button(
                onClick = { onNavigate(UIEvent.Navigate(Routes.DOCTOR_BOOKING_PROCESS_FIRST_SCREEN)) },
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Book Appointment",
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}