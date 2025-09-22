package voxy.friend.chat.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import network.chaintech.sdpcomposemultiplatform.sdp
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.common.typegraphy.VoxyTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionPaywallScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            PaywallTopBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues).background(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Send Unlimited Chats for",
                color = AppColors.OnPrimary,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Text(
                text = "₹1",
                color = AppColors.priceHighlight,
                style = VoxyTypography.default().displayLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Text(
                text = "75k+ people bought the trial offer till now!",
                color = AppColors.darkHighlight,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }


        AnimatedBottomSheet(
            value = true,
            tonalElevation = 10.sdp,
            onDismissRequest = { /*TODO*/ },
        ) {
            AuthenticationScreen(modifier = Modifier.fillMaxSize())
        }
    }
}


