package com.rohyme.core.shared.di.modules

import android.content.Context
import com.rohyme.core.BuildConfig
import com.rohyme.core.dataLayer.dataSources.remote.service.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@InstallIn(ApplicationComponent::class)
@Module(includes = [MoshiModule::class])
object  NetworkModule {

    @Reusable
    @Provides
    fun providesHttpCache(appContext: Context): Cache? {
        val cacheSize = 10 * 1024 * 1024
        var cache: Cache? = null
        try {
            val myDir = File(appContext.cacheDir, "response")
            myDir.mkdir()
            cache = Cache(myDir, cacheSize.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return cache
    }


    @Reusable
    @Provides
    fun provideOkHttpClient(cache: Cache?): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .cache(cache)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Reusable
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Reusable
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}