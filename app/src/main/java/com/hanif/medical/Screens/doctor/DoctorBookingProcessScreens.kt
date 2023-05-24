package com.hanif.medical.Screens.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonAppBar
import com.hanif.medical.ui.theme.DMSans

@Preview
@Composable
fun DoctorBookingProcessFirstScreens() {
    Scaffold(topBar = { CommonAppBar(navigationIconAction = { /*TODO*/ }, title = "Booking") }) {
        val innerPadding = it
        Column {
            androidx.compose.material3.Card(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(20.dp), shape = RoundedCornerShape(30.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        modifier =
                        Modifier
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
                text = "Appointment Type",
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans,
                fontStyle = FontStyle.Italic,
                maxLines = 3,
                fontSize = 16.sp
            )

            val chipList: List<String> = listOf(
                "Online", "In Person"
            )

            var selected by remember { mutableStateOf(chipList[0]) }

            Row(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
            ) {
                chipList.forEach { it ->
                    Chip(
                        title = it,
                        selected = selected,
                        onSelected = {
                            selected = it
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun Chip(
    title: String,
    selected: String,
    onSelected: (String) -> Unit
) {

    val isSelected = selected == title

    val background = if (isSelected) Color.Blue else Color.LightGray
    val contentColor = if (isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .padding(end = 10.dp)
            .height(35.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(
                onClick = {
                    onSelected(title)
                }
            )
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = title, color = contentColor, fontSize = 16.sp)
        }
    }

    

}

