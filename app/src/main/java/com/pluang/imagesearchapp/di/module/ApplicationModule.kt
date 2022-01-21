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
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
//@Qualifier
//annotation class DefaultCache
//@Qualifier
//annotation class CoilCache

@Qualifier
annotation class AppCoroutineScope
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
//

//    @Singleton
//    @Provides
//    fun provideMoshi(): Moshi {
//        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//    }
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        val clientBuilder = OkHttpClient.Builder().apply {
//            connectTimeout(60, TimeUnit.SECONDS)
//            readTimeout(60, TimeUnit.SECONDS)
//            retryOnConnectionFailure(false)
//            if (BuildConfig.DEBUG) {
//                addInterceptor(HttpLoggingInterceptor().also {
//                    it.level = HttpLoggingInterceptor.Level.BODY
//                })
//            }
//
//           // cache(cache)
//           // addInterceptor(CoilInterceptor())
//        }
//        if (BuildConfig.DEBUG) clientBuilder.addNetworkInterceptor(StethoInterceptor())
//
//        return clientBuilder.build()
//    }

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

           // .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
            //.addCallAdapterFactory(LiveDataCallAdapterFactory())

            .client(okHttpClient)
            .build()

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