package up.chiapas.delp.post.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import up.chiapas.delp.core.shared.presentation.composable.clickable.InternalButton
import up.chiapas.delp.core.shared.presentation.composable.layout.DefaultLayout
import up.chiapas.delp.post.presentation.viewmodels.PostViewModel
import up.chiapas.delp.post.presentation.composable.PostForm

@Composable
fun CreatePostScreen( navController: NavController, postViewModel: PostViewModel) {

    val snackBarHostState = remember { SnackbarHostState() }

    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    DefaultLayout(navController = navController, bottomBarContent = {
        InternalButton("Publish", !isLoading) {
            coroutineScope.launch {
                isLoading = true
                val response = postViewModel.save()
                isLoading = false
                if (!response.success) {
                    showError = true
                    errorMessage = response.message
                } else {
                    navController.popBackStack()
                }
            }
        }
    }) {
        PostForm(postViewModel) {
            Column {
                SnackbarHost(
                    hostState = snackBarHostState,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                )

                if (showError) {
                    LaunchedEffect(true) {
                        snackBarHostState.showSnackbar(message = errorMessage)
                        showError = false
                    }
                }
            }

        }
    }
}