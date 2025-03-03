package up.chiapas.delp.core.shared.presentation.composable.clickable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import up.chiapas.delp.ui.theme.Blue
import up.chiapas.delp.ui.theme.BlueSky
import up.chiapas.delp.ui.theme.Purple
import up.chiapas.delp.ui.theme.Red
import up.chiapas.delp.ui.theme.White

enum class TextButtonType {
    LINK, DANGER, DEFAULT, NORMAL
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
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.SemiBold,
        textDecoration = TextDecoration.Underline,
        color = when (type) {
            TextButtonType.LINK -> BlueSky
            TextButtonType.DANGER -> Red
            TextButtonType.DEFAULT -> White
            TextButtonType.NORMAL -> Purple
        },
        modifier = Modifier
            .clickable(onClick = onclick)
            .padding(4.dp)
    )
}


@Composable
fun InternalIconButton(
    icon: ImageVector,
    iconDescription: String, onclick: () -> Unit
) {
    IconButton(
        onClick = { onclick() },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Blue,
            contentColor = White
        )
    ) {
        Icon(imageVector = icon, contentDescription = iconDescription, tint = White)
    }
}