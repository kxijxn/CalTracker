package com.example.caltracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.caltracker.nav.Routes
import com.example.caltracker.models.MechanicData
import com.example.caltracker.models.ServiceData
import com.example.caltracker.models.mechanicList
import com.example.caltracker.ui.theme.Primary
import com.example.caltracker.ui.theme.PrimaryVariant
import com.example.caltracker.ui.theme.Purple700
import com.example.caltracker.ui.theme.Secondary
import com.example.caltracker.widgets.CustomButton
import com.example.caltracker.widgets.CustomTopAppBar



@Composable
fun Mechanic(navController: NavHostController){

    var itemSelection by remember{ mutableStateOf(-1) }
    val items by remember {
        mutableStateOf(mechanicList)
    }
    var selectedMechanic by remember {
        mutableStateOf("")
    }

    val selectedService = navController.previousBackStackEntry?.savedStateHandle?.get<ServiceData>(
        "selectedService"
    )


    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Foods",
                showBackIcon = true
            )
        },
        content = { padding ->
            LinearProgressIndicator(
                progress = 0.7f,
                modifier = Modifier.fillMaxWidth(),
                color = PrimaryVariant
            )//70% progress
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {

                LazyColumn{
                    itemsIndexed(items){ indexNumber, string ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp, 6.dp, 12.dp, 6.dp)
                                .wrapContentHeight(
                                    align = Alignment.CenterVertically
                                )
                                .selectable(
                                    selected = itemSelection == indexNumber,
                                    onClick = {
                                        itemSelection = indexNumber

                                        selectedMechanic = items[itemSelection].name
                                    }
                                ),
                            elevation = 6.dp,
                            shape = RoundedCornerShape(10.dp),
                            backgroundColor =
                            if (itemSelection == indexNumber){
                                com.example.caltracker.ui.theme.Surface
                            } else {
                                Secondary

                            }
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column() {
                                        Icon(imageVector = Icons.Default.FoodBank, contentDescription = "Mechanic Profile")
                                        Text(text = items[indexNumber].name, color = Primary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.padding(3.dp))
                                        Text(text = items[indexNumber].distance, color = Primary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }


                val serv = selectedService?.service
                val allData = serv?.let { MechanicData(service = it, mechanic = selectedMechanic) }

                CustomButton(
                    btnText = "Confirm",
                    onClickFun = {
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set("allData", allData)
                        }
                        navController.navigate(Routes.Payment.route)
                    },
                    btnColor = Secondary
                )

            }
        }
    )
}