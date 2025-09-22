package voxy.friend.chat.image.loading

import android.content.Context
import coil3.ImageLoader
import coil3.network.ktor3.KtorNetworkFetcherFactory

actual fun createImageLoader(context: Context): ImageLoader {
    return baseImageLoaderBuilder(context)
        .components {
            add(KtorNetworkFetcherFactory())
        }.build()
}