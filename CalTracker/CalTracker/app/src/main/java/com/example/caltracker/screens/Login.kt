package com.example.caltracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.caltracker.R
import com.example.caltracker.nav.Routes
import com.example.caltracker.ui.theme.Primary
import com.example.caltracker.ui.theme.PrimaryVariant
import com.example.caltracker.ui.theme.Purple700
import com.example.caltracker.ui.theme.Secondary
import com.example.caltracker.viewModel.LoginViewModel
import com.example.caltracker.widgets.CustomButton
import com.example.caltracker.widgets.CustomDialogClose
import com.example.caltracker.widgets.CustomOutlinedTextField
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


@Composable
fun Login( navController: NavHostController, auth: FirebaseAuth, vm:LoginViewModel = viewModel()) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        //RoadHelpLogo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo_desc),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(10.dp))
        )


        CustomOutlinedTextField(
            value = vm.email,
            onValueChangeFun = {vm.email = it},
            labelText = stringResource(R.string.email_hint),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIconImageVector = Icons.Default.AlternateEmail
        )

        // Password
        var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

        CustomOutlinedTextField(
            value = vm.password,
            onValueChangeFun = { vm.password = it },
            labelText = stringResource(R.string.password_hint),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPasswordField = true,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChange = { isPasswordVisible = it},
            leadingIconImageVector = Icons.Default.Password,
        )

        // LoginButton
        var showLoginError by remember { mutableStateOf(false) }
        var isLoading by remember { mutableStateOf(false) }

        CustomButton(
            btnText = stringResource(R.string.login_button),
            btnColor = Secondary,
        onClickFun = {
            scope.launch {
                isLoading = true
                val data = vm.logInWithEmail()

                if(data!= null) {
                    navController.navigate(Routes.HomePage.route)
                } else {
                    showLoginError = true
                }
                isLoading = false
            }
        }

        )

        // RegisterLink

        ClickableText(
            text = AnnotatedString("Not a user? Register here"),
            modifier = Modifier.padding(20.dp),
            onClick = { navController.navigate(Routes.UserRegistration.route) },
            style = TextStyle(
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = Primary
            )
        )

        if (showLoginError) {
            CustomDialogClose(
                alertTitle = stringResource(id = R.string.login_error_header),
                alertBody = stringResource(id = R.string.login_error_desc),
                onDismissFun = { showLoginError = false },
                btnCloseClick = { showLoginError = false }
            )
        }
    }


}



