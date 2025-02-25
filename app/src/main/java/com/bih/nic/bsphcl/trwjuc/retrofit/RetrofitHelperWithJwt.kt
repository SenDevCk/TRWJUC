package com.bih.nic.bsphcl.trwjuc.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by Chandan Singh on 2/5/2025.
 */
object RetrofitHelperWithJwt {



    fun getInstance(jwtToken: String): Retrofit {
        // Create an Interceptor to add the JWT token to headers
        val interceptor = Interceptor { chain ->
            // Get the request
            val originalRequest: Request = chain.request()

            // Add Authorization header with JWT token
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $jwtToken")
                .build()

            // Proceed with the request
            chain.proceed(newRequest)
        }

        // Create an OkHttpClient and add the interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        // Return Retrofit instance
        return Retrofit.Builder()
            .baseUrl(Urls.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client) // Pass the OkHttpClient with JWT Interceptor
            .build()
    }
}