package com.hanif.medical.Screens.doctor

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hanif.medical.R
import com.hanif.medical.Screens.CategoryItem
import com.hanif.medical.Screens.CommonButton
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.Screens.commo.CommonTextFiled
import com.hanif.medical.Screens.commo.CustomDropDown
import com.hanif.medical.models.SubsCategories
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.ui.theme.Purple500
import com.hanif.medical.ui.theme.Purple700
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.BottomBarScreen
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.HomeViewModel
import com.joelkanyi.horizontalcalendar.HorizontalCalendarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun DoctorBookingProcessFirstScreens(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    sharedViewModel: DoctorSharedViewModel,
    modifier: Modifier = Modifier,
    viewModel: DoctorBookingViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                /*is UIEvent.PopBacKStackWithDestination -> {
                    onPopBackStack(event)
                }*/
                is UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message, actionLabel = event.action
                    )
                }

                is UIEvent.Navigate -> {
                    onNavigate(event)
                }

                else -> Unit
            }
        }
    }


    Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = { viewModel.onEvent(DoctorBookingEvent.OnPopBack) },
            title = "Booking"
        )
    }) {
        val innerPadding = it
        val doctorModel = sharedViewModel.doctorModel
        doctorModel?.let {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .imePadding(),) {
                Card(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(20.dp),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = rememberAsyncImagePainter(model = it.image),
                            modifier = Modifier
                                .size(150.dp)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(20)),
                            contentDescription = ""
                        )
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(vertical = 20.dp)
                        ) {
                            Column() {
                                Text(
                                    text = it.name,
                                    Modifier
                                        .padding(horizontal = 10.dp)
                                        .padding(top = 10.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = DMSans,
                                    fontStyle = FontStyle.Italic,
                                    maxLines = 3,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = it.specialize,
                                    Modifier.padding(horizontal = 10.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = DMSans,
                                    fontStyle = FontStyle.Italic,
                                    maxLines = 3,
                                    fontSize = 14.sp
                                )
                            }
                            Text(
                                text = "₹ " + it.fees.toString(),
                                Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontFamily = DMSans,
                                fontStyle = FontStyle.Italic,
                                maxLines = 3,
                                fontSize = 16.sp
                            )

                        }
                    }

                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Booking  For",
                    Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 16.sp
                )

                var tabPage by remember { mutableStateOf(TabPage.Self) }
                val backgroundColor by animateColorAsState(if (tabPage == TabPage.Self) Purple500 else Purple700)
                Spacer(modifier = Modifier.height(10.dp))
                HomeTabBar(backgroundColor = backgroundColor,
                    tabPage = tabPage,
                    onTabSelected = { tabPage = it })

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Appointment Type",
                    Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))


                val itemList = remember { AppointmentType() }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(itemList.AppTYpe, key = { it.categories }) {
                        CategoryItem(it, itemList::onItemSelectedd)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Basic Infomation",
                    Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(10.dp))


                Text(
                    text = "Gender",
                    Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomDropDown(
                    array = stringArrayResource(R.array.gender),
                    text = viewModel.gender,
                    onValueChange = {
                        viewModel.onEvent(DoctorBookingEvent.OnGenderChange(it))
                    },
                    hint = stringResource(id = R.string.select_gender),
                    modifier = Modifier.padding(horizontal = 20.dp),
                    isError = viewModel.genderValid,
                    errorMes = viewModel.genderErrMsg
                )

                Text(
                    text = "Current Weight (KG)",
                    Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                CommonTextFiled(
                    hint = "Current Weight ",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next, text = viewModel.weight,
                    onValueChange = {
                        viewModel.onEvent(DoctorBookingEvent.OnWeightChange(it))
                    },
                    errorMes = viewModel.weightErrMsg,
                    isError = viewModel.weightValid,
                )

                Text(
                    text = "Current Height (CM)",
                    Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                CommonTextFiled(
                    hint = "Current Height ",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next, text = viewModel.height,
                    onValueChange = {
                        viewModel.onEvent(DoctorBookingEvent.OnHeightChange(it))
                    },
                    errorMes = viewModel.heightErrMsg,
                    isError = viewModel.heightValid,
                )

                Text(
                    text = "Age",
                    Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 14.sp,
                )

                Spacer(modifier = Modifier.height(10.dp))

                CommonTextFiled(
                    hint = "Age ",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done, text = viewModel.age,
                    onValueChange = {
                        viewModel.onEvent(DoctorBookingEvent.OnAgeChange(it))
                    },
                    errorMes = viewModel.ageErrMsg,
                    isError = viewModel.ageValid,
                )


                Text(
                    text = "patientPhoneNumber",
                    Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 14.sp,
                )

                Spacer(modifier = Modifier.height(10.dp))

                CommonTextFiled(
                    hint = "patientPhoneNumber ",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done, text = viewModel.patientPhoneNumber,
                    onValueChange = {
                        viewModel.onEvent(DoctorBookingEvent.OnPatientPhoneNumberChange(it))
                    },
                    errorMes = viewModel.patientPhoneNumberMsg,
                    isError = viewModel.patientPhoneNumberValid,
                )
                Spacer(modifier = Modifier.height(20.dp))

                CommonButton("Process",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    shape = RoundedCornerShape(50),
                    onClick = {
                        sharedViewModel.addBookingProcess(
                            model = BookingProcess(
                                sharedViewModel.doctorModel!!,
                                bookingType = tabPage.name,
                                appointmentType = "Audio call",
                                basic_gender = viewModel.gender,
                                height = viewModel.height,
                                weight = viewModel.weight,
                                age = viewModel.age
                            )
                        )
                        viewModel.onEvent(DoctorBookingEvent.OnSubmitClick)
                    })
            }
        }
    }
}

@Composable
fun DoctorBookingProcessSecondScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController, sharedViewModel: DoctorSharedViewModel,
    modifier: Modifier = Modifier,
    onPopBackStack: () -> Unit
) {
    val context = LocalContext.current
    val model = sharedViewModel.bookingProcessModel

    val c: Date = Calendar.getInstance().getTime()
    println("Current time => $c")
    val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
    var date = df.format(c)
    Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = { navController.popBackStack() },
            title = "Booking"
        )
    }) {
        val innerPadding = it
        Column() {
            Text(
                text = "Select Date And Time",
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Italic,
                maxLines = 3,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalCalendarView(modifier = Modifier,
                selectedTextColor = Color.White,
                unSelectedTextColor = Color.Black,
                selectedCardColor = Color.Blue,
                unSelectedCardColor = Color.LightGray,
                onDayClick = { day ->
                    date = day.fullDate
                })

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Time",
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Italic,
                maxLines = 3,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            val itemList = remember { AppointmentType() }
            var selectedAppointmentTime by remember { mutableStateOf("09:00 AM") }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(itemList.time, key = { it.categories }) {
                    if (it.isSelected) selectedAppointmentTime = it.categories
                    CategoryItem(it, itemList::onItemSelectedTime)
                }
            }
            CommonButton("Process",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                shape = RoundedCornerShape(50),
                onClick = {
                    if (date.isNotEmpty()) {
                        sharedViewModel.addBookingProcess(
                            model!!.copy(
                                date = date,
                                time = selectedAppointmentTime
                            )
                        )
                        onNavigate(UIEvent.Navigate(Routes.DOCTOR_BOOKING_PROCESS_THIRD_SCREEN))
                    } else {
                        Toast.makeText(context, "Please Select a date", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }
}


@Composable
fun DoctorBookingProcessThirdScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController, viewmodel: HomeViewModel = hiltViewModel(),
    sharedViewModel: DoctorSharedViewModel,
    onPopBackStack: () -> Unit
) {

    val model = sharedViewModel.bookingProcessModel
    Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = { navController.popBackStack() },
            title = "Booking"
        )
    }) {
        val innerPadding = it
        var selectedOption = ""
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Column() {

                Text(
                    text = "Payments Options",
                    Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 20.sp
                )
                val option = RedioOPtionForPayment() {
                    selectedOption = it
                }
            }
            CommonButton("Process",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                shape = RoundedCornerShape(50),
                onClick = {
                    sharedViewModel.addBookingProcess(
                        model!!.copy(
                            paymentOption = selectedOption
                        )
                    )
                    CoroutineScope(Dispatchers.IO).launch {
                        viewmodel.insertAppointment(sharedViewModel.bookingProcessModel!!)
                    }
                    onNavigate(UIEvent.Navigate(Routes.CONFORM_DOCTOR_APPOINTMENT))
                })
        }
    }
}


@Composable
fun ConformDoctorAppointment(
    onNavigate: (UIEvent.Navigate) -> Unit, navController: NavController
) {
    Scaffold(topBar = {
        CommonAppBar(
            navigationIconAction = {
                navController.popBackStack(
                    BottomBarScreen.Home.route, inclusive =
                    false
                )
            },
            title = "Shopping Address"
        )
    }) { paddingValues ->

        val innerPaddings = paddingValues
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.coform_doctor_booking))
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
            )

            Text(
                text = "Your Appointment with Dr.Hanif was Successfully booked 🥳",
                Modifier.padding(20.dp),
                fontWeight = FontWeight.Medium,
                fontFamily = DMSans,
                fontStyle = FontStyle.Normal, textAlign = TextAlign.Center,
                maxLines = 3,
                fontSize = 14.sp
            )
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddPaymentCardScreen(
    onNavigate: (UIEvent.Navigate) -> Unit, navController: NavController
) {
    //AddPaymentCard()
}


enum class TabPage {
    Self, Other
}

@Composable
private fun HomeTabIndicator(
    tabPositions: List<TabPosition>, tabPage: TabPage
) {
    val transition = updateTransition(
        tabPage, label = "Tab indicator"
    )
    val indicatorLeft by transition.animateDp(
        transitionSpec = {
            if (TabPage.Self isTransitioningTo TabPage.Other) {
                // Indicator moves to the right.
                // Low stiffness spring for the left edge so it moves slower than the right edge.
                spring(stiffness = Spring.StiffnessVeryLow)
            } else {
                // Indicator moves to the left.
                // Medium stiffness spring for the left edge so it moves faster than the right edge.
                spring(stiffness = Spring.StiffnessMedium)
            }
        }, label = "Indicator left"
    ) { page ->
        tabPositions[page.ordinal].left
    }
    val indicatorRight by transition.animateDp(
        transitionSpec = {
            if (TabPage.Self isTransitioningTo TabPage.Other) {
                // Indicator moves to the right
                // Medium stiffness spring for the right edge so it moves faster than the left edge.
                spring(stiffness = Spring.StiffnessMedium)
            } else {
                // Indicator moves to the left.
                // Low stiffness spring for the right edge so it moves slower than the left edge.
                spring(stiffness = Spring.StiffnessVeryLow)
            }
        }, label = "Indicator right"
    ) { page ->
        tabPositions[page.ordinal].right
    }
    val color by transition.animateColor(
        label = "Border color"
    ) { page ->
        if (page == TabPage.Self) Purple700 else Purple700
    }
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(4.dp)
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, color), RoundedCornerShape(4.dp)
            )
    )
}

@Composable
private fun HomeTabBar(
    backgroundColor: Color, tabPage: TabPage, onTabSelected: (tabPage: TabPage) -> Unit
) {
    TabRow(
        selectedTabIndex = tabPage.ordinal,
        backgroundColor = Color.Transparent, modifier = Modifier.padding(horizontal = 20.dp),
        indicator = { tabPositions ->
            HomeTabIndicator(tabPositions, tabPage)
        },
    ) {
        HomeTab(
            onClick = { onTabSelected(TabPage.Self) }, title = "My Self"
        )
        HomeTab(title = "Other Person", onClick = { onTabSelected(TabPage.Other) })
    }
}

@Composable
private fun HomeTab(
    title: String, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontFamily = DMSans,
            fontStyle = FontStyle.Italic,
        )
    }
}


data class SubsCategories(
    val categories: String, @DrawableRes val image: Int,
    val isSelected: Boolean = false,
)

class AppointmentType {
    val AppTYpe = mutableStateListOf(
        SubsCategories("Audio Call", R.drawable.cardiologist, true),
        /* SubsCategories("Video Call", R.drawable.cardiologist),
         SubsCategories("Message", R.drawable.dental),*/
    )

    val time = mutableStateListOf(
        SubsCategories("08:00 AM", R.drawable.cardiologist, true),
        SubsCategories("08:30 AM", R.drawable.cardiologist),
        SubsCategories("09:00 AM", R.drawable.cardiologist),
        SubsCategories("09:30 AM", R.drawable.cardiologist),
        SubsCategories("10:00 AM", R.drawable.dental),
        SubsCategories("10.30 AM", R.drawable.cardiologist),
        SubsCategories("11:00 AM", R.drawable.dental),
        SubsCategories("11:30 AM", R.drawable.dental),
        SubsCategories("12:00 PM", R.drawable.dental),
        SubsCategories("12:30 PM", R.drawable.dental),
        SubsCategories("03:00 PM", R.drawable.dental),
        SubsCategories("03:30 PM", R.drawable.dental),
        SubsCategories("04:00 PM", R.drawable.dental),
        SubsCategories("04:30 PM", R.drawable.dental),
        SubsCategories("05:00 PM", R.drawable.dental),
        SubsCategories("5:30 PM", R.drawable.dental),
        SubsCategories("6:00 PM", R.drawable.dental),
        SubsCategories("7:00 PM", R.drawable.dental),
        SubsCategories("8:00 PM", R.drawable.dental),
        SubsCategories("9:00 PM", R.drawable.dental),
    )

    // were updating the entire list in a single pass using its iterator
    fun onItemSelectedd(selectedItemData: SubsCategories) {
        val iterator = AppTYpe.listIterator()

        while (iterator.hasNext()) {
            val listItem = iterator.next()

            iterator.set(
                if (listItem.categories == selectedItemData.categories) {
                    selectedItemData
                } else {
                    listItem.copy(isSelected = false)
                }
            )
        }
    }

    /** *For Date and Time  */
    fun onItemSelectedTime(selectedItemData: SubsCategories) {
        val iterator = time.listIterator()

        while (iterator.hasNext()) {
            val listItem = iterator.next()

            iterator.set(
                if (listItem.categories == selectedItemData.categories) {
                    selectedItemData
                } else {
                    listItem.copy(isSelected = false)
                }
            )
        }
    }
}


@Composable
fun RedioOPtionForPayment(onvalueChange: (String) -> Unit) {
    val radioOptions = listOf("Google Pay", "Apple Pay", "Paypal", "Debit card", "Credit Card")

    var selectedItem by remember {
        mutableStateOf(radioOptions[0])
    }

    Column(modifier = Modifier.selectableGroup()) {
        radioOptions.forEach { label ->

            Card(
                Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                shape = RoundedCornerShape(20)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (selectedItem == label),
                            onClick = {
                                selectedItem = label
                                onvalueChange(label)
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(24.dp),
                        painter = if (selectedItem == label) painterResource(id = R.drawable.check_circle) else painterResource(
                            id = R.drawable.circle
                        ),
                        // screen readers will read the Text() compsable content
                        // if we pass label here, they end up reading the content twice
                        // so, pass null
                        contentDescription = null,
                        tint = Color.Magenta
                    )
                    Text(text = label)
                }
            }
        }
    }

}
