package up.chiapas.delp.login.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import up.chiapas.delp.core.navigation.Register
import up.chiapas.delp.login.presentation.viewmodels.LoginViewModel
import up.chiapas.delp.ui.theme.Purple
import up.chiapas.delp.ui.theme.White

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val emailText: String by loginViewModel.email.observeAsState("")
    val passwordText: String by loginViewModel.password.observeAsState("")


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
        Text(
            text = stringResource(R.string.welcome), style = MaterialTheme.typography.headlineSmall
        )

        FormInput(
            emailText,
            stringResource(R.string.email_placeholder),
            Icons.Filled.Email,
            "Email Icon"
        ) {
            loginViewModel.onChangeEmail(it)
        }

        FormPasswordInput(passwordText, stringResource(R.string.password_placeholder)) {
            loginViewModel.onChangePassword(it)
        }

        InternalButton(stringResource(R.string.access), !isLoading) {
            coroutineScope.launch {
                isLoading = true
                val response = loginViewModel.submit()
                isLoading = false

                if (!response.success) {
                    showError = true
                    errorMessage = response.message
                } else {
                    navController.navigate(Home) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }

            }
        }

        Spacer(Modifier.padding(5.dp))

        Text(
            stringResource(R.string.no_registered),
            style = MaterialTheme.typography.labelSmall,
            color = White
        )

        InternalTextButton(stringResource(R.string.create_account), TextButtonType.LINK) {
            navController.navigate(Register)
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