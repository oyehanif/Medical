package com.hanif.medical.models

import android.media.Image
import androidx.annotation.DrawableRes
import com.hanif.medical.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title :String,
    val desc :String
){

    object First : OnBoardingPage(
        R.drawable.img,
        "Hanif",
        "This is 1st rank Doctor in India."
    )

    object Second : OnBoardingPage(
        R.drawable.img,
        "Hanif",
        "This is 1st rank Doctor in India."
    )

    object Third : OnBoardingPage(
        R.drawable.img,
        "Hanif",
        "This is 1st rank Doctor in India."
    )
}
