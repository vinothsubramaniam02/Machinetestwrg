package com.whiterabbittest.machinetest.data.network.api.service


import com.whiterabbittest.machinetest.data.network.api.response.ResponseTest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface TestRequestApi {
    @GET("5d565297300000680030a986")
    suspend fun reqNotificationSettings(): Response<ResponseTest>
}