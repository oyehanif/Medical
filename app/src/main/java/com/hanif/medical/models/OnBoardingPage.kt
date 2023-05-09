package com.hanif.medical.models

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.hanif.medical.R

sealed class OnBoardingPage(
    @RawRes
    val image: Int,
    val title: String,
    val desc: String
) {

    object First : OnBoardingPage(
        R.raw.first,
        "Take Prescription from a doctor",
        "Get easy access to consultation, treatment and care from qualified doctors"
    )

    object Second : OnBoardingPage(
        R.raw.secound,
        "24 Hours service for patients",
        "Find a Doctor anytime online and get best care of your health ."
    )

    object Third : OnBoardingPage(
        R.raw.therd,
        "Take Service Online ",
        "Now you can take direct doctor service by video calling."
    )
}
