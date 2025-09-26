package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.ic_paytm
import voxy.friend.chat.navigation.OnBoardingGraph

@Composable
fun PaywallContent(
    modifier: Modifier = Modifier,
    showBottomSheet: Boolean,
    onStartTrialClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Header Section
        PaywallHeader()

        Spacer(Modifier.height(40.dp))

        // Main Image
        PaywallImage()

        // Disclaimer Text
        PaywallDisclaimer()

        Spacer(Modifier.height(24.dp))

        // Unlock Section
        PaywallUnlockSection()

        Spacer(Modifier.weight(1f))

        // Payment Section
        PaymentRowSection(
            paymentLogoRes = Res.drawable.ic_paytm,
            onStartTrialClick = onStartTrialClick
        )

        // Bottom Sheet
        if (showBottomSheet) {
            OnBoardingGraph()
        }
    }
}