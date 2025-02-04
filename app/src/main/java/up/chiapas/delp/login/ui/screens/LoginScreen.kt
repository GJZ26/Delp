package up.chiapas.delp.login.ui.screens

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import up.chiapas.delp.R
import up.chiapas.delp.core.navigation.composable.input.FormInput
import up.chiapas.delp.core.navigation.composable.input.FormPasswordInput
import up.chiapas.delp.core.navigation.composable.text.Title
import up.chiapas.delp.ui.theme.Blue
import up.chiapas.delp.ui.theme.Purple
import up.chiapas.delp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }

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

        FormInput(emailText, stringResource(R.string.email_placeholder), Icons.Filled.Email, "Email Icon"){
            emailText = it
        }

        FormPasswordInput(passwordText, stringResource(R.string.password_placeholder)) {
            passwordText = it
        }

        Button(onClick = {

        }) {
            Text(stringResource(R.string.access))
        }

        Text(
            stringResource(R.string.no_registered)
        )

        TextButton(onClick = {}) {
            Text(stringResource(R.string.create_account))
        }
    }
}