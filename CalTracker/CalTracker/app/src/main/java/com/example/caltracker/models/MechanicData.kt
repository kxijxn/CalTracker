package com.example.caltracker.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MechanicData(
    val service: String,
    val mechanic: String
) : Parcelable
