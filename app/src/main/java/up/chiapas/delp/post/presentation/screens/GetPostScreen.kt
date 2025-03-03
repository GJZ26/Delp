package up.chiapas.delp.post.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import up.chiapas.delp.core.shared.presentation.composable.clickable.InternalButton
import up.chiapas.delp.core.shared.presentation.composable.layout.DefaultLayout
import up.chiapas.delp.post.presentation.viewmodels.PostViewModel
import up.chiapas.delp.ui.theme.LightPurple

@Composable
fun GetPostScreen(navController: NavController, id: String, postViewModel: PostViewModel) {
    val snackBarHostState = remember { SnackbarHostState() }
    val title by postViewModel.title.observeAsState("")
    val body by postViewModel.body.observeAsState("")
    val image by postViewModel.image.observeAsState("")
    val author by postViewModel.author.observeAsState("")
    val authorAvatar by postViewModel.authorAvatar.observeAsState("")
    val createdAt by postViewModel.createdAt.observeAsState("")

    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(
        title.isEmpty() && body.isEmpty() && image.isEmpty()
    ) {
        val response = postViewModel.findPost(id)
        if (!response.success) {
            showError = true
            errorMessage = response.message
        }
        isLoading = false
    }

    DefaultLayout(navController, bottomBarContent = {
        InternalButton("Volver") {
            navController.popBackStack()
        }
    }) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(LightPurple)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightPurple)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = body,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (image.isNotEmpty()) {
                    Image(
                        painter = rememberImagePainter(image),
                        contentDescription = title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(authorAvatar),
                        contentDescription = title,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = author,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = createdAt,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                SnackbarHost(
                    hostState = snackBarHostState,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
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