package com.restart.jetpack_compose_examples

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import java.io.File
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {

    var text by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // OkHttp API call to https://jsonplaceholder.typicode.com/todos

        doRequest()

        setContent {
            Jetpack_compose_examplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }

                }
            }
        }
    }

    private fun doRequest() {

        val cache = Cache(
            File(applicationContext.cacheDir, "felo_http_cache"),
            10L * 1024L * 1024L // 10 MB cache size
        )
        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })

            .addNetworkInterceptor { chain ->
                val response = chain.proceed(chain.request())

                val cahceControl = CacheControl.Builder()
                    .maxAge(10, TimeUnit.DAYS)
                    .build()

                return@addNetworkInterceptor response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", cahceControl.toString())
                    .build()
            }
            .addInterceptor { chain ->
                var request = chain.request()

                if (hasNetwork().not()) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }

                chain.proceed(request)
            }
            .build()

        val request = Request.Builder()
            .url("http://192.168.1.36:8080/employees")
            .build()


        okHttpClient.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("OkHttp", "onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(
                    "OkHttp",
                    "cacheResponse: ${response.cacheResponse}: ${response.cacheResponse},\n networkResponse: ${response.networkResponse}"
                )
                text = response.body.string() ?: "No response"
            }

        })

    }
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.hasNetwork(): Boolean {
    val result: Boolean
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }

    return result
}


