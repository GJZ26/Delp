package up.chiapas.delp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import up.chiapas.delp.login.presentation.LoginScreen
import up.chiapas.delp.login.presentation.LoginViewModel
import up.chiapas.delp.register.presentation.RegisterScreen
import up.chiapas.delp.register.presentation.RegisterViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> { LoginScreen(navController, LoginViewModel()) }
        composable<Register> { RegisterScreen(navController, RegisterViewModel()) }
    }
}