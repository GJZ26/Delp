package up.chiapas.delp.core.navigation.composable.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import up.chiapas.delp.R

@Composable
@Preview
fun Title(showSlogan: Boolean = true) {
    if (showSlogan) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.app_slogan),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    } else {
        Text(
            text = stringResource(R.string.app_name), style = MaterialTheme.typography.titleLarge
        )
    }
}