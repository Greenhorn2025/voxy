package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject
import voxy.friend.chat.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaywallScreen(modifier: Modifier = Modifier, onDismiss: () -> Unit = {}) {
    val viewModel: SharedViewModel = koinInject<SharedViewModel>()
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    PaywallContent(
        modifier = modifier,
        showBottomSheet = uiState.showBottomSheet,
        onStartTrialClick = { viewModel.checkUserLoggedInState() },
        onDismiss = {
            onDismiss.invoke()
        }
    )
}