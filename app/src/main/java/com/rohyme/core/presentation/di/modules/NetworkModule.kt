package com.rohyme.core.presentation.di.modules

import android.content.Context
import com.rohyme.core.presentation.di.qualifiers.ForApplication
import com.rohyme.core.data.service.ApiService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

  @Reusable
  @Provides
  fun providesHttpCache(@ForApplication appContext: Context): Cache? {
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
  fun providesGson(): Gson {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
    return gsonBuilder.create()
  }

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


  @Reusable
  @Provides
  fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
  }

  @Reusable
  @Provides
  fun providesApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }

}