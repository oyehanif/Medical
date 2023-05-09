package com.hanif.medical.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hanif.medical.R
import com.hanif.medical.models.ListOfCategories
import com.hanif.medical.models.SubsCategories
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.graphs.UIEvent


@Composable
fun AllDoctorScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController, modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Doctors Category's",
            fontWeight = FontWeight.Bold,
            modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Start,
          fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(10.dp))
        val itemList = remember { ListOfCategories() }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(itemList.itemDataList, key = { it.categories }) {
                CategoryItem(it, itemList::onItemSelected)
            }
        }

        LazyColumn() {
            items(20) {
                DoctorItem {
                    onNavigate(UIEvent.Navigate(Routes.DETAIL_DOCTOR_SCREEN))
                }
            }
        }
    }
}


//category list item.
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)
@Composable
fun CategoryItem(subsCategories: SubsCategories, onSelectChanged: (SubsCategories) -> Unit) {

    Row {
        Chip(
            onClick = {
                onSelectChanged(subsCategories.copy(isSelected = !subsCategories.isSelected))
            },
            colors = ChipDefaults.chipColors(
                backgroundColor = if (subsCategories.isSelected) White else White
            ),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp))
        ) {
            Text(
                text = subsCategories.categories,
                color = if (subsCategories.isSelected) Gray else Black,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DoctorItem(onClick:() -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center, modifier = Modifier.clickable { onClick() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .clip(
                        CircleShape
                    )
                    .size(100.dp)
            )

            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    var isClicked by remember {
                        mutableStateOf(false)
                    }
                    Text(
                        text = "Dr. Hanif Shaikh",
                        fontSize = 20.sp,

                        fontWeight = FontWeight.Bold
                    )
                    val img =
                        if (isClicked) R.drawable.fill_favorite else R.drawable.withoutfill_favorite
                    Image(
                        painterResource(id = img),
                        contentDescription = "",
                        Modifier.clickable { isClicked = !isClicked })
                }
                Text(text = "Dental", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically
                ) {
                    Row(verticalAlignment = CenterVertically) {
                        Text(text = "4.5", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                        Image(
                            Icons.Default.Star,
                            modifier = Modifier.size(16.dp),
                            contentDescription = ""
                        )
                    }

                    Text(
                        text = "Book Now",
                        modifier = Modifier
                            .clip(RoundedCornerShape(40))
                            .background(
                                White
                            )
                            .padding(horizontal = 8.dp)
                    )

                }
            }
        }
    }
}
