package up.chiapas.delp.core.composable.clickable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import up.chiapas.delp.ui.theme.Blue
import up.chiapas.delp.ui.theme.BlueSky
import up.chiapas.delp.ui.theme.Purple
import up.chiapas.delp.ui.theme.Red
import up.chiapas.delp.ui.theme.White

enum class TextButtonType {
    LINK, DANGER, DEFAULT
}

@Composable
fun InternalButton(text: String, enabled: Boolean = true, onclick: () -> Unit) {
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
            contentColor = White
        ),
        modifier = Modifier.padding(horizontal = 60.dp),
        enabled = enabled
    ) {
        Text(text)
    }
}

@Composable
fun InternalTextButton(text: String, type: TextButtonType, onclick: () -> Unit) {
    TextButton(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(
            contentColor = when (type) {
                TextButtonType.LINK -> BlueSky
                TextButtonType.DANGER -> Red
                else -> Purple
            }
        )
    ) {
        Text(text)
    }
}