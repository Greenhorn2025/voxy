package voxy.friend.chat.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import coil3.compose.setSingletonImageLoaderFactory
import org.koin.compose.koinInject
import voxy.friend.chat.common.theme.DarkColorScheme
import voxy.friend.chat.common.typegraphy.VoxyTypography
import voxy.friend.chat.image.loading.createImageLoader
import voxy.friend.chat.main.App
import voxy.friend.chat.viewmodel.SharedViewModel

@Composable
fun MainViewApp() {
    setSingletonImageLoaderFactory { context ->
        createImageLoader(context)
    }

    VoxyApp(
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun VoxyApp(modifier: Modifier = Modifier){
    val viewModel: SharedViewModel = koinInject<SharedViewModel>()
    val startDestinations by viewModel.state.collectAsStateWithLifecycle()

    VoxyAppTheme {
        val backgroundColor = MaterialTheme.colorScheme.primary
        val navHostController = rememberNavController()

        Surface(
            color = backgroundColor
        ){
            Box(
                modifier = modifier.safeDrawingPadding()
                    .background(backgroundColor) // Background covers entire screen including system bars
                    .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical))
            ) {
                App(
                    navHostController = navHostController,
                    startDestinations = startDestinations.destinations
                )
            }
        }

    }
}

@Composable
fun VoxyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = VoxyTypography.default(),
        content = content
    )
}