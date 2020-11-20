package com.rohyme.core.shared.di.modules

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object MoshiModule {

    @Reusable
    @Provides
    fun providesMoshi(): Moshi {
        val moshiBuilder = Moshi.Builder()
        return moshiBuilder.build()
    }
}