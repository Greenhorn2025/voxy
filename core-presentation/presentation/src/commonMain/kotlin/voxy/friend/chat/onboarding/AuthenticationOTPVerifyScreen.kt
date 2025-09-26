package voxy.friend.chat.onboarding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationOTPVerifyScreen(
    modifier: Modifier = Modifier,
    phoneNumber : String,
    onEditClick : (String) -> Unit = {}
) {
    AnimatedBottomSheet(
        value = true,
        onDismissRequest = { /*TODO*/ },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        borderColor = MaterialTheme.colorScheme.outline
    ) {
       OTPVerifyScreen(modifier, phoneNumber, onEditClick)
    }
}