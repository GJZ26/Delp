package up.chiapas.delp.core.navigation.composable.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import up.chiapas.delp.R
import up.chiapas.delp.ui.theme.Blue
import up.chiapas.delp.ui.theme.Purple
import up.chiapas.delp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    value: String,
    placeholder: String,
    icon: ImageVector,
    iconDescription: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value,
        singleLine = true,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Blue,
            unfocusedBorderColor = White,
            containerColor = White,
        ),
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = iconDescription, tint = Purple)
        },
        shape = RoundedCornerShape(10.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPasswordInput(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Blue,
            unfocusedBorderColor = White,
            containerColor = White,
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Lock, contentDescription = "Lock Icon", tint = Purple)
        },
        trailingIcon = {
            Icon(
                painter = if (passwordVisible) painterResource(R.drawable.visibility_off_24px) else painterResource(R.drawable.visibility_24px),
                contentDescription = if (passwordVisible) "Visibility Off Icon" else "Visibility Icon",
                tint = Purple,
                modifier = Modifier.clickable {
                    passwordVisible = !passwordVisible
                }
            )
        },
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
