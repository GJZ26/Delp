package up.chiapas.delp.register.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import up.chiapas.delp.R
import up.chiapas.delp.core.shared.presentation.composable.clickable.InternalButton
import up.chiapas.delp.core.shared.presentation.composable.clickable.InternalTextButton
import up.chiapas.delp.core.shared.presentation.composable.clickable.TextButtonType
import up.chiapas.delp.core.shared.presentation.composable.input.FormInput
import up.chiapas.delp.core.shared.presentation.composable.input.FormPasswordInput
import up.chiapas.delp.core.shared.presentation.composable.text.Title
import up.chiapas.delp.core.navigation.Home
import up.chiapas.delp.register.presentation.viewmodels.RegisterViewModel
import up.chiapas.delp.ui.theme.Purple
import up.chiapas.delp.ui.theme.White

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
        Spacer(Modifier.padding(50.dp))
        Text(stringResource(R.string.register_instruction), style = MaterialTheme.typography.headlineSmall)

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
                    navController.navigate(Home) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive =
                                true
                        }
                        launchSingleTop =
                            true
                    }
                }
            }
        }

        Spacer(Modifier.padding(5.dp))

        Text(
            "Already have an account?",
            style = MaterialTheme.typography.labelSmall,
            color = White
        )

        InternalTextButton("Sign in", TextButtonType.LINK) {
            navController.popBackStack()
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