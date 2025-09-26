package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentDropDown(
    paymentMethods: List<String>,
    modifier: Modifier = Modifier,
    label: String = "Pay via"
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val textFieldState = rememberTextFieldState(paymentMethods[0])

    ExposedDropdownMenuBox(
        expanded = isDropdownExpanded,
        onExpandedChange = { isDropdownExpanded = it },
        modifier = modifier
    ) {
        PaymentDropDownTextField(
            label = label,
            expanded = isDropdownExpanded,
            selected = textFieldState.text as String,
            modifier = modifier.width(90.sdp)
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
        )

        ExposedDropdownMenu(
            expanded = isDropdownExpanded,
            onDismissRequest = { isDropdownExpanded = false }
        ) {
            paymentMethods.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, color = Color.White, style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.ssp)) },
                    onClick = {
                        textFieldState.setTextAndPlaceCursorAtEnd(option)
                        isDropdownExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}
