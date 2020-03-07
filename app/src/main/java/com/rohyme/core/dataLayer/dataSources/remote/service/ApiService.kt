package com.rohyme.core.dataLayer.dataSources.remote.service

import com.rohyme.core.dataLayer.dataSources.remote.testingFeature.TestingResponse
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getTestPosts(): TestingResponse
}