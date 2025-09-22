package voxy.friend.chat.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.aakira.napier.Napier
import network.chaintech.sdpcomposemultiplatform.sdp
import org.koin.compose.viewmodel.koinViewModel
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.viewmodel.PhoneHintViewModel

@Composable
fun MobileNumberInput(modifier: Modifier = Modifier, value: String = "", isError: Boolean = false){
    var phoneNumber by remember { mutableStateOf(value) }
    val focusRequester = remember { FocusRequester() }
    var hasRequestedHint by remember { mutableStateOf(false) }

    val phoneHintViewModel = koinViewModel<PhoneHintViewModel>()
    val phoneHintState = phoneHintViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(phoneHintState.value) {
        phoneHintState.value.let { state ->
            if (state.showSuccess && state.phoneNumber.isNotEmpty()) {
                // Extract only the digits and remove country code if present
                Napier.i { "Phone number: ${state.phoneNumber}" }
                val extractedNumber = extractPhoneNumber(state.phoneNumber)
                phoneNumber = extractedNumber
                phoneHintViewModel.clearSuccess()
            }
        }
    }

    OutlinedTextField(
        value = phoneNumber,
        onValueChange = { newValue ->
            // Filter to only allow digits and limit to 10 digits
            val filteredValue = newValue.filter { it.isDigit() }.take(10)
            phoneNumber = filteredValue
            if (filteredValue.isNotEmpty() && hasRequestedHint) {
                hasRequestedHint = false
            }
//            onValueChange(filteredValue)
        },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp).focusRequester(focusRequester).onFocusChanged{ focusState ->
                if (focusState.isFocused && phoneNumber.isEmpty()) {
                    hasRequestedHint = true
                    phoneHintViewModel.requestPhoneHint()
                }
            },
        placeholder = {
            Text(
                text = "Your 10-digit Mobile Number",
                color = Color.Gray.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodySmall
            )
        },
        leadingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 12.sdp)
            ) {
                Text(
                    text = "+91",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Box(
                    modifier = Modifier.padding(horizontal = 5.sdp)
                        .width(1.sdp)
                        .height(20.sdp)
                        .background(Color.Gray.copy(alpha = 0.5f))
                        .padding(horizontal = 8.sdp)
                )
            }
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
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}

private fun extractPhoneNumber(fullNumber: String): String {
    val digitsOnly = fullNumber.filter { it.isDigit() }
    return if (digitsOnly.length > 10) {
        digitsOnly.takeLast(10)
    } else {
        digitsOnly
    }
}