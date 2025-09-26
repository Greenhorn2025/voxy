package voxy.friend.chat.onboarding

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import io.github.aakira.napier.Napier
import network.chaintech.sdpcomposemultiplatform.sdp
import org.koin.compose.viewmodel.koinViewModel
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.viewmodel.PhoneHintViewModel


@Composable
fun OTPNumberInput(modifier: Modifier = Modifier, isError: Boolean = false) {
    val phoneHintViewModel = koinViewModel<PhoneHintViewModel>()
    var otpValue by remember { mutableStateOf("") }

    OutlinedTextField(
        value = otpValue,
        onValueChange = { newValue ->
            val filteredValue = newValue.filter { it.isDigit() }.take(4)
            otpValue = filteredValue
            Napier.d("OTPNumberInput: $otpValue")
            if(otpValue.length == 4){
                phoneHintViewModel.updateOTPNumber(otpValue)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = {
            Text(
                text = "Enter the 4-digit OTP",
                color = Color.Gray.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodySmall
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppColors.borderHighlight,
            unfocusedBorderColor = AppColors.borderHighlight.copy(alpha = 0.5f),
            errorBorderColor = Color.Red,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        ),
        shape = RoundedCornerShape(12.sdp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        isError = isError,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}