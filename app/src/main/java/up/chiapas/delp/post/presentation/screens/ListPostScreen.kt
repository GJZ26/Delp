package up.chiapas.delp.post.presentation.screens

import PostCard
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import up.chiapas.delp.core.shared.presentation.composable.clickable.InternalIconButton
import up.chiapas.delp.core.shared.presentation.composable.layout.DefaultLayout
import up.chiapas.delp.core.navigation.CreatePost
import up.chiapas.delp.post.presentation.viewmodels.HomeViewModel

@Composable
fun ListPostScreen(navController: NavController, homeViewModel: HomeViewModel) {
    var showError by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }

    val postList by homeViewModel.posts.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        val response = homeViewModel.getList()
        if (!response.success) {
            showError = true
            errorMessage = response.message
        }
    }

    DisposableEffect (Unit) {
        onDispose {
            homeViewModel.resetFetched()
        }
    }

    DefaultLayout(
        bottomBarContent = {
            InternalIconButton(Icons.Filled.Add, "Add Icon") {
                navController.navigate(CreatePost)
            }
        },
        navController = navController
    ) {
        Column {
            postList.forEach { post ->
                PostCard(post, homeViewModel, navController)
            }

            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier.align(Alignment.CenterHorizontally),
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

