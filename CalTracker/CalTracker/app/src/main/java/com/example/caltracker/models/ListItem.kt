package com.example.caltracker.models

data class ListItem(
    val item: String,
    val isSelected: Boolean,
)

val serviceList = listOf(
    ListItem("Breakfast", false),
    ListItem("Lunch", false),
    ListItem("Teatime", false),
    ListItem("Dinner", false),
    ListItem("Brunch", false),
    ListItem("Supper", false)
)
