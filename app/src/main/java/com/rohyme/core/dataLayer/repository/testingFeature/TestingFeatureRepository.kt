package com.rohyme.core.dataLayer.repository.testingFeature

import com.rohyme.core.dataLayer.dataSources.remote.service.ApiService
import com.rohyme.core.dataLayer.dataSources.remote.testingFeature.TestingResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestingFeatureRepository @Inject constructor(private val service : ApiService) {
    suspend fun getTestingPosts(): TestingResponse = service.getTestPosts()
}