package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import voxy.friend.chat.common.color.AppColors

@Composable
fun PaymentRowSection(
    paymentLogoRes: DrawableResource,
    modifier: Modifier = Modifier,
    onPaymentMethodClick: () -> Unit = {},
    onStartTrialClick: () -> Unit = {},
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.drop_down_bg)
            .padding(horizontal = 16.sdp, vertical = 12.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.clickable { onPaymentMethodClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Paytm logo
            Box(
                modifier = Modifier
                    .size(30.sdp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(resource = paymentLogoRes),
                    contentDescription = "Paytm Logo",
                    modifier = Modifier.size(32.sdp)
                )
            }

            Spacer(modifier = Modifier.width(4.sdp))

            PaymentDropDown(
                modifier = Modifier.wrapContentWidth(),
                paymentMethods = listOf("Paytm", "Google Pay", "Credit Card", "UPI", "Net Banking"),
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    onStartTrialClick()
                },
                modifier = Modifier.height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.baseColor
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Start Trial",
                        color = Color.Black,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowRightAlt,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}