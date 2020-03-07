package com.rohyme.core.shared.di.modules

import android.content.Context
import com.rohyme.core.BuildConfig
import com.rohyme.core.dataLayer.dataSources.remote.service.ApiService
import com.rohyme.core.shared.di.qualifiers.ApplicationContext
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module(includes = [MoshiModule::class])
object NetworkModule {

    @JvmStatic
    @Reusable
    @Provides
    fun providesHttpCache(@ApplicationContext appContext: Context): Cache? {
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


    @JvmStatic
    @Reusable
    @Provides
    fun provideOkhttpClient(cache: Cache?): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
//                .cache(cache)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @JvmStatic
    @Reusable
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    @Provides
    @Reusable
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}