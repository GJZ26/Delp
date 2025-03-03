package up.chiapas.delp.core.shared.presentation.composable.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import up.chiapas.delp.ui.theme.Purple
import up.chiapas.delp.ui.theme.White

@Composable
fun FormInput(
    value: String,
    placeholder: String,
    icon: ImageVector,
    iconDescription: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
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
            Icon(imageVector = icon, contentDescription = iconDescription, tint = Purple)
        },
        shape = RoundedCornerShape(10.dp)
    )
}