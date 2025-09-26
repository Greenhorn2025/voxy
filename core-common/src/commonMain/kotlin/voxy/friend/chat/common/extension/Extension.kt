package voxy.friend.chat.common.extension

import androidx.compose.runtime.Composable

@Composable
fun DisplayIf(visible: Boolean, content: @Composable () -> Unit) {
    if (visible) {
        content()
    }
}

@Composable
fun DisplayIf(
    visible: Boolean,
    content: @Composable () -> Unit,
    elseContent: @Composable () -> Unit
) {
    if (visible) {
        content()
    } else
        elseContent()
}