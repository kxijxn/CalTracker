package com.example.caltracker.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.RunCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.caltracker.R
import com.example.caltracker.nav.Routes
import com.example.caltracker.ui.theme.PrimaryVariant
import com.example.caltracker.viewModel.UserDataViewModel
import com.example.caltracker.widgets.CustomBottomAppBar
import com.example.caltracker.widgets.CustomButton
import com.example.caltracker.widgets.CustomTopAppBar
import com.example.caltracker.widgets.IconButton
import kotlin.system.exitProcess


@Composable
fun HomePage(navController: NavHostController,vm: UserDataViewModel = viewModel()) {

    val getData = vm.state.value

    var height by remember {
        mutableStateOf("")
    }
    var weight by remember {
        mutableStateOf("")
    }
    var results by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Homepage",
                showBackIcon = false
            )
        },
        bottomBar = { CustomBottomAppBar(navController = navController)},
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .verticalScroll(
                        rememberScrollState()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Spacer(modifier = Modifier.padding(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.logo_desc),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(180.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = "Welcome back " + getData.name + "!",
                    style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.padding(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    IconButton(onClickFun = { navController.navigate(Routes.Profile.route) }, icon = Icons.Default.PermIdentity )
                    IconButton(onClickFun = { navController.navigate(Routes.Service.route) }, icon = Icons.Default.RunCircle)
                    IconButton(onClickFun = { navController.navigate(Routes.Notice.route) }, icon = Icons.Default.History)
                }

                Spacer(modifier = Modifier.padding(16.dp))

                Text(text = "Your calorie intake for today is 1000 kcal", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))

                Spacer(modifier = Modifier.padding(12.dp))

                TextField(
                    value = height,
                    onValueChange = { height = it },
                    label =
                        {
                            Text(text = "Height (CM)")
                        }
                )

                TextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label =
                    {
                        Text(text = "Weight (KG)")
                    }
                )

                Spacer(modifier = Modifier.padding(12.dp))

                Button(
                    onClick = {
                        results = calculateBMI(
                            height = height.toDouble(),
                            weight = weight.toDouble()
                        )
                    },
                    modifier = Modifier.padding(5.dp)
                )
                {
                    Text(text = "Calculate")
                }

                if(results.isNotBlank())
                {
                    Text(text = results)
                }

                Spacer(modifier = Modifier.padding(10.dp))

                val activity = (LocalContext.current as? Activity)

            }
        }
    )
}

private fun calculateBMI(height:Double, weight:Double):String
{
    val bmiIndex = weight / (height * height) * 10000
    return "Your BMI index is $bmiIndex"
}