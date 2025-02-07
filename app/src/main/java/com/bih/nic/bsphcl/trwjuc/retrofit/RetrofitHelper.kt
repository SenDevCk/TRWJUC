package com.bih.nic.bsphcl.trwjuc.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by Chandan Singh on 2/5/2025.
 */
object RetrofitHelper {

    val baseUrl = "http://localhost:8086/api-trwjuc/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}