package up.chiapas.delp.post.presentation.composable

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import up.chiapas.delp.core.shared.presentation.composable.clickable.InternalButton
import up.chiapas.delp.core.shared.presentation.composable.input.TextAreaInput
import up.chiapas.delp.post.presentation.viewmodels.PostViewModel
import up.chiapas.delp.ui.theme.Purple
import java.io.File

@Composable
fun PostForm(postViewModel: PostViewModel, content: @Composable () -> Unit) {
    val title by postViewModel.title.observeAsState("")
    val body by postViewModel.body.observeAsState("")
    val imageUri by postViewModel.imageUri.observeAsState(null)

    val context = LocalContext.current

    var latestPhotoUri: Uri? = null
    var latestPhotoFile: File? = null

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && latestPhotoUri != null) {
                postViewModel.onChangeImageUri(latestPhotoUri!!, latestPhotoFile)
            } else {
                Toast.makeText(context, "Error al tomar la foto", Toast.LENGTH_SHORT).show()
            }
        }

    fun launchCamera() {
        val photoFile = File.createTempFile("photo", ".jpg", context.cacheDir).apply {
            deleteOnExit()
        }
        val photoUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            photoFile
        )

        latestPhotoUri = photoUri // Guardar la referencia temporalmente
        latestPhotoFile = photoFile // Guardar el archivo temporalmente

        cameraLauncher.launch(photoUri)
    }


    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            launchCamera()
        } else {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestCameraPermission() {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Spacer(Modifier.padding(vertical = 20.dp))
        Text("Ask the community a question", style = MaterialTheme.typography.labelMedium)
        Spacer(Modifier.padding(vertical = 18.dp))
        TextAreaInput(title, "Title", false) {
            postViewModel.onChangeTitle(it)
        }
        Spacer(Modifier.padding(vertical = 10.dp))
        TextAreaInput(body, "Tell us more about your question", true) {
            postViewModel.onChangeBody(it)
        }
        Spacer(Modifier.padding(vertical = 12.dp))
        content()

        InternalButton("Take a photo") {
            requestCameraPermission()
        }

        imageUri?.let { uri -> // No renderiza la imagen tomada
            Spacer(Modifier.padding(vertical = 12.dp))
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Selected image",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.padding(vertical = 12.dp))
        Text(
            "Be sure to align yourself with the Delp! rules of conduct, any content that violates the rules may be removed.",
            style = MaterialTheme.typography.labelSmall,
            color = Purple,
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
    }
}
