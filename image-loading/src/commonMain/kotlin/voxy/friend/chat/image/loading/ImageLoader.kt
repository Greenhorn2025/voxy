package voxy.friend.chat.image.loading

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.crossfade
import okio.FileSystem


expect fun createImageLoader(context: PlatformContext): ImageLoader

fun baseImageLoaderBuilder(context: PlatformContext): ImageLoader.Builder {
    return ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder()
                // Set memory cache size to 25% of available memory.
                .maxSizePercent(context, 0.25)
                .strongReferencesEnabled(true)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                // Use a platform-specific cache directory.
                .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
                // Set disk cache size to 512MB.
                .maxSizeBytes(512L * 1024 * 1024)
                .build()
        }
        // Add other configurations like crossfade animations.
        .crossfade(true)
}