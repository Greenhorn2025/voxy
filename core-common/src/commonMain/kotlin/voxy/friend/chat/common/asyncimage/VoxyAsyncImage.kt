package voxy.friend.chat.common.asyncimage

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun VoxyAsyncImage(
    src: Any,
    modifier: Modifier = Modifier,
    contentDescription: String = "src_$src",
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    onError: (s: AsyncImagePainter.State) -> Unit = {},
    onLoading: (s: AsyncImagePainter.State) -> Unit = {},
    onSuccess: (s: AsyncImagePainter.State) -> Unit = {},
){
    when (src) {
        is DrawableResource -> {
            // Use Compose's Image for DrawableResource for better performance
            Image(
                painter = painterResource(src),
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = contentScale,
                alignment = alignment
            )
        }
        else -> {
            // Use AsyncImage for other sources (URLs, etc.)
            AsyncImage(
                modifier = modifier,
                model = src,
                contentDescription = contentDescription,
                onError = onError,
                onLoading = onLoading,
                onSuccess = onSuccess,
                contentScale = contentScale,
                alignment = alignment,
            )
        }
    }
}