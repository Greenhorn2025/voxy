package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import network.chaintech.sdpcomposemultiplatform.ssp
import voxy.friend.chat.common.color.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentDropDownTextField(
    label: String,
    modifier: Modifier,
    expanded : Boolean,
    selected : String = "",
) {
    TextField(
        readOnly = true,
        value = selected,
        onValueChange = {},
        label = { Text(label, style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.ssp)) },
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
        modifier = modifier,
        singleLine = true,
        textStyle = MaterialTheme.typography.labelSmall.copy(fontSize = 14.sp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedLabelColor = AppColors.secondary_text,
            focusedLabelColor = AppColors.secondary_text,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}