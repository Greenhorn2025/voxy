package voxy.friend.chat.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import voxy.friend.chat.common.color.AppColors

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    onGetOtpClick: () -> Unit = {},
    onGoogleSignInClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()) // ✅ make content scrollable
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.sdp))
        Text(
            text = "Enter your mobile number",
            color = AppColors.OnPrimary,
            modifier = Modifier.align(Alignment.Start).padding(start = 16.sdp, bottom = 5.sdp),
            style = MaterialTheme.typography.bodyMedium
        )
        MobileNumberInput(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.sdp))

        Button(
            onClick = onGetOtpClick,
            modifier = Modifier
                .padding(vertical = 15.sdp)
                .fillMaxWidth()
                .height(40.sdp)
                .padding(horizontal = 10.sdp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A4A4A), // Dark gray
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.sdp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.sdp,
                pressedElevation = 8.sdp
            )
        ) {
            Text(
                text = "Get OTP",
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.disableColor
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Gray.copy(alpha = 0.5f))
            )

            Text(
                text = "OR",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.sdp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Gray.copy(alpha = 0.5f))
            )
        }

        Spacer(modifier = Modifier.height(10.sdp))

        Button(
            onClick = onGoogleSignInClick,
            modifier = Modifier
                .padding(horizontal = 10.sdp)
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A4A4A), // Dark gray
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50.sdp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Google Logo (using a simple colored circle as placeholder)
                // You can replace this with actual Google logo image
//                GoogleLogo()

                Spacer(modifier = Modifier.width(12.sdp))

                Text(
                    text = "Sign in with Google",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.white
                )
            }
        }

        Spacer(modifier = Modifier.height(28.sdp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // First line - Terms and Privacy Policy
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "By continuing you agree to our ",
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    fontSize = 8.ssp
                )

                Text(
                    text = "Terms of Use",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    fontSize = 8.ssp,
                    modifier = Modifier.clickable {
                        // Handle terms of use click
                    }
                )

                Text(
                    text = " and ",
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 8.ssp,
                )

                Text(
                    text = "Privacy Policy",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    fontSize = 8.ssp,
                    modifier = Modifier.clickable {
                        // Handle privacy policy click
                    }
                )
            }

            // Second line - Security message
            Text(
                text = "All your details are 100% safe & secure.",
                color = Color.Gray,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 8.ssp,
                textAlign = TextAlign.Center
            )
        }
    }
}
