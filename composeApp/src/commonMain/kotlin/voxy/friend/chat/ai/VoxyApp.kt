package voxy.friend.chat.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil3.compose.setSingletonImageLoaderFactory
import voxy.friend.chat.ai.navigation.VoxyNavGraph
import voxy.friend.chat.common.theme.DarkColorScheme
import voxy.friend.chat.common.typegraphy.VoxyTypography
import voxy.friend.chat.image.loading.createImageLoader

@Composable
fun VoxyApp() {
    setSingletonImageLoaderFactory { context ->
        createImageLoader(context)
    }
    VoxyAppTheme {
        val backgroundColor = MaterialTheme.colorScheme.primary

        Box(
            modifier = Modifier.fillMaxSize()
                .background(backgroundColor) // Background covers entire screen including system bars
                .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical))
        ) {
            val navHostController = rememberNavController()
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navHostController,
                startDestination = VoxyNavGraph.Dest.Root.route
            ) {
                listOf(VoxyNavGraph).forEach { navGraph ->
                    navGraph.build(
                        modifier = Modifier,
                        navHostController = navHostController,
                        navGraphBuilder = this
                    )
                }
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