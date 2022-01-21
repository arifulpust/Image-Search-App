package com.pluang.imagesearchapp

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.imageLoader
import com.facebook.stetho.Stetho
import com.pluang.imagesearchapp.data.interceptor.CoilInterceptor
import com.pluang.imagesearchapp.di.module.AppCoroutineScope
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import javax.inject.Inject

@HiltAndroidApp
class App : Application()
{
    @Inject
    @AppCoroutineScope
    lateinit var coroutineScope: CoroutineScope
    @Inject lateinit var coilInterceptor: CoilInterceptor
//    @CoilCache
//    lateinit var coilCache: Cache
    override fun onCreate() {
    super.onCreate()
    Stetho.initializeWithDefaults(this)
        initCoil()
}

    private fun initCoil() {
        val imageLoader = ImageLoader.Builder(this).apply {
            crossfade(true)

            okHttpClient {
                OkHttpClient.Builder()
                   // .cache(coilCache)
                    .addInterceptor(coilInterceptor)
                    .build()
            }

        }.build()

        Coil.setImageLoader(imageLoader)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        with(imageLoader) {
            bitmapPool.clear()
            memoryCache.clear()
        }
    }

}

