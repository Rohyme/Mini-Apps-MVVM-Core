package com.rohyme.core.dataLayer.dataSources.remote.testingFeature

import com.squareup.moshi.Json

data class TestingResponse(
    val testingList: List<Testing> = listOf()
) {
    data class Testing(
        @Json(name = "body")
        var body: String = "",
        @Json(name = "id")
        var id: Int = 0,
        @Json(name = "title")
        var title: String = "",
        @Json(name = "userId")
        var userId: Int = 0
    )
}