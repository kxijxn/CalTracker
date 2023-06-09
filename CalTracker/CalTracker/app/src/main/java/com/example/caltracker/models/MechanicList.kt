package com.example.caltracker.models

data class MechanicList(
    val name: String,
    val isSelected: Boolean,
    val distance: String
)

val mechanicList = listOf(
    MechanicList("Noodles", false, "138 Calories"),
    MechanicList("Rice", false, "130 Calories"),
    MechanicList("Bread", false, "265 Calories"),
    MechanicList("Eggs", false, "155 Calories"),
    MechanicList("Fried Rice", false, "165 Calories"),

)

