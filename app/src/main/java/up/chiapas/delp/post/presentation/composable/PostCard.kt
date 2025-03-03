import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import up.chiapas.delp.core.shared.presentation.composable.clickable.InternalTextButton
import up.chiapas.delp.core.shared.presentation.composable.clickable.TextButtonType
import up.chiapas.delp.post.data.model.response.Post
import up.chiapas.delp.post.presentation.viewmodels.HomeViewModel
import up.chiapas.delp.ui.theme.LightOrange
import up.chiapas.delp.ui.theme.LightPurple
import up.chiapas.delp.ui.theme.Purple

@Composable
fun PostCard(post: Post, homeViewModel: HomeViewModel, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = rememberImagePainter(post.author.avatar),
            contentDescription = "${post.author.username} avatar",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = if (post.is_mine) LightOrange else LightPurple)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Purple,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.clickable {
                        navController.navigate("view/${post.id}")
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(
                            text = "${post.created_at} by ",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                        Text(
                            text = if (post.is_mine) "You" else post.author.username,
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    if (post.is_mine) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            InternalTextButton("Edit", TextButtonType.NORMAL) {
                                navController.navigate("edit/${post.id}")
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            InternalTextButton("Remove", TextButtonType.DANGER) {
                                coroutineScope.launch {
                                    homeViewModel.removePost(post.id)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}
