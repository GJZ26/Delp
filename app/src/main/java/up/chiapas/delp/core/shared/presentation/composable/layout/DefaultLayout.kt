package up.chiapas.delp.core.shared.presentation.composable.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DefaultLayout(
    navController: NavController,
    bottomBarContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = { BottomBar { bottomBarContent() } }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding(), top = 12.dp)
        ) {
            MainContent(modifier = Modifier.weight(1f)) {
                content()
            }
        }
    }
}
