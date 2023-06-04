package com.hanif.medical.Screens


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.Screens.doctor.BookingProcess
import com.hanif.medical.application.MedicalAppClass
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Preview
@Composable
fun AppointmentScheduleScreen(viewModel: HomeViewModel = hiltViewModel()) {
//    EmptyScreen("No Appointment Book In Past","appointment schedule not found please book appointment to see history")
    val state = viewModel.appointmentState
    LaunchedEffect(key1 = viewModel.appointmentState) {

    }
    Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = { /*TODO*/ },
            title = "Schedule Appointment's"
        )
    }) {
        val innerPaddingValues = it
        Column() {
            CustomTabs()

            LazyColumn() {
                state.companies.reversed()
                items(state.companies.size) { i ->
                    val bookingProcessModel: BookingProcess = state.companies[i]
                    ItemScheduleAppointment(
                        bookingProcessModel, isDoctor = false, cancel = {
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.insertAppointment(
                                    bookingProcessModel.copy(status = "cancel"),
                                    true
                                )
                                viewModel.getAppointmentDataList()
                            }
                        }
                    ) {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.insertAppointment(bookingProcessModel.copy(status = ""), false)
                            viewModel.getAppointmentDataList()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTabs() {
    var selectedIndex by remember { mutableStateOf(0) }

    val list = listOf("Upcoming Appointment's", "Completed")

    TabRow(selectedTabIndex = selectedIndex,
        backgroundColor = Color(0xff1E76DA),
        modifier = Modifier
            .wrapContentHeight()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(50))
            .padding(1.dp),
        indicator = { tabPositions: List<TabPosition> ->
            Box {}
        }
    ) {
        list.forEachIndexed { index, text ->
            val selected = selectedIndex == index
            Tab(
                modifier = if (selected) Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        Color.White
                    )
                else Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        Color(
                            0xff1E76DA
                        )
                    ),
                selected = selected,
                onClick = { selectedIndex = index },
                text = { Text(text = text, color = Color(0xff6FAAEE)) }
            )
        }
    }
}


@Composable
fun ItemScheduleAppointment(
    itemModel: BookingProcess, text: String = "", onValueChange: (String) -> Unit = {},
    isDoctor: Boolean = false,
    cancel: () -> Unit = {}, isReportAdd: Boolean = false,
    secoundButton: () -> Unit = {},
) {
    val context = LocalContext.current

    var isReportSectionISVisible by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(itemModel.model!!.image),
                contentDescription = "", contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(.3f)
                    .clip(
                        RoundedCornerShape(20)
                    )
                    .size(100.dp)
            )

            Column(Modifier.weight(.5f)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = itemModel.model.name,
                        fontSize = 20.sp,

                        fontWeight = FontWeight.Bold
                    )

                    AnimatedVisibility(visible = itemModel.status == "cancel") {
                        Text(
                            text = "Booking Cancel",
                            fontSize = 20.sp, color = Color.Red,
                            fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 10.dp)
                        )
                    }

                }
                Text(
                    text = itemModel.model.specialize,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = itemModel.date + " " + itemModel.time,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            if (itemModel.status != "cancel") {
                Image(
                    Icons.Default.Call, //rememberAsyncImagePainter(itemModel.image) ,
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(.2f)
                        .clip(
                            CircleShape
                        )
                        .clickable {
                            if (isDoctor) {
                                callerFunction(context, itemModel.model.phone.toString())
                            } else {
                                callerFunction(context = context, itemModel.patientPhoneNumber)
                            }
                        },
                    contentDescription = "",
                )
            }
        }

        Divider(thickness = 1.dp, color = Color.Gray, modifier = Modifier.padding(10.dp))

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            if (itemModel.status != "cancel") {
                Text(
                    text = "Cancel Appointment",
                    fontWeight = FontWeight.Medium,
                    fontFamily = DMSans,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .height(42.dp)
                        .padding(horizontal = 10.dp)
                        .border(1.dp, Color.Blue, RoundedCornerShape(30))
                        .padding(10.dp)
                        .clickable { cancel() },
                    textAlign = TextAlign.Center
                )
            }

            val textOfSecondButton = if (isDoctor) "Add Report" else "Re Book"
            Text(
                text = textOfSecondButton,
                fontWeight = FontWeight.Medium,
                fontFamily = DMSans, color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .height(42.dp)
                    .clip(RoundedCornerShape(30))
                    .background(Color.Blue)
                    .padding(10.dp)
                    .clickable {
                        if (isDoctor) {
                            isReportSectionISVisible = !isReportSectionISVisible
                        } else {
                            secoundButton()
                        }
                    },
                textAlign = TextAlign.Center
            )
        }

        AnimatedVisibility(visible = isReportSectionISVisible) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {

                Textarea(text) { onValueChange(it) }

                CommonButton(text = "Add Report") {
                    secoundButton()
                    isReportSectionISVisible = !isReportSectionISVisible
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Textarea(text: String, onValueChange: (String) -> Unit) {

    TextField(
        value = text,
        onValueChange = { onValueChange(it) }, modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
    )
}

fun callerFunction(context: Context, phone: String) {
    val i = Intent(Intent.ACTION_DIAL)
    val p = "tel:$phone"
    i.data = Uri.parse(p)
    context.startActivity(i)
}