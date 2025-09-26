package voxy.friend.chat.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.elements
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.common.extension.extractPhoneNumber
import voxy.friend.chat.viewmodel.PhoneHintViewModel

@Composable
fun MobileNumberInput(modifier: Modifier = Modifier, initialPhoneNumber: String = "", isError: Boolean = false){
    var phoneNumber by remember { mutableStateOf(initialPhoneNumber) }
    val focusRequester = remember { FocusRequester() }
    var hasRequestedHint by remember { mutableStateOf(false) }

    val phoneHintViewModel = koinViewModel<PhoneHintViewModel>()
    val state by phoneHintViewModel.state.collectAsStateWithLifecycle()

    val phoneHintResult by phoneHintViewModel.uiState
        .map { it.phoneNumber to it.showSuccess }
        .distinctUntilChanged().collectAsStateWithLifecycle(
            initialValue = "" to false,
            lifecycle = LocalLifecycleOwner.current.lifecycle
        )

    LaunchedEffect(initialPhoneNumber) {
        if (initialPhoneNumber.isNotEmpty()) {
            phoneHintViewModel.numberSelected(initialPhoneNumber)
        }
    }

    LaunchedEffect(phoneHintResult.first, phoneHintResult.second) {
        if (phoneHintResult.second && phoneHintResult.first.isNotEmpty()) {
            phoneNumber = phoneHintResult.first.extractPhoneNumber()
            Napier.i { "Phone number: $phoneNumber" }
            phoneHintViewModel.numberSelected(phoneNumber)
            phoneHintViewModel.clearSuccess()
        }
    }

    OutlinedTextField(
        value = phoneNumber,
        onValueChange = { newValue ->
            // Filter to only allow digits and limit to 10 digits
            val filteredValue = newValue.filter { it.isDigit() }.take(10)
            phoneNumber = filteredValue
            phoneHintViewModel.numberSelected(phoneNumber)
            if (filteredValue.isNotEmpty() && hasRequestedHint) {
                hasRequestedHint = false
            }
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
        trailingIcon = {
            // Show tick icon when phone number is exactly 10 digits
            if (state.phoneNumber.length == 10) {
                Image(
                    painter = painterResource(Res.drawable.elements),
                    contentDescription = "Valid phone number",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp),
                    contentScale = ContentScale.None
                )
            }
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
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}