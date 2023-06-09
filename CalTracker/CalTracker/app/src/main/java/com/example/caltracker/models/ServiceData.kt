package com.example.caltracker.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ServiceData(
    val service : String
) : Parcelable
