package com.example.caltracker.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.caltracker.viewModel.ServiceDataViewModel
import com.example.caltracker.viewModel.UserDataViewModel
import com.example.caltracker.widgets.CustomBottomAppBar
import com.example.caltracker.widgets.CustomTopAppBar
import com.example.caltracker.R
import com.example.caltracker.ui.theme.PrimaryVariant
import com.example.caltracker.ui.theme.Purple700
import com.example.caltracker.ui.theme.Secondary
import com.example.caltracker.widgets.CustomButton
import kotlin.system.exitProcess


@Composable
fun Profile(navController: NavHostController, vm1: UserDataViewModel = viewModel(), vm2: ServiceDataViewModel = viewModel()) {

    val getData = vm1.state.value
    val getService = vm2.state.value


     Scaffold(
            topBar = {
                CustomTopAppBar(
                    navController = navController,
                    title = "User Profile",
                    showBackIcon = false
                )
            },
            bottomBar = { CustomBottomAppBar(navController = navController) },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,

                ) {
//                    Icon(imageVector = Icons.Filled.PermIdentity, contentDescription = "profile", modifier = Modifier.size(128.dp))
                    Spacer(modifier = Modifier.padding(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.screenshot_2023_06_02_191753),
                        contentDescription = stringResource(id = R.string.logo_desc),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(180.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Card (
                        elevation = 10.dp,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Name : " + getData.name)
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(text = "Email : " + getData.email)
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(text = "Phone : " + getData.phone)
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.screenshot_2023_06_02_191226),
                        contentDescription = stringResource(id = R.string.logo_desc),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(180.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    CustomButton(
                        btnText = "Logout",
                        onClickFun = {
                            //activity?.finish()
                            exitProcess(0)
                        },
                        btnColor = Secondary
                    )
                }
            }
        )
}

