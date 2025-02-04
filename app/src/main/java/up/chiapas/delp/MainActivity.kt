package up.chiapas.delp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import up.chiapas.delp.core.navigation.NavigationWrapper
import up.chiapas.delp.ui.theme.DelpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            DelpTheme {
                LockScreenOrientation()
                NavigationWrapper()
            }
        }
    }
}

@Composable
fun LockScreenOrientation() {
    val activity = LocalActivity.current as ComponentActivity
    LaunchedEffect(Unit) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}
