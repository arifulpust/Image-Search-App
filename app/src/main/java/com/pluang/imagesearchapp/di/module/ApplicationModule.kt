package com.pluang.imagesearchapp.di.module

import android.content.Context
import coil.util.CoilUtils
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.pluang.imagesearchapp.BuildConfig
import com.pluang.imagesearchapp.data.api.ApiHelper
import com.pluang.imagesearchapp.data.api.ApiHelperImpl
import com.pluang.imagesearchapp.data.api.ApiService
import com.pluang.imagesearchapp.data.interceptor.CoilInterceptor

import com.squareup.moshi.Moshi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.Cache
import retrofit2.converter.gson.GsonConverterFactory

@Qualifier
annotation class DefaultCache
@Qualifier
annotation class CoilCache

@Qualifier
annotation class AppCoroutineScope
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .addNetworkInterceptor(StethoInterceptor())
           .  addInterceptor(CoilInterceptor())

            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,url: String
    ): Retrofit =
        Retrofit.Builder()
         // .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
//    @Provides
//    @Singleton
//    @DefaultCache
//    fun getCacheIterator(@ApplicationContext ctx: Context): Cache{
//        val cacheSize = 100 * 1024 * 1024 // 100 MB
//        return Cache(ctx.cacheDir, cacheSize.toLong())
//    }

    @Provides
    @Singleton
    @CoilCache
    fun getCoilCache(@ApplicationContext ctx: Context): Cache {
        return CoilUtils.createDefaultCache(ctx)
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    @AppCoroutineScope
    fun providesApplicationCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

}