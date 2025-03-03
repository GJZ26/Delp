package up.chiapas.delp.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import up.chiapas.delp.core.network.TokenManager
import up.chiapas.delp.post.presentation.screens.ListPostScreen
import up.chiapas.delp.post.presentation.viewmodels.HomeViewModel
import up.chiapas.delp.login.presentation.screens.LoginScreen
import up.chiapas.delp.login.presentation.viewmodels.LoginViewModel
import up.chiapas.delp.post.presentation.viewmodels.PostViewModel
import up.chiapas.delp.post.presentation.screens.CreatePostScreen
import up.chiapas.delp.post.presentation.screens.EditPostScreen
import up.chiapas.delp.post.presentation.screens.GetPostScreen
import up.chiapas.delp.register.presentation.screens.RegisterScreen
import up.chiapas.delp.register.presentation.viewmodels.RegisterViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (TokenManager.getToken().isNullOrEmpty()) Login else Home
    ) {
        composable<Login> {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(navController, loginViewModel)
        }

        composable<Register> {
            val registerViewModel: RegisterViewModel = viewModel()
            RegisterScreen(navController, registerViewModel)
        }

        composable<Home> {
            val homeViewModel: HomeViewModel = viewModel()
            ListPostScreen(navController, homeViewModel)
        }

        composable<CreatePost> {
            val postViewModel: PostViewModel = viewModel()
            CreatePostScreen(navController, postViewModel)
        }

        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            val postViewModel: PostViewModel = viewModel()
            EditPostScreen(id, navController, postViewModel)
        }

        composable("view/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            val postViewModel: PostViewModel = viewModel()
            GetPostScreen(navController, id, postViewModel)
        }
    }
}