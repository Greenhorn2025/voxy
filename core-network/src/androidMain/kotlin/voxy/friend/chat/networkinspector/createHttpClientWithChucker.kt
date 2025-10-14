package voxy.friend.chat.networkinspector

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun createHttpClientWithChucker(context: Context): HttpClient {
    return HttpClient(OkHttp) {
        engine {
            preconfigured = OkHttpClient.Builder()
                .addInterceptor(
                    ChuckerInterceptor.Builder(context)
                        .collector(ChuckerCollector(context))
                        .maxContentLength(250_000L)
                        .redactHeaders("Authorization", "Bearer")
                        .alwaysReadResponseBody(true)
                        .build()
                )
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        install(ContentNegotiation) {
            json()
        }
    }
}

fun setupAndroidClient(context: Context): HttpClient {
    return createHttpClientWithChucker(context)
}
