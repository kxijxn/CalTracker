package com.example.caltracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.caltracker.nav.Routes
import com.example.caltracker.models.ServiceData
import com.example.caltracker.models.serviceList
import com.example.caltracker.ui.theme.Primary
import com.example.caltracker.ui.theme.PrimaryVariant
import com.example.caltracker.ui.theme.Secondary
import com.example.caltracker.widgets.CustomBottomAppBar
import com.example.caltracker.widgets.CustomButton
import com.example.caltracker.widgets.CustomTopAppBar
import com.google.firebase.auth.FirebaseAuth


@Composable
fun Service(navController: NavHostController, auth: FirebaseAuth) {

    var itemSelection by remember{ mutableStateOf(-1) }
    val items by remember {
        mutableStateOf(serviceList)
    }
    var selectedService by remember {
        mutableStateOf("")
    }


    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Track Calories",
                showBackIcon = false
            )
        },
        bottomBar = { CustomBottomAppBar(navController = navController) },
        content = { padding ->
            LinearProgressIndicator(
                progress = 0.35f,
                modifier = Modifier.fillMaxWidth(),
                color = PrimaryVariant
            )//35% progress
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {

                Row(

                ) {
                    Icon(imageVector = Icons.Default.LocationSearching , contentDescription = "location" )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(text = "Your location has been tracked.")
                    Spacer(modifier = Modifier.padding(10.dp))
                }

                LazyColumn{
                    itemsIndexed(items){ indexNumber, string ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp, 6.dp, 12.dp, 6.dp)
                                .wrapContentHeight(
                                    align = CenterVertically
                                )
                                .selectable(
                                    selected = itemSelection == indexNumber,
                                    onClick = {

                                        itemSelection = indexNumber

                                        selectedService = items[itemSelection].item

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
                                    verticalAlignment = CenterVertically
                                ) {
                                    Column {
                                        Text(text = items[indexNumber].item, color = Primary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.padding(3.dp))
//                                        Text(text = "pricing starts from RM 40", color = Primary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }



                val selected = ServiceData(service = selectedService)

                CustomButton(
                    btnText = "Confirm",
                    onClickFun =
                    {
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set("selectedService", selected)
                        }
                        navController.navigate(Routes.Mechanic.route)
                    },
                    btnColor = Secondary
                )

            }
        }
    )
}
