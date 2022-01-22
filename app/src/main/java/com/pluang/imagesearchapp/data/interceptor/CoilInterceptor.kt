package com.pluang.imagesearchapp.data.interceptor
import okhttp3.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoilInterceptor @Inject constructor(

): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = request(chain,"")
        return try {
            chain.proceed(request)
        }
        catch (e: Exception) {
                throw IOException(e.message)

        }

    }

    private fun request(chain: Interceptor.Chain,token:String): Request {
        val original = chain.request()
        val originalHttpUrl = original.url
            val requestBuilder = original.newBuilder()
             //   .addHeader("Authorization", " )
                .url(originalHttpUrl)
            return requestBuilder.build()

    }
}