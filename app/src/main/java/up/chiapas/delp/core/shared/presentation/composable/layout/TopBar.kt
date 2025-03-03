package up.chiapas.delp.core.shared.presentation.composable.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import up.chiapas.delp.core.shared.presentation.composable.clickable.InternalTextButton
import up.chiapas.delp.core.shared.presentation.composable.clickable.TextButtonType
import up.chiapas.delp.core.shared.presentation.composable.text.Title
import up.chiapas.delp.core.navigation.Login
import up.chiapas.delp.core.network.TokenManager
import up.chiapas.delp.ui.theme.Purple

@Composable
fun TopBar(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Purple)
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InternalTextButton("Log Out", TextButtonType.DEFAULT){
            TokenManager.clearToken()
            navController.navigate(Login) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
        Title(false)
    }
}