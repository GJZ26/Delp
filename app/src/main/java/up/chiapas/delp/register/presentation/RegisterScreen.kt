package up.chiapas.delp.register.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import up.chiapas.delp.R
import up.chiapas.delp.core.composable.clickable.InternalButton
import up.chiapas.delp.core.composable.clickable.InternalTextButton
import up.chiapas.delp.core.composable.clickable.TextButtonType
import up.chiapas.delp.core.composable.input.FormInput
import up.chiapas.delp.core.composable.input.FormPasswordInput
import up.chiapas.delp.core.composable.text.Title
import up.chiapas.delp.core.navigation.Login
import up.chiapas.delp.ui.theme.Purple

@Composable
fun RegisterScreen(navController: NavController, registerViewModel: RegisterViewModel) {

    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val usernameText: String by registerViewModel.username.observeAsState("")
    val emailText: String by registerViewModel.email.observeAsState("")
    val passwordText: String by registerViewModel.password.observeAsState("")
    val retypedPasswordText by registerViewModel.re_password.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Purple),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Title(true)
        Text(stringResource(R.string.register_instruction))

        FormInput(usernameText, "Username", Icons.Filled.AccountCircle, "User Icon") {
            registerViewModel.onChangeUsername(it)
        }

        FormInput(emailText, "Email", Icons.Filled.Email, "Email Icon") {
            registerViewModel.onChangeEmail(it)
        }

        FormPasswordInput(passwordText, "Password") {
            registerViewModel.onChangePassword(it)
        }

        FormPasswordInput(retypedPasswordText, "Re-type the password") {
            registerViewModel.onChangeRePassword(it)
        }

        InternalButton("Create account", !isLoading) {
            coroutineScope.launch {
                isLoading = true
                val response = registerViewModel.submit()
                isLoading = false
                if (!response.success) {
                    showError = true
                    errorMessage = response.message
                } else {
                    Log.i("Delp", "Se logró!!!")
                }
            }
        }

        Text(
            "Already have an account?"
        )

        InternalTextButton("Sign in", TextButtonType.LINK) {
            navController.navigate(Login)
        }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),

            )

        if (showError) {
            LaunchedEffect(true) {
                snackBarHostState.showSnackbar(errorMessage)
                showError = false
            }
        }
    }
}