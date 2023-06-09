package com.example.caltracker.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.caltracker.nav.Routes
import com.example.caltracker.ui.theme.Primary
import com.example.caltracker.ui.theme.PrimaryVariant
import com.example.caltracker.viewModel.UserRegistrationViewModel
import com.example.caltracker.widgets.CustomButton
import com.example.caltracker.widgets.CustomOutlinedTextField
import com.example.caltracker.widgets.CustomTopAppBar
import com.google.firebase.auth.FirebaseAuth
import com.example.caltracker.R
import com.example.caltracker.ui.theme.Secondary

@Composable
fun UserRegistration (navController: NavHostController, auth: FirebaseAuth, vm: UserRegistrationViewModel = viewModel()){


    val context = LocalContext.current
    val scrollState = rememberScrollState()


    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable{ mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var validateName by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePhone by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateNameError = "Plaase input a valid name"
    val validateEmailError = "Please input a valid email"
    val validatePhoneError = "Please enter a valid Malaysia phone number"
    val validatePasswordError = "Please mix uppercase and lowercase letters and a number with a minimum length of 8 "


    fun validateData(name: String, email:String, phone: String, password: String): Boolean {
        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}".toRegex()
        //val passwordRegex = "(^\\d+\$).([a-zA-z]*).{8,}".toRegex()

        validateName = name.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePhone = Patterns.PHONE.matcher(phone).matches()
        validatePassword = passwordRegex.matches(password)

        return validateName && validateEmail && validatePhone && validatePassword
    }

    fun register (
        name: String,
        email: String,
        phone: String,
        password: String
    ) {
        if (validateData(name, email, phone, password)) {

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    vm.addDataToFirebase(
                        name = name,
                        email = email,
                        phone = phone,
                        context = context)
                    navController.navigate(Routes.Login.route) // testing

                } else {
                    Toast.makeText(context, "Please review the fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold (
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "User Registration",
                showBackIcon = true
            )
        }, content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = "Set Up Profile",
                    modifier = Modifier.padding(vertical = 20.dp),
                    color = Primary,
                    fontSize = 30.sp
                )

                CustomOutlinedTextField(
                    value = name,
                    onValueChangeFun = {name = it},
                    showError = !validateName,
                    labelText = stringResource(R.string.name_hint),
                    errorMessage = validateNameError,
                    leadingIconImageVector = Icons.Default.PermIdentity,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                CustomOutlinedTextField(
                    value = email,
                    onValueChangeFun = {email = it},
                    showError = !validateEmail,
                    labelText = stringResource(R.string.email_hint),
                    errorMessage = validateEmailError,
                    leadingIconImageVector = Icons.Default.AlternateEmail,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                CustomOutlinedTextField(
                    value = phone,
                    onValueChangeFun = {phone = it},
                    showError = !validatePhone,
                    labelText = stringResource(R.string.phone_hint),
                    errorMessage = validatePhoneError,
                    leadingIconImageVector = Icons.Default.Phone,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )
                )

                CustomOutlinedTextField(
                    value = password,
                    onValueChangeFun = {password = it},
                    showError = !validatePassword,
                    labelText = stringResource(R.string.password_hint),
                    errorMessage = validatePasswordError,
                    isPasswordField = true,
                    isPasswordVisible = isPasswordVisible,
                    onVisibilityChange = { isPasswordVisible = it},
                    leadingIconImageVector = Icons.Default.Password,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )

                CustomButton(
                    btnText = stringResource(id = R.string.register_button) ,
                    onClickFun = { register(name, email, phone, password) },
                    btnColor = Secondary
                )

            }
        }
    )
}




