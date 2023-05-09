package com.hanif.medical.models

import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateListOf
import com.hanif.medical.R


data class SubsCategories(
    val categories: String,@DrawableRes val image:Int,
    val isSelected: Boolean = false,
)

class ListOfCategories {
    val itemDataList = mutableStateListOf(
        SubsCategories("all", R.drawable.cardiologist,true),
        SubsCategories("Cardiologist", R.drawable.cardiologist),
        SubsCategories("Dental", R.drawable.dental),
        SubsCategories("physcologist", R.drawable.brain),
        SubsCategories("Skin Specialist", R.drawable.cardiologist),
        SubsCategories("eye's", R.drawable.eye),
        SubsCategories("Other", R.drawable.cardiologist),
    )

    // were updating the entire list in a single pass using its iterator
    fun onItemSelected(selectedItemData: SubsCategories) {
        val iterator = itemDataList.listIterator()

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

data class AllSubscriptionItemList(
    val subsName: String,
    val date: String,
    val price: String,
    val duration: String,
    val categories: String,
)


val itemDataList = mutableStateListOf(
    AllSubscriptionItemList("Netflix", "24, April 2023", "₹ 1,499", "Annual", "Entertainment"),
    AllSubscriptionItemList("Spotify", "24, April 2023", "₹ 1,499", "Annual", "Music"),
    AllSubscriptionItemList("Amazon Prime", "24, April 2023", "₹ 1,499", "Annual", "SIP"),
    AllSubscriptionItemList("Cheezy Code", "24, April 2023", "₹ 1,499", "Annual", "Education"),
    AllSubscriptionItemList("LIC", "24, April 2023", "₹ 1,499", "Annual", "Other"),
    AllSubscriptionItemList("Spotify1", "24, April 2023", "₹ 1,499", "Annual", "Music"),
    AllSubscriptionItemList("Amazon Prime", "24, April 2023", "₹ 1,499", "Annual", "SIP"),
    AllSubscriptionItemList("Cheezy Code1", "24, April 2023", "₹ 1,499", "Annual", "Education"),
    AllSubscriptionItemList("LIC", "24, April 2023", "₹ 1,499", "Annual", "Other"),
    AllSubscriptionItemList("Netflix1", "24, April 2023", "₹ 1,499", "Annual", "Entertainment"),
)


val getPieChartData = mapOf(
    Pair("Chrome", 34.68F),
    Pair("Firefox", 16.60F),
    Pair("Safari", 16.15F),
    Pair("Internet Explorer", 15.62F),
    Pair("Internet Explorer1", 15.62F),
)

data class PieChartData(
    var browserName: String,
    var value: Float,
)

val getPieChartData1 = listOf(
    PieChartData("Chrome", 34.68F),
    PieChartData("Firefox", 16.60F),
    PieChartData("Safari", 16.15F),
    PieChartData("Internet Explorer", 15.62F),
)