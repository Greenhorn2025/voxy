package voxy.friend.chat.image.loading

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.network.ktor3.KtorNetworkFetcherFactory

actual fun createImageLoader(context: PlatformContext): ImageLoader {
    return baseImageLoaderBuilder(context)
        .components {
            add(KtorNetworkFetcherFactory())
        }
        .build()
}