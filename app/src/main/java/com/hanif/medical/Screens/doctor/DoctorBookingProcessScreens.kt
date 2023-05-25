package com.hanif.medical.Screens.doctor

import android.widget.Toast
import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanif.medical.R
import com.hanif.medical.Screens.CategoryItem
import com.hanif.medical.Screens.CommonButton
import com.hanif.medical.Screens.CommonTextFiled
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.Screens.commo.CustomDropDown
import com.hanif.medical.models.ListOfCategories
import com.hanif.medical.models.SubsCategories
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.ui.theme.Purple500
import com.hanif.medical.ui.theme.Purple700
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent
import com.joelkanyi.horizontalcalendar.HorizontalCalendarView

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun DoctorBookingProcessFirstScreens() {
    Scaffold(topBar = { CommonAppBar(navigationIconAction = { /*TODO*/ }, title = "Booking") }) {
        val innerPadding = it
        Column(Modifier.verticalScroll(rememberScrollState())) {
            androidx.compose.material3.Card(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(20.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.img),
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
                                text = "DR. Hanif Shaikh",
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
                                text = "Dental",
                                Modifier.padding(horizontal = 10.dp),
                                fontWeight = FontWeight.Bold,
                                fontFamily = DMSans,
                                fontStyle = FontStyle.Italic,
                                maxLines = 3,
                                fontSize = 14.sp
                            )
                        }
                        Text(
                            text = "$30",
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

            var gender by rememberSaveable() {
                mutableStateOf("")
            }

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
                text = gender,
                onValueChange = {
                    /*viewModel.onEvent(
                        EditProfileEvents.EditGender(
                            gender = it
                        )
                    )*/
                    gender = it
                },
                hint = stringResource(id = R.string.select_gender),
                modifier = Modifier.padding(horizontal = 20.dp)
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
                imeAction = ImeAction.Next
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
                imeAction = ImeAction.Next
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
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            CommonTextFiled(
                hint = "Age ",
                modifier = Modifier.padding(horizontal = 20.dp),
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            )
            Spacer(modifier = Modifier.height(20.dp))

            CommonButton("Process",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                shape = RoundedCornerShape(50),
                onClick = {
                    // onNavigate(UIEvent.Navigate(Routes.SHOPPING_PRE_PAYMENT_SCREEN))
                })
        }
    }
}

@Preview
@Composable
fun DoctorBookingProcessSecondScreen() {
    val context = LocalContext.current
    Scaffold(topBar = { CommonAppBar(navigationIconAction = { /*TODO*/ }, title = "Booking") }) {
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
                    Toast.makeText(context, day.toString(), Toast.LENGTH_SHORT).show()
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
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(itemList.time, key = { it.categories }) {
                    CategoryItem(it, itemList::onItemSelectedd)
                }
            }

        }
    }
}

private enum class TabPage {
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
        SubsCategories("Video Call", R.drawable.cardiologist),
        SubsCategories("Message", R.drawable.dental),
    )

    val time = mutableStateListOf(
        SubsCategories("08:00 AM", R.drawable.cardiologist, true),
        SubsCategories("08:30 AM", R.drawable.cardiologist, true),
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
}
