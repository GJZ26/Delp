package up.chiapas.delp.core.shared.presentation.composable.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import up.chiapas.delp.ui.theme.DecoloredPurple
import up.chiapas.delp.ui.theme.LightPurple
import up.chiapas.delp.ui.theme.Purple

@Composable
fun TextAreaInput(
    value: String,
    placeholder: String,
    bigTextArea: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = DecoloredPurple, fontSize = 14.sp) },
        modifier = Modifier
            .background(LightPurple)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        maxLines = if (bigTextArea) 8 else 1,
        minLines = if (bigTextArea) 8 else 1,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Purple,
            focusedIndicatorColor = Purple,
            focusedContainerColor = LightPurple,
            unfocusedContainerColor = LightPurple
        )
    )
}
