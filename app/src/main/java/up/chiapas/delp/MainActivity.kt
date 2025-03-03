package up.chiapas.delp

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import leakcanary.LeakCanary
import up.chiapas.delp.core.navigation.NavigationWrapper
import up.chiapas.delp.core.network.TokenManager
import up.chiapas.delp.ui.theme.DelpTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TokenManager.init(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        LeakCanary.config = LeakCanary.config.copy(
            dumpHeap = true
        )

        setContent {
            DelpTheme {
                LockScreenOrientation()
                NavigationWrapper()
            }
        }
    }
}

@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun LockScreenOrientation() {
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    LaunchedEffect(Unit) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}