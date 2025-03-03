package up.chiapas.delp.core.shared.presentation.composable.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import up.chiapas.delp.ui.theme.Purple

@Composable
fun BottomBar(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Purple)
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier.align(Alignment.Center)
        ) {
            content()
        }
    }
}
