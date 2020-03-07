package com.rohyme.core.shared.di.modules

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object MoshiModule {

    @JvmStatic
    @Reusable
    @Provides
    fun providesMoshi(): Moshi {
        val moshiBuilder = Moshi.Builder()
        return moshiBuilder.build()
    }
}