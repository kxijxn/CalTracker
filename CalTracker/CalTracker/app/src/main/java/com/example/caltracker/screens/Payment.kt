package com.example.caltracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.caltracker.nav.Routes
import com.example.caltracker.models.MechanicData
import com.example.caltracker.ui.theme.PrimaryVariant
import com.example.caltracker.ui.theme.Secondary
import com.example.caltracker.viewModel.ServiceViewModel
import com.example.caltracker.widgets.CustomBottomAppBar
import com.example.caltracker.widgets.CustomButton
import com.example.caltracker.widgets.CustomTopAppBar



@Composable
fun Payment(navController: NavHostController, vm:ServiceViewModel = viewModel())   {

    val context = LocalContext.current
    val allData = navController.previousBackStackEntry?.savedStateHandle?.get<MechanicData>(
        "allData"
    )



    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Confirmation",
                showBackIcon = true
            )
        },
        bottomBar = { CustomBottomAppBar(navController = navController) },
        content = { padding ->
            LinearProgressIndicator(
                progress = 1f,
                modifier = Modifier.fillMaxWidth(),
                color = PrimaryVariant
            )//100% progress
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {

                var selectedPayMethod :String = ""

                Text(
                    text = "Confirmation Page",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Cursive
                    )
                )

                Card {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column{
                                if (allData != null) {
                                    Text(text = "Selected : " + allData.service)
                                    Spacer(modifier = Modifier.padding(10.dp))
                                    Text(text = "Selected food : " + allData.mechanic)
                                    Spacer(modifier = Modifier.padding(10.dp))
//                                    Text(text = "Total price : RM80.00")
                                }
                            }
                        }
                    }
                }

                CustomButton(
                    btnText = "Confirm",
                    onClickFun = {
                        if (allData != null) {
                            vm.addServicePaymentToFirebase(
                                service = allData.service,
                                mechanic = allData.mechanic,
                                payment = selectedPayMethod,
                                context = context
                            )
                        }
                        navController.navigate(Routes.HomePage.route)
                    },
                    btnColor = Secondary
                )


            }
        }
    )
}


