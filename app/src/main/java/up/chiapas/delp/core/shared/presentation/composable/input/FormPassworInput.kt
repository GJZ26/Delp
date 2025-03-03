package up.chiapas.delp.core.shared.presentation.composable.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import up.chiapas.delp.R
import up.chiapas.delp.ui.theme.Purple
import up.chiapas.delp.ui.theme.White

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
            .padding(horizontal = 18.dp, vertical = 15.dp)
            .height(52.dp),
        placeholder = { Text(placeholder, fontSize = 15.sp, modifier = Modifier.padding(0.dp)) },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = White,
            focusedContainerColor = White,
            unfocusedContainerColor = White
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Lock, contentDescription = "Lock Icon", tint = Purple)
        },
        trailingIcon = {
            Icon(
                painter = if (!passwordVisible) painterResource(R.drawable.visibility_off_24px) else painterResource(
                    R.drawable.visibility_24px
                ),
                contentDescription = if (!passwordVisible) "Visibility Off Icon" else "Visibility Icon",
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