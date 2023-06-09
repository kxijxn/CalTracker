package com.example.caltracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.caltracker.widgets.CustomBottomAppBar
import com.example.caltracker.widgets.CustomTopAppBar
import com.google.firebase.auth.FirebaseAuth


@Composable
fun Notice(navController: NavHostController, auth: FirebaseAuth) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "History",
                showBackIcon = false
            )
        },
        bottomBar = { CustomBottomAppBar(navController = navController) },
        content = { padding ->

                Column (modifier = Modifier.verticalScroll(rememberScrollState())){
                    Card(
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.padding(12.dp),
                        elevation = 6.dp

                    ) {

                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "17/05/2022 - Wednesday",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 25.sp, fontWeight = FontWeight.Bold
                            )
                        )

                        Spacer(modifier = Modifier.height(5.dp))


                        Text(

                            text = "Calories for 17/05/2022 - Wednesday",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            style = TextStyle(

                                fontSize = 20.sp

                            )

                        )

                        Text(

                            text = "Breakfast - Eggs, Bread (200kcal)\n\n" +
                                    "Lunch - Fried Rice (168kcal)\n\n"+
                                    "Dinner - Noodles (100kcal)\n",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontSize = 14.sp

                            )

                        )
                    }
                }
                    Card(
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.padding(12.dp),
                        elevation = 6.dp

                    ) {

                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = "16/05/2022 - Tuesday",
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 25.sp, fontWeight = FontWeight.Bold
                                )
                            )

                            Spacer(modifier = Modifier.height(5.dp))


                            Text(

                                text = "Calories for 16/05/2022 - Tuesday",
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = TextStyle(

                                    fontSize = 20.sp

                                )

                            )

                            Text(

                                text = "Breakfast - Eggs, Bread (200kcal)\n\n" +
                                        "Lunch - Fried Rice (168kcal)\n\n"+
                                        "Dinner - Noodles (100kcal)\n",
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    fontSize = 14.sp

                                )

                            )
                        }
                    }

                    Card(
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.padding(12.dp),
                        elevation = 6.dp

                    ) {

                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = "15/05/2022 - Monday",
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 25.sp, fontWeight = FontWeight.Bold
                                )
                            )

                            Spacer(modifier = Modifier.height(5.dp))


                            Text(

                                text = "Calories for 15/05/2022 - Monday",
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = TextStyle(

                                    fontSize = 20.sp

                                )

                            )

                            Text(

                                text = "Breakfast - Eggs, Bread (200kcal)\n\n" +
                                        "Lunch - Fried Rice (168kcal)\n\n"+
                                        "Dinner - Noodles (100kcal)\n",
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    fontSize = 14.sp

                                )

                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.padding(30.dp))
            }
        }
    )
}