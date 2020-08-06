package `in`.coding.experiononlinetest.configurations

import `in`.coding.experiononlinetest.MyApplication
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


object RetrofitConfig {

    private const val baseUrl = "https://dl.dropboxusercontent.com/"
    private const val headerCacheControl = "Cache-Control"
    private const val headerPragma = "Pragma"

    //cache size
    private const val cacheSize = (5 * 1024 * 1024).toLong() //5 MB

    private val retrofit: Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: RestCallInterface by lazy {
        retrofit
            .build()
            .create(RestCallInterface::class.java)
    }

    //cache the data returned from Api call
    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache())
            .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
            .addNetworkInterceptor(networkInterceptor()) // only used when network is on
            .addInterceptor(offlineInterceptor())
            .build()
    }

    private fun cache(): Cache = Cache(File(MyApplication.getInstance()!!.cacheDir, "someIdentifier"), cacheSize)

    private fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request: Request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!MyApplication.hasNetwork()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(headerPragma)
                    .removeHeader(headerCacheControl)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    private fun networkInterceptor() : Interceptor {
        return Interceptor { chain ->
            val response: Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()
            response.newBuilder()
                .removeHeader(headerPragma)
                .removeHeader(headerCacheControl)
                .header(headerCacheControl, cacheControl.toString())
                .build()
        }

    }

    private fun httpLoggingInterceptor() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}