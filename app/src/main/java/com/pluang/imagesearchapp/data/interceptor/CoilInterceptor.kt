package com.pluang.imagesearchapp.data.interceptor


import android.util.Log

import okhttp3.*
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoilInterceptor @Inject constructor(

): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
Log.e("api ","call")
//        synchronized(this) {
//            val request = request(chain,"")
//            val initialResponse = chain.proceed(request)
//            val code = initialResponse.code
//           Timber.d("code "+code.toString()+"\n"+request.toString())
//            Timber.e(initialResponse.toString())
//            return initialResponse
//
//        }

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
        Log.e("request",originalHttpUrl.toString())
            val requestBuilder = original.newBuilder()
             //   .addHeader("Authorization", " )
                .url(originalHttpUrl)
            return requestBuilder.build()

    }
}