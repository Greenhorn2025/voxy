package voxy.friend.chat.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.ic_emoji
import voxy.friend.chat.common.color.AppColors

@Composable
fun BottomMessageInput(
    modifier: Modifier = Modifier,
    message: String = "",
    onMessageChange: (String) -> Unit = {},
    onSendMessage: () -> Unit = {},
    onEmojiClick: () -> Unit = {},
    placeholder: String = "Message",
    enabled: Boolean = true
) {
    var textState by remember { mutableStateOf(message) }
    val focusRequester = remember { FocusRequester() }
    var isSending by remember { mutableStateOf(false) }

    // Sync with external message state
    LaunchedEffect(message) {
        if (textState != message) {
            textState = message
        }
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ),
        color = Color.Black
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.sdp, vertical = 4.sdp),
            verticalAlignment = Alignment.Bottom
        ) {
            // Message Input Container with dynamic height
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    ),
                color = AppColors.Tertiary,
                shape = RoundedCornerShape(24.sdp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.sdp, vertical = 8.sdp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    // Emoji button inside input
                    Surface(
                        onClick = onEmojiClick,
                        modifier = Modifier
                            .size(20.sdp)
                            .align(Alignment.CenterVertically),
                        color = Color.Transparent,
                        shape = CircleShape
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_emoji),
                            tint = AppColors.white,
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.width(8.sdp))

                    // Text input with dynamic height
                    BasicTextField(
                        value = textState,
                        onValueChange = { newText ->
                            if (!isSending) { // Prevent changes while sending
                                textState = newText
                                onMessageChange(newText)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester),
                        textStyle = MaterialTheme.typography.bodyMedium.copy(color = AppColors.white),
                        singleLine = false,
                        maxLines = 5, // Increased max lines
                        keyboardOptions = KeyboardOptions(
                            imeAction = if (textState.isBlank()) ImeAction.Default else ImeAction.Send,
                            capitalization = KeyboardCapitalization.Sentences
                        ),
                        keyboardActions = KeyboardActions(
                            onSend = {
                                if (textState.isNotBlank() && !isSending) {
                                    isSending = true
                                    onSendMessage()

                                    // Delay clearing text and resetting state to prevent flicker
                                    MainScope().launch {
                                        delay(100) // Small delay to prevent keyboard flicker
                                        textState = ""
                                        isSending = false
                                        // Don't hide keyboard immediately, let it stay focused
                                    }
                                }
                            }
                        ),
                        cursorBrush = SolidColor(Color.White),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (textState.isEmpty()) {
                                    Text(
                                        text = placeholder,
                                        color = Color(0xFF9CA3AF),
                                        fontSize = 16.ssp,
                                        lineHeight = 22.ssp
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.sdp))

            // Send button with smooth animation
            AnimatedVisibility(
                visible = textState.isNotBlank(),
                enter = slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ) + fadeIn(
                    animationSpec = tween(300)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(200)
                ) + fadeOut(
                    animationSpec = tween(200)
                )
            ) {
                Spacer(modifier = Modifier.width(8.sdp))

                Surface(
                    onClick = {
                        if (textState.isNotBlank() && !isSending) {
                            isSending = true
                            onSendMessage()

                            // Handle send with proper state management
                            MainScope().launch {
                                delay(100)
                                textState = ""
                                isSending = false
                                // Keep focus on text field to maintain keyboard
                                focusRequester.requestFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .size(38.sdp),
                    color = AppColors.baseColor,
                    shape = CircleShape,
                    shadowElevation = 2.dp
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send message",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}