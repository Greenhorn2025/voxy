package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.frame

@Composable
fun PaywallImage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(180.dp),
        painter = painterResource(Res.drawable.frame),
        contentDescription = "Paywall feature illustration",
        contentScale = ContentScale.Fit
    )
}
