package voxy.friend.chat.login

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.ic_arrow_back
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.common.typegraphy.VoxyTypography

@Preview
@Composable
fun PaywallTopBar(modifier: Modifier = Modifier.fillMaxWidth()) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        tonalElevation = 4.dp // Add elevation for better visual separation
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(Res.drawable.ic_arrow_back), contentDescription = null)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "FAQs",
                modifier = Modifier.wrapContentSize(),
                color = AppColors.Error,
                style = VoxyTypography.default().bodySmall
            )
        }
    }
}