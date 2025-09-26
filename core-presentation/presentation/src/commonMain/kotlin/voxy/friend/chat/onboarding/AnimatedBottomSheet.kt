package voxy.friend.chat.onboarding

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.node.Ref
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AnimatedBottomSheet(
    value: T?,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false, confirmValueChange = { newState ->
        newState != SheetValue.Hidden
    }),
    sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = 0.dp,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    dragHandle: @Composable (() -> Unit)? = null,
    contentWindowInsets: @Composable () -> WindowInsets = { WindowInsets.ime },
    properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
    borderColor: Color = Color.Gray,
    content: @Composable ColumnScope.(T & Any) -> Unit,
) {
    LaunchedEffect(value != null) {
        if (value != null) {
            sheetState.show()
        } else {
            sheetState.hide()
            onDismissRequest()
        }
    }
    if (!sheetState.isVisible && value == null) {
        return
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        sheetMaxWidth = sheetMaxWidth,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        scrimColor = scrimColor,
        dragHandle = dragHandle,
        contentWindowInsets = contentWindowInsets,
        properties = properties
    ) {
        // Remember the last not null value: If our value becomes null and the sheet slides down,
        // we still need to show the last content during the exit animation.
        val notNullValue = lastNotNullValueOrNull(value) ?: return@ModalBottomSheet

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight() // only take needed height
                .border(2.dp, borderColor, shape)
                .padding(16.dp) // keep some padding inside border
        ) {
            content(notNullValue)
        }
    }
}

@Composable
fun <T> lastNotNullValueOrNull(value: T?): T? {
    val lastNotNullValueOrNullRef = remember { Ref<T>() }
    return value?.also {
        lastNotNullValueOrNullRef.value = it
    } ?: lastNotNullValueOrNullRef.value
}