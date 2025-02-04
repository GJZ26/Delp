package up.chiapas.delp.login.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import up.chiapas.delp.R
import up.chiapas.delp.core.composable.clickable.InternalButton
import up.chiapas.delp.core.composable.clickable.InternalTextButton
import up.chiapas.delp.core.composable.clickable.TextButtonType
import up.chiapas.delp.core.composable.input.FormInput
import up.chiapas.delp.core.composable.input.FormPasswordInput
import up.chiapas.delp.core.composable.text.Title
import up.chiapas.delp.core.navigation.Login
import up.chiapas.delp.core.navigation.Register
import up.chiapas.delp.ui.theme.Purple

@Composable
fun LoginScreen(navController: NavController) {
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }

    // limpiar el registro de navController para que entrando a login, no haya forma de retroceder!

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Purple),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Title(true)
        Text(
            text = stringResource(R.string.welcome)
        )

        FormInput(
            emailText,
            stringResource(R.string.email_placeholder),
            Icons.Filled.Email,
            "Email Icon"
        ) {
            emailText = it
        }

        FormPasswordInput(passwordText, stringResource(R.string.password_placeholder)) {
            passwordText = it
        }

        InternalButton(stringResource(R.string.access)) {
            Log.i("Delp", emailText)
        }

        Text(
            stringResource(R.string.no_registered)
        )

        InternalTextButton(stringResource(R.string.create_account), TextButtonType.LINK) {
            navController.navigate(Register)
        }
    }
}